package myJournal;

import myJournal.DataStructures.Account;
import myJournal.DataStructures.Journal;
import myJournal.DataStructures.Page;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A class for communication with the database.
 */
public class DBCommunication {
    private static ArrayList<Account> fakeAccountsTable = new ArrayList<>();
    private static ArrayList<Journal> fakeJournalsTable = new ArrayList<>();
    private static ArrayList<Page> fakePagesTable = new ArrayList<>();

    /**
     *
     * @param toAdd The account to add.
     * @return The id of the account in the database.
     */
    public static long addAccount(Account toAdd) {
        long id = fakeAccountsTable.size();
        fakeAccountsTable.add(toAdd.copyWithId(id));
        return id;
    }

    /**
     *
     * @param id The id
     * @return the account, from the database.
     */
    public static Account getAccount(long id) {
        return fakeAccountsTable.get((int)id);
    }

    public static Account getAccountByUsername(String username) {
        for (Account account : fakeAccountsTable) {
            if (account.getProfile().getUsername().equals(username)) {
                return account;
            }
        }
        throw new NoSuchElementException();
    }

    public static void editAccount(long id, Account newAccount) {
        fakeAccountsTable.set((int)id, newAccount);
    }

    public static void deleteAccount(long id) {
        fakeAccountsTable.remove((int)id);
    }

    /**
     *
     * @param toAdd The journal to add to database.
     * @return The id of the journal in the database.
     */
    public static long addJournal(Journal toAdd) {
        long id = fakeJournalsTable.size();
        fakeJournalsTable.add(toAdd.copyWithId(id));
        return id;
    }

    /**
     *
     * @param id The id of the journal.
     * @return the journal, from the database.
     */
    public static Journal getJournal(long id) {
        return fakeJournalsTable.get((int)id);
    }

    public static void editJournal(long id, Journal newJournal) {
        fakeJournalsTable.set((int)id, newJournal);
    }

    public static void deleteJournal(long id) {
        fakeJournalsTable.remove((int)id);
    }

    /**
     *
     * @param toAdd The page to add to the database.
     * @return the id of the page in the database.
     */
    public static long addPage(Page toAdd) {
        long id = fakePagesTable.size();
        fakePagesTable.add(toAdd.copyWithId(id));
        return id;
    }
    /**
     *
     * @param id The id of the page.
     * @return the page, from the database.
     */
    public static Page getPage(long id) {
        return fakePagesTable.get((int)id);
    }

    public static void editPage(long id, Page newPage) {
        fakePagesTable.set((int)id, newPage);
    }

    public static void deletePage(long id) {
        fakePagesTable.remove((int)id);
    }

}
