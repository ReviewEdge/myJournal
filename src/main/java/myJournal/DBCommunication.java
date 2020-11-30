package myJournal;

import myJournal.DataStructures.Account;
import myJournal.DataStructures.Journal;
import myJournal.DataStructures.Page;

import java.util.ArrayList;

/**
 * A class for communication with the database.
 */
public class DBCommunication {
    private ArrayList<Account> fakeAccountsTable;
    private ArrayList<Journal> fakeJournalsTable;
    private ArrayList<Page> fakePagesTable;

    public DBCommunication(String DBLocation) {
        fakeAccountsTable = new ArrayList<>();
        fakeJournalsTable = new ArrayList<>();
        fakePagesTable = new ArrayList<>();
    }

    /**
     *
     * @param toAdd The account to add.
     * @return The id of the account in the database.
     */
    public long addAccount(Account toAdd) {
        long id = fakeAccountsTable.size();
        fakeAccountsTable.add(toAdd);
        return id;
    }

    /**
     *
     * @param id The id
     * @return the account, from the database.
     */
    public Account getAccount(long id) {
        return fakeAccountsTable.get((int)id);
    }

    /**
     *
     * @param toAdd The journal to add to database.
     * @return The id of the journal in the database.
     */
    public long addJournal(Journal toAdd) {
        long id = fakeJournalsTable.size();
        fakeJournalsTable.add(toAdd);
        return id;
    }

    /**
     *
     * @param id The id of the journal.
     * @return the journal, from the database.
     */
    public Journal getJournal(long id) {
        return fakeJournalsTable.get((int)id);
    }

    /**
     *
     * @param toAdd The page to add to the database.
     * @return the id of the page in the database.
     */
    public long addPage(Page toAdd) {
        long id = fakePagesTable.size();
        fakePagesTable.add(toAdd);
        return id;
    }
    /**
     *
     * @param id The id of the page.
     * @return the page, from the database.
     */
    public Page getPage(long id) {
        return fakePagesTable.get((int)id);
    }
}
