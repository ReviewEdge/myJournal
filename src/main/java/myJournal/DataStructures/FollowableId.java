package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class FollowableId {
    protected final long id;
    public FollowableId(long id) {
        this.id = id;
    }
    public abstract Followable getFollowable();
    public long getId() {
        return this.id;
    }
    public static ArrayList<Followable> toFollowableArray(ArrayList<FollowableId> af) {
        return af.stream().map(FollowableId::getFollowable).collect(Collectors.toCollection(ArrayList::new));
    }
}
