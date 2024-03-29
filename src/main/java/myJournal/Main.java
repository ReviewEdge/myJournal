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
		port(80);
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
		post(Endpoints.JournalLike, Routes.likeJournal);
		post(Endpoints.PageLike, Routes.likePage);
		post(Endpoints.Account, Routes.addAccount);
		post(Endpoints.Journal, Routes.addJournal);
		post(Endpoints.Page, Routes.addPage);
		post(Endpoints.Session, Routes.createSession);
		put(Endpoints.JournalLike, Routes.unlikeJournal);
		put(Endpoints.PageLike, Routes.unlikePage);
		put(Endpoints.Account, Routes.editAccount);
		put(Endpoints.Journal, Routes.editJournal);
		put(Endpoints.AccountSubscribe, Routes.subscribeAccount);
		put(Endpoints.JournalSubscribe, Routes.subscribeJournal);
		delete(Endpoints.Account, Routes.deleteAccount);
		delete(Endpoints.Journal, Routes.deleteJournal);
		delete(Endpoints.Page, Routes.deletePage);

	}

}
