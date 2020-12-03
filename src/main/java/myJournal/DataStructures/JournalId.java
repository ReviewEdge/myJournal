package myJournal.DataStructures;

import myJournal.DBCommunication;
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

    @Override
    public JSONElement asJsonElement() {
        JSONBuilder jb = JSONBuilder.object();
        jb.pair("type", "journal");
        jb.pair("id", id);
        return jb.toJSONElement();
    }

    @Override
    public String asJson() {
        return asJsonElement().toJSONString();
    }
}
