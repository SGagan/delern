import 'dart:async';

import 'package:meta/meta.dart';

import '../models/base/stream_muxer.dart';
import '../models/base/transaction.dart';
import '../models/card.dart';
import '../models/deck.dart';
import '../models/scheduled_card.dart';
import '../remote/analytics.dart';

enum LearningUpdateType {
  deckUpdate,
  scheduledCardUpdate,
}

class LearningViewModel {
  ScheduledCardModel _scheduledCard;

  ScheduledCardModel get scheduledCard => _scheduledCard;
  CardModel get card => _scheduledCard?.card;

  final DeckModel deck;
  final bool allowEdit;

  LearningViewModel({@required this.deck, @required this.allowEdit});

  Stream<LearningUpdateType> get updates {
    logStartLearning(deck.key);
    return StreamMuxer({
      LearningUpdateType.deckUpdate: deck.updates,
      LearningUpdateType.scheduledCardUpdate: ScheduledCardModel.next(deck)
          .transform(StreamTransformer.fromHandlers(handleData: (sc, sink) {
        _scheduledCard = sc;
        sink.add(null);
      })),
      // We deliberately do not subscribe to Card updates (i.e. we only watch
      // ScheduledCard). If the card that the user is looking at right now is
      // updated live, it can result in bad user experience.
    }).map((muxerEvent) => muxerEvent.stream);
  }

  Future<void> answer(bool knows, bool learnBeyondHorizon) {
    var cv = _scheduledCard.answer(knows, learnBeyondHorizon);
    return (Transaction()..save(_scheduledCard)..save(cv)).commit();
  }

  Future<void> deleteCard() =>
      (Transaction()..delete(card)..delete(_scheduledCard)).commit();
}
