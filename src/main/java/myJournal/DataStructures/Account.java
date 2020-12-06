
package myJournal.DataStructures;

import myJournal.Intermediaries.FollowableId;
import myJournal.Intermediaries.JournalId;
import myJournal.util.JSON.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a followable account.
 */
public class Account implements Followable, JSONSerializable {
    private AccountData profile;
    private final long id;
    private ArrayList<FollowableId> subscribed;
    private Feed feed;
    private ArrayList<JournalId> journalIds;
    private AccountStatistics stats;


    public Account(long id, AccountData profile, ArrayList<FollowableId> subscribed, Feed feed, ArrayList<JournalId> journalIds, AccountStatistics stats) {
        if(profile == null  || subscribed == null || feed == null || journalIds == null || stats == null)
            throw new IllegalArgumentException();
        this.profile = profile;
        this.id = id;
        this.subscribed = subscribed;
        this.journalIds = journalIds;
        this.stats = stats;
        this.feed = new Feed(subscribed, id);
    }

    public Account(long id, AccountData profile) {
        if(profile == null)
            throw new IllegalArgumentException();
        this.profile = profile;
        this.id = id;
        this.subscribed = new ArrayList<>();
        this.feed = new Feed(this.subscribed, id);
        this.journalIds = new ArrayList<>();
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
     * @return a Hashset of the account's followers
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


    public ArrayList<JournalId> getJournals() {
        return journalIds;
    }

    public void addJournalId(JournalId j) {
        journalIds.add(j);
    }

    public void removeJournal(long id) {
        journalIds.remove(new JournalId(id));
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
        return new Account(id, this.profile, this.subscribed, this.feed, this.journalIds, this.stats);
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
		if (!(o instanceof Account)) {
			return false;
		}
		Account s = (Account) o;
		
		return ((s.id == this.id) && s.profile.equals(this.profile) && s.subscribed.equals(this.subscribed) 
				&& s.stats.equals(this.stats) && s.feed.equals(this.feed) && s.journalIds.equals(this.journalIds));
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + profile.hashCode();
		result = result*37 + (Long.hashCode(id));	
		result = result*37 + subscribed.hashCode();
		result = result*37 + feed.hashCode();
		result = result*37 + journalIds.hashCode();
		result = result*37 + stats.hashCode();
		return result;
	}
    
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("profile", profile);
        jb.pair("id", JSONValue.from(id));
        jb.pairArray("subscribed").add(subscribed).close();
        jb.pairArray("journalIds").add(journalIds).close();
        jb.pair("stats", stats);
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
