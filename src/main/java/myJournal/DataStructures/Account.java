
package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Represents a followable account.
 */
public class Account implements Followable{
    private AccountData profile;
    private final long id;
    private ArrayList<Followable> subscribed;
    private Feed feed;
    private ArrayList<Journal> journals;
    private AccountStatistics stats;


    public Account(long id, AccountData profile, ArrayList<Followable> subscribed, Feed feed) {
        this.profile = profile;
        this.id = id;
        this.subscribed = subscribed;
        this.feed = new Feed(subscribed);
    }

    /**
     *
     * @return the account's profile information
     */
    public AccountData getProfile() {
        return profile;
    }

    /**
     *
     * @param profile set the account's profile information
     */
    public void setProfile(AccountData profile) {
        this.profile = profile;
    }

    /**
     *
     * @return the account's id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return the list of followable objects the account is subscribed to
     */
    public ArrayList<Followable> getSubscribed() {
        return subscribed;
    }

    /**
     * Overwrite the subscribed list
     * @param subscribed the list of followable objects the account should be subscribed to
     */
    public void setSubscribed(ArrayList<Followable> subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * subscribe to a new followable object
     * @param f a followable object
     */
    public void subscribeTo(Followable f) {
        this.subscribed.add(f);
    }

    /**
     *
     * @return the account's personal feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     *
     * @param requestingId the id of the account requesting page
     * @return an arraylist of the pages available to the account
     */
    public ArrayList<Page> getPages(long requestingId) {
        return new ArrayList<>();
    }

    /**
     *
     * @param requestingId the id of the account requesting page
     * @return a page available to the account
     */
    public Page getLatestPage(long requestingId) {
        return null;
    }

    /**
     *
     * @return a hashset of the account's followers
     */
    public HashSet<Long> getFollowers() {
        return stats.getFollowers();
    }

    /**
     *
     * @return the number of followers the account has
     */
    public long getNumFollowers() {
        return stats.getNumFollowers();
    }

    /**
     *
     * @return the number of pages the account has created
     */
    public long getNumPages() {
        return 0;
    }

    /**
     *
     * @param passwordHash the hash of the password to be checked
     * @return whether or not the hashes match
     */
    public boolean checkPasswordHash(String passwordHash) {
        return this.profile.getPasswordHash().equals(passwordHash);
    }

    public Account copyWithId(long id) {
        return new Account(id, this.profile, this.subscribed, this.feed);
    }
}
