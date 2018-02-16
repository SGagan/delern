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

package org.dasfoo.delern.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import org.dasfoo.delern.models.helpers.MultiWrite;

import io.reactivex.Completable;

/**
 * Created by katarina on 2/22/17.
 * Model class for deck_access.
 */
@SuppressWarnings(/* firebase */ {"checkstyle:MemberName", "checkstyle:HiddenField"})
public class DeckAccess extends Model {
    private String access;

    /**
     * An empty constructor is required for Firebase deserialization.
     */
    private DeckAccess() {
        super(null, null);
    }

    /**
     * Create a DeckAccess object associated with a deck.
     *
     * @param deck Deck this DeckAccess belongs to.
     */
    public DeckAccess(final Deck deck) {
        super(deck, null);
    }

    /**
     * Get the Deck this DeckAccess is associated with.
     *
     * @return Model parent casted to Deck (if set).
     */
    @Exclude
    public Deck getDeck() {
        return (Deck) getParent();
    }

    /**
     * Getter for access of deck for the user.
     *
     * @return access of deck.
     */
    public String getAccess() {
        return access;
    }

    /**
     * Setter of access of deck for the user.
     *
     * @param acs sets access for deck.
     */
    public void setAccess(final String acs) {
        this.access = acs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DeckAccess{" + super.toString() +
                ", access='" + access + '\'' +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Exclude
    @Override
    public <T> DatabaseReference getChildReference(final Class<T> childClass) {
        if (childClass == User.class) {
            DatabaseReference reference = getDeck().getUser()
                    .getChildReference(childClass).child(getKey());
            reference.keepSynced(true);
            return reference;
        }

        return super.getChildReference(childClass);
    }

    /**
     * Remove the current deckAccess from the database.
     *
     * @return FirebaseTaskAdapter for the delete operation.
     */
    @Exclude
    public Completable delete() {
        return new MultiWrite()
                .delete(this)
                .write();
    }
}