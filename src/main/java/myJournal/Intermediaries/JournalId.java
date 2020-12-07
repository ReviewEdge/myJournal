package myJournal.Intermediaries;

import myJournal.DBCommunication;
import myJournal.DataStructures.Account;
import myJournal.DataStructures.Followable;
import myJournal.DataStructures.Journal;
import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;

public class JournalId extends FollowableId{

    public JournalId(long id) {
        super(id);
    }

    public static JournalId from(Journal j) {
        return new JournalId(j.getId());
    }

    @Override
    public Followable getFollowable() {
        return DBCommunication.getJournal(this.id);
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
		if (!(o instanceof JournalId)) {
			return false;
		}
		JournalId s = (JournalId) o;
		
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
    
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("type", "journal");
        jb.pair("id", id);
        return jb.toJSONElement();
    }

	/**
	 * @return the JSON string of the object
	 * @Override
	 */
    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
