package myJournal.DataStructures;

import java.util.HashSet;

/**
 * Stores statistics about an account
 */
public class AccountStatistics {
    private HashSet<Long> followers;
    
    
    /**
     * @param followers
     */
    public AccountStatistics(HashSet<Long> followers) {
  
    }

    /**
     * @return a hashset of all of the followers
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
}
