package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public class JournalStatistics {

	private HashSet<Long> likers;
	private HashSet<Long> followers;
	private HashSet<Long> viewers;
	
	public JournalStatistics(HashSet<Long> likers, HashSet<Long> followers, HashSet<Long> viewers) {
		this.likers = likers;
		this.followers = followers;
		this.viewers = viewers;
	}

	/**
	 *
	 * @return
	 */
	public long getLikes() {
		return likers.size();
	}

	/**
	 *
	 * @return
	 */
	public HashSet<Long> getLikers() {
		return likers;
	}

	/**
	 *
	 * @return
	 */
	public HashSet<Long> getFollowers(){
		return followers;
	}

	/**
	 *
	 * @return
	 */
	public long getNumFollowers() {
		return followers.size();
	}

	/**
	 *
	 * @return
	 */
	public HashSet<Long> getViewers () {
		return viewers;
	}

	/**
	 *
	 * @return
	 */
	public int getNumViewers() {
		return viewers.size();
	}


}
