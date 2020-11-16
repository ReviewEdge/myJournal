package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;

public class JournalStatistics extends Statistics {

	private HashSet<Long> likers;
	private HashSet<Long> followers;
	private HashSet<Long> viewers;
	
	public JournalStatistics(HashSet<Long> likers, HashSet<Long> followers, HashSet<Long> viewers) {
		this.likers = likers;
		this.followers = followers;
		this.viewers = viewers;
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
	
	public HashSet<Long> getViewers () {
		return viewers;
	}

	public int getNumViewers() {
		return viewers.size();
	}


}
