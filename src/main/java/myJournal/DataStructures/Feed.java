package myJournal.DataStructures;

import myJournal.Intermediaries.FollowableId;
import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;

import java.util.*;

/**
 * A class that creates a set of pages.
 */
public class Feed {
    private static final int MAX_PAGES = 15;
    private final HashSet<FollowableId> subscribed;
    private LinkedList<Page> pages;
    private List<Page> consumed;
    private long accountId;

    public Feed(HashSet<FollowableId> subscribed, long accountId) {
        this.subscribed = subscribed;
        this.accountId = accountId;
        this.consumed = new LinkedList<>();
        pages = new LinkedList<>();
        ArrayList<Queue<Page>> allPages = new ArrayList<>();
        for (FollowableId id : subscribed) {
            Followable f = id.getFollowable();
            Queue<Page> fPages = new LinkedList<>();
            fPages.addAll(f.getPages(accountId));
            allPages.add(fPages);
        }
        boolean sufficientAdded = false;
        int numLeft = MAX_PAGES;
        while(!sufficientAdded) {
            boolean allNull = true;
            for(Queue<Page> pageQueue : allPages) {
                try {
                    pages.add(pageQueue.remove());
                    numLeft--;
                    allNull = false;
                }
                catch(NoSuchElementException n) {

                }
            }
            numLeft--;
            if(allNull || numLeft <= 0) sufficientAdded = true;
        }
    }

    /**
     * Repopulate the internal page list.
     */
    public void refreshFeed() {
        pages = new LinkedList<>();
        ArrayList<Queue<Page>> allPages = new ArrayList<>();
        for (FollowableId id : subscribed) {
            Followable f = id.getFollowable();
            Queue<Page> fPages = new LinkedList<>();
            fPages.addAll(f.getPages(accountId));
            allPages.add(fPages);
        }
        boolean sufficientAdded = false;
        int numLeft = MAX_PAGES;
        while(!sufficientAdded) {
            boolean allNull = true;
            for(Queue<Page> pageQueue : allPages) {
                try {
                    Page nextPage = pageQueue.remove();
                    if(!consumed.contains(nextPage)) {
                        pages.add(nextPage);
                        numLeft--;
                    }
                    allNull = false;
                }
                catch(NoSuchElementException n) {

                }
            }
            if(allNull || numLeft <= 0) sufficientAdded = true;
        }
    }

    /**
     *
     * @return the next page for the user to read
     */
    public Page getPage() {
        try {
            Page nextPage = pages.remove();
            consumed.add(nextPage);
            return nextPage;
        }
        catch (NoSuchElementException e) {
            refreshFeed();
            if(pages.size() > 0) {
                return getPage();
            }
            else throw new NoSuchElementException();
        }
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
		if (!(o instanceof Feed)) {
			return false;
		}
		Feed s = (Feed) o;

		return (s.pages.equals(this.pages));
	}

	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + pages.hashCode();
		return result;
	}

	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pairArray("pages").add(pages).close();
        return jb.toJSONElement();
    }

    public ArrayList<Page> getPages(int num) {
        ArrayList<Page> out = new ArrayList<>();
        for (int i = num; i > 0; i--) {
            try {
                out.add(getPage());
            }
            catch(NoSuchElementException n) {
                break;
            }
        }
        return out;
    }
}
