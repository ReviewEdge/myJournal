package myJournal.DataStructures;

import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

import java.util.HashSet;


/**
 * Holds text as content that can be viewed, and belongs to a journal.
 */
public class Page implements JSONSerializable {
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
	 */
	public Page(long newId, String newName, String content, long authorId, Journal parentJournal) {
		this.id = newId;
		this.name = newName;
		this.content = content;
		this.authorId = authorId;
		this.parentJournal = parentJournal;
		this.stats = new PageStatistics();
		this.hasLikes = this.getParentJournal().hasLikes();
		this.hasViews = true; //this.getParentJournal().hasViews();
	}

	public Page(long newId, String newName, String content, long authorId, Journal parentJournal, PageStatistics stats) {
		this.id = newId;
		this.name = newName;
		this.content = content;
		this.authorId = authorId;
		this.parentJournal = parentJournal;
		this.stats = stats;
		this.hasLikes = this.getParentJournal().hasLikes();
		this.hasViews = true; //this.getParentJournal().hasViews();
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
		this.stats.addLiker(likerId);
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

	public Page copyWithId(long id) {
		return new Page(id, name, content, authorId, parentJournal);
	}

	public Page authorized(long requestingId) {
		if(parentJournal.canView(requestingId)) return this;
		else throw new IllegalAccessAttempt();
	}
	
	/**
	 * @return the content as a string
	 */
	@Override
	public String toString() {
		return this.content;
	}
	
	/**
	 * @param o
	 * @return if they are equal
	 * @Override
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Page)) {
			return false;
		}
		Page s = (Page) o;
		
		return ((s.id == this.id) && s.name.equals(this.name) && s.content.equals(this.content) 
				&& s.stats.equals(this.stats) && s.authorId == this.authorId  && s.parentJournal.getId() == this.parentJournal.getId()
				 && s.hasLikes == this.hasLikes  && s.authorId == this.authorId);
	}
	
	/**
	 * @return the HashCode of the object
	 * 	@Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + Long.hashCode(id);
		result = result*37 + name.hashCode();	
		result = result*37 + content.hashCode();
		result = result*37 + Long.hashCode(authorId);
		result = result*37 + stats.hashCode();
		result = result*37 + parentJournal.hashCode();
		result = result*37 + Boolean.hashCode(hasLikes);
		result = result*37 + Boolean.hashCode(hasViews);
		return result;
	}

	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("id", JSONValue.from(id));
        jb.pair("name", name);
        jb.pair("content", content);
        jb.pair("authorId", JSONValue.from(authorId));
        jb.pair("stats", stats);
        jb.pair("parentJournal", parentJournal);
        jb.pair("hasLikes", hasLikes);
        jb.pair("hasViews", hasViews);
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }

	public void removeLiker(long likingId) {
    	this.stats.removeLiker(likingId);
	}
}
