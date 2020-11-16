package myJournal.DataStructures;

import java.util.HashSet;

public class Page {
	private final long id;
	private String name;
	private String content;
	private long authorId;
	private PageStatistics stats;
	private Journal parentJournal;
	//Page Options
	private boolean hasLikes;
	private boolean hasViews;
	
	public Page(long newId, String newName, String content, long authorId, Journal parentJournal) {
		this.id = newId;
		this.name = newName;
		this.content = content;
		this.authorId = authorId;
		this.parentJournal = parentJournal;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public long getAuthorId() {
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
	
	public void setHasLikes(boolean hasLikes) {
		this.hasLikes = hasLikes;
	}
	
	public void setHasViews(boolean hasViews) {
		this.hasViews = hasViews;
	}
	
	public void addView(long viewerId) {
		this.stats.addView(viewerId);
	}
	
	public void addLiker(long likerId) {
		this.stats.addView(likerId);
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void writeContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return this.content;
	}
}
