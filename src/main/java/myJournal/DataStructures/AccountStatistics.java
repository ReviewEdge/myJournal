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


    public JSONElement asJsonElement() {
    	JSONBuilder jb = JSONBuilder.object();
    	jb.pairArray("followers").add(followers).close();
        return jb.toJSONElement();
    }


    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
