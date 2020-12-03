package myJournal.DataStructures;

import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that creates a set of pages.
 */
public class Feed implements JSONSerializable {
    private LinkedList<Page> pages;

    public Feed(ArrayList<FollowableId> subscribed) {

    }

    /**
     * Repopulate the internal page list.
     */
    public void refreshFeed() {

    }

    /**
     *
     * @return the next page for the user to read
     */
    public Page getPage() {
        //refresh page after no more page
        return null;
    }

    @Override
    public JSONElement asJsonElement() {
        return null;
    }

    @Override
    public String asJson() {
        return null;
    }
}
