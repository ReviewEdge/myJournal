package main.java.myJournal.DataStructures;

import java.util.HashSet;

/**
 * Stores the statistics for a page.
 */
public class PageStatistics {
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
	
}
