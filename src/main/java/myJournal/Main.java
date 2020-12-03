package myJournal;

import myJournal.DataStructures.AccountStatistics;
import myJournal.Endpoints;
import myJournal.Routes;

import java.util.Arrays;
import java.util.HashSet;

import static spark.Spark.*;

/**
 * The main server class, which runs all the code.
 */
public class Main {
	/**
	 * Main server loop.
	 * @param args Command line arguments to the program.
	 */
	public static void main(String[] args) {
		HashSet<Long> followers = new HashSet(Arrays.asList(1,2,3,4));
		AccountStatistics as = new AccountStatistics(followers);
		System.out.println(as.getNumFollowers());
		System.out.println(as.asJson());
		before((request, response) -> {
			request.session(true);
		});
		get("/", (q,a)->{
			return q.session().isNew();
		});
		get(Endpoints.Test, Routes.getTest);
		get(Endpoints.Account, Routes.getAccount);
		get(Endpoints.Journal, Routes.getJournal);
		get(Endpoints.Page, Routes.getPage);
		get(Endpoints.Feed, Routes.getFeed);
		get(Endpoints.FeedNext, Routes.getFeedNext);
		get(Endpoints.AccountPages, Routes.getAccountPages);
		get(Endpoints.JournalPages, Routes.getJournalPages);
		post(Endpoints.Account, Routes.addAccount);
		post(Endpoints.Journal, Routes.addJournal);
		post(Endpoints.Page, Routes.addPage);
		post(Endpoints.Session, Routes.createSession);
		put(Endpoints.Account, Routes.editAccount);
		put(Endpoints.Journal, Routes.editJournal);
		put(Endpoints.AccountSubscribe, Routes.subscribeAccount);
		put(Endpoints.JournalSubscribe, Routes.subscribeJournal);
		delete(Endpoints.Account, Routes.deleteAccount);
		delete(Endpoints.Journal, Routes.deleteJournal);
		delete(Endpoints.Page, Routes.deletePage);

	}

}
