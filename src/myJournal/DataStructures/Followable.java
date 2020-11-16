package myJournal.DataStructures;


import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface Followable {
    /**
     *
     * @return
     */
    HashSet<Long> getFollowers();

    /**
     *
     * @param requestingId
     * @return
     */
    ArrayList<Page> getPages(long requestingId);

    /**
     *
     * @param requestingId
     * @return
     */
    Page getLatestPage(long requestingId);

    /**
     *
     * @return
     */
    long getNumFollowers();

    /**
     *
     * @return
     */
    long getNumPages();
}
