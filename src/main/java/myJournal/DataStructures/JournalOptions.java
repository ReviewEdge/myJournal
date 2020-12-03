package myJournal.DataStructures;

import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;

import java.util.HashSet;

/**
 * Stores the options for a journal.
 */
public class JournalOptions implements JSONSerializable {

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
	
	/**
	 * @param o
	 * @return if they are equal
	 * @Override
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof JournalOptions)) {
			return false;
		}
		JournalOptions j = (JournalOptions) o;
		
		return (j.owners.equals(this.owners) && j.contributers.equals(this.contributers) && (j.isPrivate == this.isPrivate)
				&& (j.hasLikes == this.hasLikes) && (j.isPrivate == this.isPrivate));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + owners.hashCode();
		result = result*37 + contributers.hashCode();
		
		if (isPrivate) {
			result = result*37 + 1;
		}
		else {
			result *= 37;
		}
		
		if (hasLikes) {
			result = result*37 + 1;
		}
		else {
			result *= 37;
		}
		
		if (hasFollowers) {
			result = result*37 + 1;
		}
		else {
			result *= 37;
		}
		
		return result;
	}

	@Override
	public JSONElement asJsonElement() {
		return null;
	}

	@Override
	public String asJson() {
		return null;
	}
}
