package myJournal.DataStructures;

import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;

import java.util.HashSet;

/**
 * Stores the statistics for a journal.
 */
public class JournalStatistics implements JSONSerializable {

	private HashSet<Long> likers;
	private HashSet<Long> followers;
	private HashSet<Long> viewers;
	
	/**
	 * @param likers
	 * @param followers
	 * @param viewers
	 */
	public JournalStatistics(HashSet<Long> likers, HashSet<Long> followers, HashSet<Long> viewers) {
		this.likers = likers;
		this.followers = followers;
		this.viewers = viewers;
	}

	/**
	 * @return how many likes the journal has
	 */
	public long getLikes() {
		return likers.size();
	}

	/**
	 * @return a HashSet of the ids of all of the journal's likers
	 */
	public HashSet<Long> getLikers() {
		return likers;
	}

	/**
	 * @return a HashSet of the ids of all of the journal's followers
	 */
	public HashSet<Long> getFollowers(){
		return followers;
	}

	/**
	 * @return how many followers the journal has
	 */
	public long getNumFollowers() {
		return followers.size();
	}

	/**
	 * @return a HashSet of the ids of all of the journal's viewers
	 */
	public HashSet<Long> getViewers () {
		return viewers;
	}

	/**
	 * @return how many viewers the journal has
	 */
	public int getNumViewers() {
		return viewers.size();
	}
	
	/**
	 * @param likerId
	 */
	public void addLiker(long likerId) {
		likers.add(likerId);
	}

	/**
	 * @param followerId
	 */
	public void addFollower(long followerId) {
		followers.add(followerId);
	}

	/**
	 * @param viewerId
	 */
	public void addViewer(long viewerId) {
		viewers.add(viewerId);
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
		if (!(o instanceof JournalStatistics)) {
			return false;
		}
		JournalStatistics s = (JournalStatistics) o;
		
		return (s.likers.equals(this.likers) && s.followers.equals(this.followers) && s.viewers.equals(this.viewers));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + likers.hashCode();
		result = result*37 + followers.hashCode();	
		result = result*37 + viewers.hashCode();	
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
