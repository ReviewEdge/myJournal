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
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * @return
     */
    public ArrayList<Page> getPages() {
        return pages;
    }


    // TO DO (Implement Options):
    // Stats Data

    /**
     * @return
     */
    public long getLikes() {
        return stats.getLikes();
    }

    /**
     * @return
     */
    public HashSet<Long> getLikers() {
        return stats.getLikers();
    }

    /**
     * @return
     */
    public HashSet<Long> getFollowers() {
        return stats.getFollowers();
    }

    /**
     * @return
     */
    public long getNumFollowers() {
        return stats.getNumFollowers();
    }

    /**
     * @return
     */
    public JournalStatistics getStats() {
        return stats;
    }


    // Options data:

    /**
     * @return
     */
    public boolean isPrivate() {
        return options.isPrivate();
    }

    /**
     * @return
     */
    public boolean hasLikes() {
        return options.hasLikes();
    }

    /**
     * @return
     */
    public boolean hasFollowers() {
        return options.hasFollowers();
    }

    /**
     * @return
     */
    public HashSet<Long> getOwners() {
        return options.getOwners();
    }

    /**
     * @return
     */
    public HashSet<Long> getContributers() {
        return options.getContributers();
    }

    /**
     * @return
     */
    public JournalOptions getOptions() {
        return options;
    }


    // TO DO:

    /**
     *
     */
    public void addPage() {

    }

    //TO DO:

    /**
     * @param requestingId
     * @return
     */
    public Page getLatestPage(long requestingId) {
        return null;
    }

    /**
     * @param requestingId
     * @return
     */
    public ArrayList<Page> getPages(long requestingId) {
        return null;
    }

    /**
     * @return
     */
    public long getNumPages() {
        return 0;
    }


}
