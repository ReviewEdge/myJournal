package main.java.myJournal.DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that creates a set of pages.
 */
public class Feed {
    private LinkedList<Page> pages;

    public Feed(ArrayList<Followable> subscribed) {

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
}
