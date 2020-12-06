package myJournal.Intermediaries;

import myJournal.DataStructures.Followable;
import myJournal.util.JSON.JSONSerializable;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class FollowableId implements JSONSerializable {

	protected final long id;
    public FollowableId(long id) {
        this.id = id;
    }
    public abstract Followable getFollowable();
    public long getId() {
        return this.id;
    }
    public static <R extends FollowableId> ArrayList<Followable> toFollowableArray(ArrayList<R> af) {
        return af.stream().map(FollowableId::getFollowable).collect(Collectors.toCollection(ArrayList::new));
    }

}
