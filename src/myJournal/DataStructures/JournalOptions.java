package myJournal.DataStructures;

import java.util.HashSet;

/**
 * Stores the options for a journal.
 */
public class JournalOptions{

	private boolean isPrivate;
	private boolean hasLikes;
	private boolean hasFollowers;
	private HashSet<Long> owners; // Can have multiple??? (design choice)
	private HashSet<Long> contributers;
	
	/**
	 * @param isPrivate
	 * @param hasLikes
	 * @param hasFollowers
	 * @param owners
	 * @param contributers
	 */
	public JournalOptions(boolean isPrivate, boolean hasLikes, boolean hasFollowers, HashSet<Long> owners, HashSet<Long> contributers) {
		this.isPrivate = isPrivate;
		this.hasLikes = isPrivate && hasLikes;  // Assuming no likes on a private Journal
		this.hasFollowers = isPrivate && hasFollowers; // Or Followers?
		this.owners = owners;
		this.contributers = contributers;
	}

	/**
	 * @return if the journal is private
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 * @return if the journal has likes
	 */
	public boolean hasLikes() {
		return hasLikes;
	}

	/**
	 * @return if the journal has followers
	 */
	public boolean hasFollowers() {
		return hasFollowers;
	}

	/**
	 * @return a hashset of the ids of all of the journal's owners
	 */
	public HashSet<Long> getOwners(){
		return owners;
	}

	/**
	 * @return a hashset of the ids of all of the journal's contributers
	 */
	public HashSet<Long> getContributers(){
		return contributers;
	}
	
	
	
	
}
