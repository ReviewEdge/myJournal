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

	/**
	 * @param o
	 * @return if they are equal
	 * @Override
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Feed)) {
			return false;
		}
		Feed s = (Feed) o;
		
		return (s.pages.equals(this.pages));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + pages.hashCode();
		return result;
	}
    
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pairArray("pages").add(pages).close();
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }
    
}