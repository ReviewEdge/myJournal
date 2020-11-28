package myJournal.DataStructures;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classes that implement followable can have their pages shown to follower classes.
 */
public interface Followable {
    /**
     *
     * @return the number of followers a followable object has.
     */
    HashSet<Long> getFollowers();

    /**
     * Based on the permissions of the requesting account, gives a list of pages available to that account.
     * @param requestingId The id of the account requesting pages from the followable object.
     * @return An arraylist of pages accessible to the user from the followable object.
     */
    ArrayList<Page> getPages(long requestingId);

    /**
     * Based on the permissions of the requesting account, gives the latest page avaiable to that account.
     * @param requestingId The id of the account requesting pages from the followable object.
     * @return The latest page accessible to the user.
     */
    Page getLatestPage(long requestingId);

    /**
     *
     * @return the number of followers the followable object has.
     */
    long getNumFollowers();

    /**
     *
     * @return the number of pages the followable object has.
     */
    long getNumPages();
}
