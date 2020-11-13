package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Account {
    private AccountData profile;
    private final long id;
    private ArrayList<Followable> subscribed;
    private Feed feed;
    public Account(long id, AccountData profile, ArrayList<Followable> subscribed, Feed feed) {
        this.profile = profile;
        this.id = id;
        this.subscribed = subscribed;
        this.feed = new Feed(subscribed);
    }
}
