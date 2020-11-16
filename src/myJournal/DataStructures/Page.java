package myJournal.DataStructures;

import java.util.HashSet;

public class Page {
	private final int id;
	private String name;
	private String content;
	private int authorId;
	private PageStatistics stats;
	private Journal parentJournal;
	//Page Options
	private boolean hasLikes;
	private boolean hasViews;
	
	public Page(int newId, String newName, String content, int authorId, Journal parentJournal) {
		this.id = newId;
		this.name = newName;
		this.content = content;
		this.authorId = authorId;
		this.parentJournal = parentJournal;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
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
	
	public Journal getParentJournal() {
		return this.parentJournal;
	}
	
	public boolean hasLikes() {
		return this.hasLikes;
	}
	
	public boolean hasViews() {
		return this.hasViews;
	}
	
	public void addView() {
		this.stats.addView();
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void writeContent(String content) {
		this.content = content;
	}
	
	// TO DO:
	public void addLiker() { //not sure what this should take as input
		
	}
	
	@Override
	public String toString() {
		return this.content;
	}
}
