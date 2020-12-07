package myJournal.DataStructures;

import myJournal.Intermediaries.PageId;
import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * A container for individual pages that can be public or private and can have multiple contributers.
 */
public class Journal implements Followable, JSONSerializable, Permissions{
	
	private final long id;
	private String name;
	private ArrayList<PageId> pageIds;
	private JournalStatistics stats;
	private JournalOptions options;
	
	/**
	 * @param id
	 * @param name
	 * @param pageIds
	 * @param stats
	 * @param options
	 */
	public Journal(long id, String name, ArrayList<PageId> pageIds, JournalStatistics stats, JournalOptions options) {
	    this.id = id;
	    this.name = name;
	    this.pageIds = pageIds;
	    this.stats = stats;
	    this.options = options;
	}

	public Journal authorized(long requestingId) {
		if(canView(requestingId)) return this;
		else throw new IllegalAccessAttempt();
	}
	
	/**
	 * @return id of journal as long
	 */
	public long getId() {
	    return id;
	}
	
	/**
	 * @return arrayList of all of the pages in the journal
	 */
	public ArrayList<Page> getPages() {
	    return PageId.toPageArray(pageIds);
	}
	
	
	// Statistical Data
	/**
	 * @return how many likes the journal has
	 */
	public long getLikes() {
		return stats.getLikes();
	}
	
	/**
	 * @return a HashSet of all of the journal's likers
	 */
	public HashSet<Long> getLikers() {
	    return stats.getLikers();
	}
	
	/**
	*
	* adds the @param id  of the account which liked the object
	*/
    public void addLiker(Long id){
  	   stats.addLiker(id);
    }
	   
	/**
	*
	* adds the @param id  of the account which liked the object
	*/
    public void removeLiker(Long id){
  	   stats.removeLiker(id);
    }
	    
	    /**
	 * @return how many followers a journal has
	 */
	public long getNumFollowers() {
	    return stats.getNumFollowers();
	}
	
	/**
	 * @return a HashSet of all of the journal's followers
	 */
	public HashSet<Long> getFollowers() {
	    return stats.getFollowers();
	}
	
	/**
	* 
	* adds the @param id  of the account which followed the object
	*/
	public void addFollower(Long id){
		stats.addFollower(id);
	}
	
	/**
	* 
	* removes the @param id  of the account which followed the object
	*/
	public void removeFollower(Long id){
		stats.removeFollower(id);
	}
	
	    /**
	*
	* @return the ids of the accounts which can view the object
	*/
   public HashSet<Long> getViewed(){
	   return stats.getViewed();
   }
   
   /**
	*
	* @return the ids of the accounts which can view the object
	*/
   public long getViews(){
	   return stats.getViewed().size();
   }
   
	/**
	*
	* adds the @param id  of the account which viewed the object
	*/
   public void addViewed(Long id){
	   stats.addViewed(id);
   }
	
   /**
	* @return the statistics object for the journal
	*/
	public JournalStatistics getStats() {
	    return stats;
	}

	
	// Options data:
	/**
	* @return if the journal is private
	*/
	public boolean isPrivate() {
	    return options.isPrivate();
	}
	
	/**
	* privacy sets JournalOptions isPrivate to @param privacy
	*/
	public void setPrivacy(boolean privacy, long requestingId) throws IllegalAccessAttempt{
		if (isOwner(requestingId))
			options.setPrivacy(privacy);
		else
			throw new IllegalAccessAttempt("Can't change privacy settings");
	}
	
	/**
	 * @return if the journal has likes
	 */
	public boolean hasLikes() {
	    return options.hasLikes();
	}
	
	/**
	* sets JournalOptions isPrivate to @param likes
	*/
	public void setHasLikes(boolean likes, long requestingId) throws IllegalAccessAttempt{
		if (isOwner(requestingId))
			options.setHasLikes(likes);
		else
			throw new IllegalAccessAttempt("Can't change like settings");
	}
	
	/**
	 * @return if the journal has followers
	 */
	public boolean hasFollowers() {
	    return options.hasFollowers();
	}
	
	/**
	 *  sets JournalOptions hasFollowers to @param followable
	 */
	public void setHasFollowers(boolean followable, long requestingId) throws IllegalAccessAttempt{
		if (isOwner(requestingId))
			options.setHasFollowers(followable);
		else
			throw new IllegalAccessAttempt("Can't change follower settings");
	}
	
	/**
	 * @return a HashSet of the owners of the journal
	 */
	public HashSet<Long> getOwners() {
	    return options.getOwners();
	}
	
	/**
	 * adds @param newOwner to the JournalOptions owners
	 */
	public void addOwner(long newOwner, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			options.addOwner(newOwner);
		else
			throw new IllegalAccessAttempt("Can't add a new owner");
	}
	
	/**
	 * removes @param oldOwner from the JournalOptions owners
	 */
	public void removeOwner(long oldOwner, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			options.removeOwner(oldOwner);
		else
			throw new IllegalAccessAttempt("Can't remove an owner");
	}
	
	/**
	 * @return a HashSet of the contributers to the journal
	 */
	public HashSet<Long> getContributers() {
	    return options.getContributers();
	}
	
	/**
	 * @return a HashSet of the contributers to the journal
	 */
	public HashSet<Long> getEditors(){
		return getContributers();
	}
	
	/**
	 * adds @param newContributer to the JournalOptions contributers
	 */
	public void addContributer(long newContributer, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			options.addContributer(newContributer);
		else
			throw new IllegalAccessAttempt("Can't add a new contributer");
	}
	
	/**
	 * removes @param newOwner from the JournalOptions owners
	 */
	public void removeContributer(long oldContributer, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			options.removeOwner(oldContributer);
		else
			throw new IllegalAccessAttempt("Can't remove a contributer");
	}
	
	/**
	 * @return a HashSet of the viewers of the journal
	 */
	public HashSet<Long> getViewers() {
		if (isPrivate())
			return options.getViewers();
		else
			return new HashSet<Long>();
	}
	
	/**
	 * adds @param newViewer to the JournalOptions viewers
	 */
	public void addViewer(long newViewer, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			options.addViewer(newViewer);
		else
			throw new IllegalAccessAttempt("Can't add a new viewer");
	}
	
	/**
	 * removes @param oldViewer from the JournalOptions viewers
	 */
	public void removeViewer(long oldViewer, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			options.removeOwner(oldViewer);
		else
			throw new IllegalAccessAttempt("Can't remove a viewer");
	}
	
	/**
	 * @return the options object for the journal
	 */
	public JournalOptions getOptions() {
	    return options;
	}
	
	// TO DO:
	
	/**
	 * Adds a new page to the journal.
	 * @param newPageId
	 */
	public void addPage(PageId newPageId, long requestingId) throws IllegalAccessAttempt {
		if (canEdit(requestingId))
			pageIds.add(newPageId);
		else
			throw new IllegalAccessAttempt("Can't add pages");
	}

	public void removePage(Page toRemove, long requestingId) {
		if (isOwner(requestingId))
			pageIds.remove(toRemove);
		else
			throw new IllegalAccessAttempt("Can't remove page.");
	}
	
	/**
	 * @param requestingId
	 * @return the latest page in the journal
	 */
	public Page getLatestPage(long requestingId) throws IllegalAccessAttempt {
		if (canView(requestingId)) 
			return pageIds.get(pageIds.size()-1).getPage();
		throw new IllegalAccessAttempt("Can't view page");
	}
		
	/**
	 * @param requestingId
	 * @return the id of all of the pages in the journal
	 */
	public ArrayList<Page> getPages(long requestingId) throws IllegalAccessAttempt {
		if (canView(requestingId)) {
			return PageId.toPageArray(pageIds);
		}
		throw new IllegalAccessAttempt("Can't view page");
	}

	public ArrayList<PageId> getPageIds() {
		return pageIds;
	}
	
	/**
	 * @return how many pages are in the journal
	 */
	public long getNumPages() {
	    return pageIds.size();
	}
	
	/**
	 * @return the name of the journal
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name of the journal
	 */
	public void setName(String name, long requestingId) throws IllegalAccessAttempt {
		if (isOwner(requestingId))
			this.name = name;
		else
			throw new IllegalAccessAttempt("Can't change name");
	}
	
	public Journal copyWithId(long id) {
	    return new Journal(id, name, pageIds, stats, options);
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
		if (!(o instanceof Journal)) {
			return false;
		}
		Journal s = (Journal) o;
		
		return ((s.id == this.id) && s.name.equals(this.name) && s.pageIds.equals(this.pageIds)
				&& s.stats.equals(this.stats) && s.options.equals(this.options));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + (Long.valueOf(id).hashCode());
		result = result*37 + name.hashCode();	
		result = result*37 + pageIds.hashCode();
		result = result*37 + stats.hashCode();
		result = result*37 + options.hashCode();
		return result;
	}
	
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
	public JSONElement asJsonElement() {
	    JSONBuilder jb = JSONBuilder.object();
	    jb.pair("name", name);
	jb.pair("id", JSONValue.from(id));
	jb.pairArray("pages").add(pageIds).close();
	jb.pair("options", options);
	jb.pair("stats", stats);
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
