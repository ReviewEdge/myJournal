package myJournal.DataStructures;

import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

import java.util.HashSet;

/**
 * Stores the statistics for a page.
 */
public class PageStatistics implements JSONSerializable {
	private HashSet<Long> likers;
	private HashSet<Long> viewers;
	

	/**
	 * @param p
	 * @param likers
	 * @param viewers
	 */
	public PageStatistics(Page p, HashSet<Long> likers, HashSet<Long> viewers) {
		
	}
	
	/**
	 * @return how many likes the page has
	 */
	public int getLikes() {
		return this.likers.size();
	}
	
	/**
	 * @return how many views the page has
	 */
	public int getViews() {
		return this.viewers.size();
	}
	
	/**
	 * @return a hashset of all of the viewers
	 */
	public HashSet<Long> getViewers() {
		return this.viewers;
	}
	
	/**
	 * @return a hashset of all of the likers
	 */
	public HashSet<Long> getLikers() {
		return likers;
	}
	
	/**
	 * add a viewer
	 * @param viewer
	 */
	public void addView(long viewer) {
		viewers.add(viewer);
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
		if (!(o instanceof PageStatistics)) {
			return false;
		}
		PageStatistics s = (PageStatistics) o;
		
		return (s.likers.equals(this.likers) && s.viewers.equals(this.viewers));
	}
	
	/**
	 * @return the HashCode of the object
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = result*37 + likers.hashCode();
		result = result*37 + viewers.hashCode();	
		return result;
	}

	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pairArray("likers").addValues(likers).close();
        jb.pairArray("viewers").addValues(viewers).close();
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }

    public void addLiker(long likerId) {
    	this.likers.add(likerId);
    }

    public void removeLiker(long likerId) {
    	this.likers.remove(likerId);
	}
}
