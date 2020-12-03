
package myJournal.DataStructures;

import myJournal.util.JSON.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Represents a followable account.
 */
public class Account implements Followable, JSONSerializable {
    private AccountData profile;
    private final long id;
    private ArrayList<FollowableId> subscribed;
    private Feed feed;
    private ArrayList<Journal> journals;
    private AccountStatistics stats;


    public Account(long id, AccountData profile, ArrayList<FollowableId> subscribed, Feed feed, ArrayList<Journal> journals, AccountStatistics stats) {
        this.profile = profile;
        this.id = id;
        this.subscribed = subscribed;
        this.journals = journals;
        this.stats = stats;
        this.feed = new Feed(subscribed);
    }

    public Account(long id, AccountData profile) {
        this.profile = profile;
        this.id = id;
        this.subscribed = new ArrayList<>();
        this.feed = new Feed(this.subscribed);
        this.journals = new ArrayList<>();
        this.stats = new AccountStatistics();
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
    public ArrayList<FollowableId> getSubscribed() {
        return subscribed;
    }

    /**
     * Overwrite the subscribed list
     * @param subscribed the list of followable objects the account should be subscribed to
     */
    public void setSubscribed(ArrayList<FollowableId> subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * subscribe to a new followable object
     * @param f a followable object
     */
    public void subscribeTo(FollowableId f) {
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


    public ArrayList<Journal> getJournals() {
        return journals;
    }

    public void addJournal(Journal j) {
        journals.add(j);
    }

    public AccountStatistics getStats() {
        return stats;
    }


    /**
     *
     * @param password the password to be checked
     * @return whether or not the hashes match
     */
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.profile.getPasswordHash());
    }

    public Account copyWithId(long id) {
        return new Account(id, this.profile, this.subscribed, this.feed, this.journals, this.stats);
    }

    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("profile", profile.asJsonElement());
        jb.pair("id", JSONValue.from(id));
        jb.pairArray("subscribed").add(FollowableId.toFollowableArray(subscribed)).close();
        jb.pair("feed", feed);
        jb.pairArray("journals").add(journals).close();
        jb.pair("stats", stats);
        return jb.toJSONElement();
    }

    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
