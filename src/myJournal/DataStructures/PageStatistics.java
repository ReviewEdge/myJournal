package myJournal.DataStructures;

import java.util.HashSet;

public class PageStatistics {
	private int views;
	private HashSet<Long> likers;
	private HashSet<Long> viewers;
	
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
	
	public void addView(long viewer) {
		viewers.add(viewer);
	}
	
}
