package main.java.myJournal;

import main.java.myJournal.DataStructures.Account;
import main.java.myJournal.DataStructures.Journal;
import main.java.myJournal.DataStructures.Page;

/**
 * A class for communication with the database.
 */
public class DBCommunication {
    public DBCommunication(String DBLocation) {

    }

    /**
     *
     * @param toAdd The account to add.
     * @return The id of the account in the database.
     */
    public long addAccount(Account toAdd) {
        return 0;
    }

    /**
     *
     * @param id The id
     * @return the account, from the database.
     */
    public Account getAccount(long id) {
        return null;
    }

    /**
     *
     * @param toAdd The journal to add to database.
     * @return The id of the journal in the database.
     */
    public long addJournal(Journal toAdd) {
        return 0;
    }

    /**
     *
     * @param id The id of the journal.
     * @return the journal, from the database.
     */
    public Journal getJournal(long id) {
        return null;
    }

    /**
     *
     * @param toAdd The page to add to the database.
     * @return the id of the page in the database.
     */
    public long addPage(Page toAdd) {
        return 0;
    }
    /**
     *
     * @param id The id of the page.
     * @return the page, from the database.
     */
    public Page getPage(long id) {
        return null;
    }
}
