package myJournal.DataStructures;

import java.util.HashSet;

/**
 * Stores the options for a journal.
 */
public class JournalOptions{

	private boolean isPrivate;
	private boolean hasLikes;
	private boolean hasFollowers;
	private HashSet<Long> owners; 
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
	 * @return a HashSet of the ids of all of the journal's owners
	 */
	public HashSet<Long> getOwners(){
		return owners;
	}

	/**
	 * @return a HashSet of the ids of all of the journal's contributers
	 */
	public HashSet<Long> getContributers(){
		return contributers;
	}
	
	/**
	 * @param isPrivate
	 */
	public void setPrivacy(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @param hasLikes
	 */
	public void setHasLikes(boolean hasLikes) {
		this.hasLikes = hasLikes;
	}

	/**
	 * @param hasFollowers
	 */
	public void setHasFollowers(boolean hasFollowers) {
		this.hasFollowers = hasFollowers;
	}
	
	/**
	 * @param newOwner
	 */
	public void addOwner(long newOwner){
		owners.add(newOwner);
	}

	/**
	 * @param newContributer
	 */
	public void addContributer(long newContributer){
		contributers.add(newContributer);
	}

	/**
	 * @param Owner
	 */
	public void removeOwner(long Owner) throws IllegalArgumentException{
		try {
			owners.add(Owner);
		}
		catch (Exception e){
			throw new IllegalArgumentException("Owner does not exist");
		}
	}

	/**
	 * @param Contributer
	 */
	public void removeContributer(long Contributer) throws IllegalArgumentException{
		try {
		contributers.add(Contributer);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Contributer does not exist");
		}
	}
	
}
