package myJournal.DataStructures;


import java.util.ArrayList;
import java.util.HashSet;

public interface Followable {
    HashSet<Long> getFollowers();
    ArrayList<Page> getPages(long requestingId);
    Page getLatestPage(long requestingId);
    long getNumFollowers();
    long getNumPages();
}
