package myJournal.DataStructures;

import myJournal.DBCommunication;
import myJournal.util.JSON.JSONBuilder;
import myJournal.util.JSON.JSONElement;

public class AccountId extends FollowableId{
    public AccountId(long id) {
        super(id);
    }

    public static AccountId from(Account a) {
        return new AccountId(a.getId());
    }

    @Override
    public Followable getFollowable() {
        return DBCommunication.getAccount(this.id);
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
		if (!(o instanceof AccountId)) {
			return false;
		}
		AccountId s = (AccountId) o;
		
		return (s.id == this.id);
	}
	
	/**
	 * @return the HashCode of the object
	 * @Override
	 */
	public int hashCode() {
		int result = 17;
		result = result*37 + (Long.hashCode(id));
		return result;
	}
    
	/**
	 * @return the object as a JSONElement
	 * @Override
	 */
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("type", "account");
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
