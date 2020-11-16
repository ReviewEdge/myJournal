package myJournal.DataStructures;

import java.util.HashSet;

public class PageStatistics {
	private HashSet<Long> likers;
	private HashSet<Long> viewers;
	
	public PageStatistics(Page p) {
		
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
	
}
