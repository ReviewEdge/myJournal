package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class Account implements Followable{
    private AccountData profile;
    private final long id;
    private ArrayList<Followable> subscribed;
    private Feed feed;
    private ArrayList<Journal> journals;
    private HashSet<Long> followers;

    public Account(long id, AccountData profile, ArrayList<Followable> subscribed, Feed feed) {
        this.profile = profile;
        this.id = id;
        this.subscribed = subscribed;
        this.feed = new Feed(subscribed);
    }

    public AccountData getProfile() {
        return profile;
    }

    public void setProfile(AccountData profile) {
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Followable> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(ArrayList<Followable> subscribed) {
        this.subscribed = subscribed;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public ArrayList<Page> getPages(long requestingId) {
        return new ArrayList<>();
    }

    public Page getLatestPage(long requestingId) {
        return null;
    }

    public HashSet<Long> getFollowers() {
        return null;
    }

    public long getNumFollowers() {
        return 0;
    }

    public long getNumPages() {
        return 0;
    }
}
