package myJournal;

import myJournal.Endpoints;
import myJournal.Routes;

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
		before((request, response) -> {
			System.out.println(request.session(true));
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
		delete(Endpoints.Account, Routes.deleteAccount);
		delete(Endpoints.Journal, Routes.deleteJournal);
		delete(Endpoints.Page, Routes.deletePage);

	}

}
