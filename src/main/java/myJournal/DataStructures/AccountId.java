package myJournal.DataStructures;

import myJournal.DBCommunication;

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
}
