package myJournal.DataStructures;

import java.util.ArrayList;

public class PageStatistics extends Statistics {
	private int views;
	private ArrayList<Integer> likers;		// HashSet Instead? So only unique people are liking?
	
	public PageStatistics(Page p) {
		
	}
	
	public int getLikes() {
		return this.likers.size();
	}
	
	public int getViews() {
		return this.views;
	}
	
	public ArrayList<Integer> getLikers() {
		return this.likers;
	}
	
	
}
