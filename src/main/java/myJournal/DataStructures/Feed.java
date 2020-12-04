package myJournal.DataStructures;

import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

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
        JSONBuilder jb = JSONBuilder.object();
        jb.pairArray("pages").add(pages).close();
        return jb.toJSONElement();
    }

    @Override
    public String asJson() {
        return asJsonElement().toJSONString();
    }
    
}