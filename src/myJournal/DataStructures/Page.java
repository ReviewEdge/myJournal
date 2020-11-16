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
	

	/**
	 * @param newId
	 * @param newName
	 * @param content
	 * @param authorId
	 * @param parentJournal
	 * @param hasLikes
	 * @param hasViews
	 */
	public Page(long newId, String newName, String content, long authorId, Journal parentJournal, boolean hasLikes, boolean hasViews) {
		this.id = newId;
		this.name = newName;
		this.content = content;
		this.authorId = authorId;
		this.parentJournal = parentJournal;
		this.hasLikes = hasLikes;
		this.hasViews = hasViews;
	}
	
	/**
	 * @return id of page
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return the name of the page
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the page's text content
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * @return the page author's id
	 */
	public long getAuthorId() {
		return this.authorId;
	}
	
	/**
	 * @return how many likes the page has
	 */
	public int getLikes() {
		return this.stats.getLikes();
	}
	
	/**
	 * @return how many views the page has
	 */
	public int getViews() {
		return this.stats.getViews();
	}
	
	/**
	 * @return hashset of all the viewers of the page
	 */
	public HashSet<Long> getViewers() {
		return this.stats.getViewers();
	}
	
	/**
	 * @return all of the liker's ids
	 */
	public HashSet<Long> getLikers() {
		return this.stats.getLikers();
	}
	
	/**
	 * @return the page's parent journal 
	 */
	public Journal getParentJournal() {
		return this.parentJournal;
	}
	
	/**
	 * @return if the page has likes
	 */
	public boolean hasLikes() {
		return this.hasLikes;
	}
	
	/**
	 * @return if the page has views
	 */
	public boolean hasViews() {
		return this.hasViews;
	}
	
	/**
	 * @param hasLikes
	 */
	public void setHasLikes(boolean hasLikes) {
		this.hasLikes = hasLikes;
	}
	
	/**
	 * @param hasViews
	 */
	public void setHasViews(boolean hasViews) {
		this.hasViews = hasViews;
	}
	
	/**
	 * @param viewerId
	 */
	public void addView(long viewerId) {
		this.stats.addView(viewerId);
	}
	
	/**
	 * @param likerId
	 */
	public void addLiker(long likerId) {
		this.stats.addView(likerId);
	}
	
	/**
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * @param content
	 */
	public void writeContent(String content) {
		this.content = content;
	}
	
	/**
	 * return the content as a string
	 */
	@Override
	public String toString() {
		return this.content;
	}
}
