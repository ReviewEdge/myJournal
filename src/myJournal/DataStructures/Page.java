package myJournal.DataStructures;

import java.util.HashSet;

public abstract class Page {
	private final int id;
	private String name;
	private String content;
	private PageStatistics stats;
	private PageOptions options;
	
	public Page(int newId, String newName) {
	
	}
	
	public int getId() {
		
	}
	
	public String getContent() {
		
	}
	
	public int getLikes() {
		
	}
	
	public int getViews() {
		return this.stats.getViews();
	}
	
	public HashSet<Long> getLikers() {
		return this.stats.getLikers();
	}
	
	public void addView() {
		
	}
	
	public void setName() {
		
	}
	
	public void addLiker() { //not sure what this should take as input
		
	}
	
	@Override
	public String toString() {
		
	}
}
