package myJournal.Intermediaries;

import myJournal.DBCommunication;
import myJournal.DataStructures.Account;
import myJournal.DataStructures.Page;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONSerializable;
import myJournal.util.JSON.JSONValue;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PageId implements JSONSerializable {
    private final long id;
    public PageId(long id) {
        this.id = id;
    }
    public Page getPage() {
        return DBCommunication.getPage(this.id);
    }
    public long getId() {
        return this.id;
    }
    public static PageId from(Page page) {
        return new PageId(page.getId());
    }
    public static ArrayList<PageId> fromPageArray(ArrayList<Page> ap) {
        return ap.stream().map(PageId::from).collect(Collectors.toCollection(ArrayList::new));
    }
    public static ArrayList<Page> toPageArray(ArrayList<PageId> ap) {
        return ap.stream().map(PageId::getPage).collect(Collectors.toCollection(ArrayList::new));
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
		if (!(o instanceof PageId)) {
			return false;
		}
		PageId s = (PageId) o;
		
		return (this.id == s.id);
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + Long.hashCode(id);
		return result;
	}
    
    @Override
    public JSONElement asJsonElement() {
        return new JSONValue(this.id);
    }

    @Override
    public String asJson() {
        return asJsonElement().toJSONString();
    }
    
    
}
