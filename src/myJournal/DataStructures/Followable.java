package myJournal.DataStructures;


import java.util.ArrayList;

public interface Followable {
    public ArrayList<Page> getPages();
    public Page getLatestPage();
}
