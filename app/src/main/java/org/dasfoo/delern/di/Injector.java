/*
 * Copyright (C) 2017 Katarina Sheremet
 * This file is part of Delern.
 *
 * Delern is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Delern is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with  Delern.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.dasfoo.delern.di;

import org.dasfoo.delern.di.components.AddCardActivityComponent;
import org.dasfoo.delern.di.components.DaggerAddCardActivityComponent;
import org.dasfoo.delern.di.components.DaggerDelernMainActivityComponent;
import org.dasfoo.delern.di.components.DaggerEditCardListActivityComponent;
import org.dasfoo.delern.di.components.DaggerLearningCardsActivityComponent;
import org.dasfoo.delern.di.components.DaggerPreEditCardActivityComponent;
import org.dasfoo.delern.di.components.DaggerUpdateCardActivityComponent;
import org.dasfoo.delern.di.components.DelernMainActivityComponent;
import org.dasfoo.delern.di.components.EditCardListActivityComponent;
import org.dasfoo.delern.di.components.LearningCardsActivityComponent;
import org.dasfoo.delern.di.components.PreEditCardActivityComponent;
import org.dasfoo.delern.di.components.UpdateCardActivityComponent;
import org.dasfoo.delern.di.modules.AddCardActivityModule;
import org.dasfoo.delern.di.modules.DelernMainActivityModule;
import org.dasfoo.delern.di.modules.EditCardListActivityModule;
import org.dasfoo.delern.di.modules.LearningCardsActivityModule;
import org.dasfoo.delern.di.modules.PreEditCardActivityModule;
import org.dasfoo.delern.di.modules.UpdateCardActivityModule;
import org.dasfoo.delern.models.Card;
import org.dasfoo.delern.models.Deck;
import org.dasfoo.delern.addupdatecard.IAddEditCardView;
import org.dasfoo.delern.listdecks.IDelernMainView;
import org.dasfoo.delern.learncards.ILearningCardsView;
import org.dasfoo.delern.previewcard.IPreEditCardView;

/**
 * Initialize components for creating class.
 */
public final class Injector {

    private Injector() {
        // Private constructor to prohibit to create instance of clall
    }

    /**
     * Method returns injector class.
     *
     * @param view view to init Presenter for callbacks.
     * @return DelernMainActivityComponent.
     */
    public static DelernMainActivityComponent getMainActivityInjector(final IDelernMainView view) {
        return DaggerDelernMainActivityComponent
                .builder()
                .delernMainActivityModule(new DelernMainActivityModule(view)).build();
    }

    /**
     * Method returns injector class.
     *
     * @param view view to init Presenter for callbacks.
     * @param deck deck to init Presenter.
     * @return AddEditCardActivityComponent.
     */
    public static AddCardActivityComponent getAddActivityInjector(
            final IAddEditCardView view, final Deck deck) {
        return DaggerAddCardActivityComponent
                .builder()
                .addCardActivityModule(new AddCardActivityModule(view, deck)).build();
    }

    /**
     * Method returns injector class.
     *
     * @param view view to init Presenter for callbacks.
     * @param card card to init Presenter.
     * @return AddEditCardActivityComponent.
     */
    public static UpdateCardActivityComponent getUpdateActivityInjector(
            final IAddEditCardView view, final Card card) {
        return DaggerUpdateCardActivityComponent
                .builder()
                .updateCardActivityModule(new UpdateCardActivityModule(view, card)).build();
    }

    /**
     * Method returns injector class.
     *
     * @return EditCardListActivityComponent.
     */
    public static EditCardListActivityComponent getEditCardListActivityInjector() {
        return DaggerEditCardListActivityComponent
                .builder()
                .editCardListActivityModule(new EditCardListActivityModule()).build();
    }

    /**
     * Method returns injector class.
     *
     * @param view view to init Presenter for callbacks.
     * @return LearningCardsActivityComponent.
     */
    public static LearningCardsActivityComponent getLearningCardsActivityInjector(
            final ILearningCardsView view) {
        return DaggerLearningCardsActivityComponent
                .builder()
                .learningCardsActivityModule(new LearningCardsActivityModule(view)).build();
    }

    /**
     * Method returns injector class.
     *
     * @param view view to init Presenter for callbacks.
     * @return LearningCardsActivityComponent.
     */
    public static PreEditCardActivityComponent getPreEditCardActivityInjector(
            final IPreEditCardView view) {
        return DaggerPreEditCardActivityComponent
                .builder()
                .preEditCardActivityModule(new PreEditCardActivityModule(view)).build();
    }
}
