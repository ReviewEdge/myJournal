package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;

public class JournalStatistics extends Statistics {

	private HashSet<Long> likers;
	private HashSet<Long> followers;
	
	public JournalStatistics(HashSet<Long> likers, HashSet<Long> followers) {
		this.likers = likers;
		this.followers = followers;
	}
	
	public long getLikes() {
		return likers.size();
	}
	
	public HashSet<Long> getLikers() {
		return likers;
	}
	
	public HashSet<Long> getFollowers(){
		return followers;
	}
	
	public long getNumFollowers() {
		return followers.size();
	}
	
	
	
	
	
}
