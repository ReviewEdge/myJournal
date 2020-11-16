package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
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
     * @return
     */
    public AccountData getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(AccountData profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public ArrayList<Followable> getSubscribed() {
        return subscribed;
    }

    /**
     *
     * @param subscribed
     */
    public void setSubscribed(ArrayList<Followable> subscribed) {
        this.subscribed = subscribed;
    }

    /**
     *
     * @return
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     *
     * @param feed
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    /**
     *
     * @param requestingId
     * @return
     */
    public ArrayList<Page> getPages(long requestingId) {
        return new ArrayList<>();
    }

    /**
     *
     * @param requestingId
     * @return
     */
    public Page getLatestPage(long requestingId) {
        return null;
    }

    /**
     *
     * @return
     */
    public HashSet<Long> getFollowers() {
        return stats.getFollowers();
    }

    /**
     *
     * @return
     */
    public long getNumFollowers() {
        return stats.getNumFollowers();
    }

    /**
     *
     * @return
     */
    public long getNumPages() {
        return 0;
    }
}
