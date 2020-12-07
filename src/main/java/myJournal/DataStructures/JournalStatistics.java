package myJournal.DataStructures;

import myJournal.util.JSON.*;

import java.util.HashSet;

/**
 * Stores the statistics for a journal.
 */
public class JournalStatistics implements JSONSerializable {

	private HashSet<Long> likers;
	private HashSet<Long> followers;
	private HashSet<Long> viewed;
	
	/**
	 * @param likers
	 * @param followers
	 * @param viewed
	 */
	public JournalStatistics(HashSet<Long> likers, HashSet<Long> followers, HashSet<Long> viewed) {
		this.likers = likers;
		this.followers = followers;
		this.viewed = viewed;
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
	public HashSet<Long> getViewed () {
		return viewed;
	}

	/**
	 * @return how many viewers the journal has
	 */
	public int getNumViewed() {
		return viewed.size();
	}
	
	/**
	 * @param likerId
	 */
	public void addLiker(long likerId) {
		likers.add(likerId);
	}

	/**
	 * @param likerId
	 */
	public void removeLiker(long likerId) {
		likers.remove(likerId);
	}

	/**
	 * @param followerId
	 */
	public void addFollower(long followerId) {
		followers.add(followerId);
	}

	/**
	 * @param followerId
	 */
	public void removeFollower(long followerId) {
		followers.remove(followerId);
	}
	
	/**
	 * @param viewerId
	 */
	public void addViewed(long viewerId) {
		viewed.add(viewerId);
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
		
		return (s.likers.equals(this.likers) && s.followers.equals(this.followers) && s.viewed.equals(this.viewed));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + likers.hashCode();
		result = result*37 + followers.hashCode();	
		result = result*37 + viewed.hashCode();	
		return result;
	}

	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pairArray("likers").addValues(likers).close();
        jb.pairArray("followers").addValues(followers).close();
        jb.pairArray("viewers").addValues(viewed).close();
        return jb.toJSONElement();
    }


	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }

    public static JournalStatistics fromJson(String jsonString) {
    	JSONObject jsonObject = (JSONObject) JSONParser.parse(jsonString);
		HashSet<Long> followers = new HashSet<Long>(((JSONArray)jsonObject.get("followers")).getAsLongArray());
		HashSet<Long> viewers = new HashSet<Long>(((JSONArray)jsonObject.get("viewers")).getAsLongArray());
		HashSet<Long> likers = new HashSet<Long>(((JSONArray)jsonObject.get("likers")).getAsLongArray());
		return new JournalStatistics(likers, followers, viewers);
	}
}
