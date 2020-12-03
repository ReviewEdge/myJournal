package myJournal.DataStructures;

import myJournal.DBCommunication;
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
}
