package myJournal.DataStructures;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

public class PublicAccount extends Account{

    public PublicAccount() {
        super(-1, new AccountData(null, null, null, null, null, null, null, null));
    }
}
