package myJournal.DataStructures;

import java.util.HashSet;

public class JournalOptions extends Options {

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
	
	public boolean isPrivate() {
		return isPrivate;
	}
	
	public boolean hasLikes() {
		return hasLikes;
	}
	
	public boolean hasFollowers() {
		return hasFollowers;
	}
	
	public HashSet<Long> getOwners(){
		return owners;
	}
	
	public HashSet<Long> getContributers(){
		return contributers;
	}
	
	
	
	
}
