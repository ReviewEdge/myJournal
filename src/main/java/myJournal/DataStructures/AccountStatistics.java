package myJournal.DataStructures;

import myJournal.util.JSON.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Stores statistics about an account
 */
public class AccountStatistics implements JSONSerializable {
    private HashSet<Long> followers;
    
    
    /**
     * @param followers
     */
    public AccountStatistics(HashSet<Long> followers) {
        this.followers = followers;
    }

    public AccountStatistics() {
        this.followers = new HashSet<>();
    }

    /**
     * @return a HashSet of all of the followers
     */
    public HashSet<Long> getFollowers() {
        return this.followers;
    }

    /**
     * @return how many followers the account has
     */
    public long getNumFollowers() {
        return followers.size();
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
		if (!(o instanceof AccountStatistics)) {
			return false;
		}
		AccountStatistics s = (AccountStatistics) o;
		
		return (s.followers.equals(this.followers));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + followers.hashCode();
		return result;
	}
    
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
    	JSONBuilder jb = JSONBuilder.object();
    	jb.pairArray("followers").addValue(followers).close();
        return jb.toJSONElement();
    }
    
	/**
	 * @return the JSON string of the object
	 * @Override
	 */    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
