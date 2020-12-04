package myJournal.DataStructures;

import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Stores the options for a journal.
 */
public class JournalOptions implements JSONSerializable {

	private boolean isPrivate;
	private boolean hasLikes;
	private boolean hasFollowers;
	private HashSet<Long> owners; 
	private HashSet<Long> contributers;
	private HashSet<Long> viewers;
	
	/**
	 * @param isPrivate
	 * @param hasLikes
	 * @param hasFollowers
	 * @param owners
	 * @param contributers
	 */
	public JournalOptions(boolean isPrivate, boolean hasLikes, boolean hasFollowers, HashSet<Long> owners, HashSet<Long> contributers, HashSet<Long> viewers) {
		this.isPrivate = isPrivate;
		this.hasLikes = isPrivate && hasLikes;  // Assuming no likes on a private Journal
		this.hasFollowers = isPrivate && hasFollowers; // Or Followers?
		this.owners = owners;
		this.contributers = contributers;
		this.viewers = viewers;
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
	 * @return a HashSet of the ids of all of the journal's viewers
	 */
	public HashSet<Long> getViewers(){
		return viewers;
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
	 * @param newViewer
	 */
	public void addViewer(long newViewer){
		viewers.add(newViewer);
	}

	/**
	 * @param Owner
	 */
	public void removeOwner(long Owner) throws IllegalArgumentException{
		try {
			owners.remove(Owner);
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
		contributers.remove(Contributer);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Contributer does not exist");
		}
	}
	
	/**
	 * @param Contributer
	 */
	public void removeViewer(long viewer) throws IllegalArgumentException{
		try {
		viewers.remove(viewer);
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
		
		return (j.owners.equals(this.owners) && j.contributers.equals(this.contributers) && j.viewers.equals(this.viewers) 
				&& (j.isPrivate == this.isPrivate) && (j.hasLikes == this.hasLikes) && (j.isPrivate == this.isPrivate));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + owners.hashCode();
		result = result*37 + contributers.hashCode();
		result = result*37 + viewers.hashCode();
		result = result*37 + Boolean.hashCode(hasFollowers);
		result = result*37 + Boolean.hashCode(hasLikes);
		result = result*37 + Boolean.hashCode(isPrivate);
		return result;
	}

	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("isPrivate", isPrivate);
        jb.pair("hasLikes", hasLikes);
        jb.pair("hasFollowers", hasFollowers);
        jb.pairArray("owners").addValues(owners).close();
        jb.pairArray("contributers").addValues(contributers).close();
        jb.pairArray("viewers").addValues(viewers).close();
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }
}

