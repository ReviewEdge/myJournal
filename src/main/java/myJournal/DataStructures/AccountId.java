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

    @Override
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("type", "account");
        jb.pair("id", id);
        return jb.toJSONElement();
    }

    @Override
    public String asJson() {
        return asJsonElement().toJSONString();
    }
    
}
