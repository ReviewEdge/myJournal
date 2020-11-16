package myJournal.DataStructures;

import java.util.HashSet;

/**
 *
 */
public class JournalOptions{

	private boolean isPrivate;
	private boolean hasLikes;
	private boolean hasFollowers;
	private HashSet<Long> owners; // Can have multiple??? (design choice)
	private HashSet<Long> contributers;
	
	public JournalOptions(boolean isPrivate, boolean hasLikes, boolean hasFollowers, HashSet<Long> owners, HashSet<Long> contributers) {
		this.isPrivate = isPrivate;
		this.hasLikes = isPrivate && hasLikes;  // Assuming no likes on a private Journal
		this.hasFollowers = isPrivate && hasFollowers; // Or Followers?
		this.owners = owners;
		this.contributers = contributers;
	}

	/**
	 *
	 * @return
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 *
	 * @return
	 */
	public boolean hasLikes() {
		return hasLikes;
	}

	/**
	 *
	 * @return
	 */
	public boolean hasFollowers() {
		return hasFollowers;
	}

	/**
	 *
	 * @return
	 */
	public HashSet<Long> getOwners(){
		return owners;
	}

	/**
	 *
	 * @return
	 */
	public HashSet<Long> getContributers(){
		return contributers;
	}
	
	
	
	
}
