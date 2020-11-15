package myJournal.DataStructures;

import java.util.HashSet;

public class PageStatistics extends Statistics {
	private int views;
	private HashSet<Long> likers;		// HashSet Instead? So only unique people are liking?
	
	public PageStatistics(Page p) {
		
	}
	
	public int getLikes() {
		return this.likers.size();
	}
	
	public int getViews() {
		return this.views;
	}
	
	public HashSet<Long> getLikers() {
		return likers;
	}
	
	public void addView() {
		this.views++;
	}
	
	
}
