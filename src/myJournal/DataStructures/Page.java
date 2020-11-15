package myJournal.DataStructures;

import java.util.HashSet;

public abstract class Page {
	private final int id;
	private String name;
	private String content;
	private int authorId;
	private PageStatistics stats;
	private PageOptions options;
	
	public Page(int newId, String newName, int authorId) {
	
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int getAuthorId() {
		return this.authorId;
	}
	
	public int getLikes() {
		return this.stats.getLikes();
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
