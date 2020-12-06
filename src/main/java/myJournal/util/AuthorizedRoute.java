package myJournal.util;

import myJournal.DataStructures.Account;
import spark.Request;
import spark.Response;

@FunctionalInterface
public interface AuthorizedRoute {
    Object run(Request q, Response r, Account a);
}
