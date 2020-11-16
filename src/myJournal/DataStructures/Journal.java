package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public class Journal implements Followable {

    private long id;
    private String name;
    private ArrayList<Page> pages;
    private HashSet<Long> owners;
    private HashSet<Long> contributers;
    private JournalStatistics stats;
    private JournalOptions options;

    /**
     * @param id
     * @param name
     * @param pages
     * @param owners
     * @param contributers
     * @param stats
     * @param options
     */
    public Journal(long id, String name, ArrayList<Page> pages, HashSet<Long> owners, HashSet<Long> contributers, JournalStatistics stats, JournalOptions options) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.owners = owners;
        this.contributers = contributers;
        this.stats = stats;
        this.options = options;
    }

    /**
     * @return id of journal as long
     */
    public long getId() {
        return id;
    }

    /**
     * @return arrayList of all of the pages in the journal
     */
    public ArrayList<Page> getPages() {
        return pages;
    }


    // TO DO (Implement Options):
    // Stats Data

    /**
     * @return how many likes the journal has
     */
    public long getLikes() {
        return stats.getLikes();
    }

    /**
     * @return a hashset of all of the journal's likers
     */
    public HashSet<Long> getLikers() {
        return stats.getLikers();
    }

    /**
     * @return a hashset of all of the journal's followers
     */
    public HashSet<Long> getFollowers() {
        return stats.getFollowers();
    }

    /**
     * @return how many followers a journal has
     */
    public long getNumFollowers() {
        return stats.getNumFollowers();
    }

    /**
     * @return the statistics object for the journal
     */
    public JournalStatistics getStats() {
        return stats;
    }


    // Options data:

    /**
     * @return if the journal is private
     */
    public boolean isPrivate() {
        return options.isPrivate();
    }

    /**
     * @return if the journal has likes
     */
    public boolean hasLikes() {
        return options.hasLikes();
    }

    /**
     * @return if the journal has followers
     */
    public boolean hasFollowers() {
        return options.hasFollowers();
    }

    /**
     * @return a hashset of the owners of the journal
     */
    public HashSet<Long> getOwners() {
        return options.getOwners();
    }

    /**
     * @return a hashset of the contributers to the journal
     */
    public HashSet<Long> getContributers() {
        return options.getContributers();
    }

    /**
     * @return the options object for the journal
     */
    public JournalOptions getOptions() {
        return options;
    }


    // TO DO:

    /**
     * Adds a new page to the journal.
     * @param newPage
     */
    public void addPage(Page newPage) {

    }

    //TO DO:

    /**
     * @param requestingId
     * @return the latest page in the journal
     */
    public Page getLatestPage(long requestingId) {
        return null;
    }

    /**
     * @param requestingId
     * @return the id of all of the pages in the journal
     */
    public ArrayList<Page> getPages(long requestingId) {
        return null;
    }

    /**
     * @return how many pages are in the journal
     */
    public long getNumPages() {
        return 0;
    }


}
