
package myJournal;

import myJournal.DataStructures.*;
import myJournal.Intermediaries.AccountId;
import myJournal.Intermediaries.JournalId;
import myJournal.Intermediaries.PageId;
import myJournal.util.AuthorizedRoute;
import myJournal.util.JSON.JSONBuilder;
import myJournal.util.UnauthenticatedSessionException;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import spark.Route;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Routes {
    public static final Route getTest = (Request request, Response response) -> "I DONT CARE";
    public static final Route getAccount = (Request request, Response response) -> {
        String username = request.queryParams("username");
        if (username != null) {
            System.out.println("Accessing account with username " + username);
            System.out.println(DBCommunication.getAccountByUsername(username).asJson());
            return DBCommunication.getAccountByUsername(username).asJson();
        }
        String idString = request.queryParams("id");
        if (idString != null) {
            long id = Long.parseLong(idString);
            System.out.println("Accessing account with id " + id);
            System.out.println(DBCommunication.getAccount(id).asJson());
            return DBCommunication.getAccount(id).asJson();
        }
        System.out.println("No recognized query parameters.");
        if (request.session().attribute("account") != null) {
            request.session().attribute("account", DBCommunication.getAccount(
                    ((Account) request.session().attribute("account")).getId()));
            return ((Account) request.session().attribute("account")).asJson();
        }
        System.out.println("EVERYTHING FAILED");
        return "NONE";
    };
    static final AuthorizedRoute getJournalAuthorized = (request, response, account) -> {
        try {
            long requestingId = getSessionAccount(request).getId();
            long id = Long.parseLong(request.queryParams("id"));
            return DBCommunication.getJournal(id).authorized(account.getId()).authorized(requestingId).asJson();
        } catch (IllegalAccessAttempt e) {
            response.status(403);
            return "FORBIDDEN";
        }
    };
    public static final Route getJournal = (Request request, Response response) ->
            runAuthorized(request, response, getJournalAuthorized);
    static final AuthorizedRoute getPageAuthorized = (request, response, account) -> {
        try {
            long id = Long.parseLong(request.queryParams("id"));
            return DBCommunication.getPage(id).authorized(account.getId()).asJson();
        } catch (IllegalAccessAttempt e) {
            response.status(403);
            return "FORBIDDEN";
        }
    };
    public static final Route getPage = (Request request, Response response) -> runAuthorized(request, response, getPageAuthorized);
    static final AuthorizedRoute getFeedAuthorized = (r, q, a) -> {
        int numPages = Integer.parseInt(r.queryParamOrDefault("numPages", "10"));
        return JSONBuilder.convertToJSONString(a.getFeed().getPages(numPages));
    };
    public static final Route getFeed = (Request request, Response response) ->
            runAuthorized(request, response, getFeedAuthorized, true);
    public static final Route getFeedNext = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, a) -> a.getFeed().getPage().asJson(), true);
    public static final Route getAccountPages = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, a) -> {
                Long id = Long.parseLong(r.queryParams("id"));
                Account a2 = DBCommunication.getAccount(id);
                return JSONBuilder.convertToJSONString(a2.getPages(a.getId()));
            }, true);
    public static final Route getJournalPages = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, a) -> {
                Long id = Long.parseLong(r.queryParams("id"));
                Journal j = DBCommunication.getJournal(id);
                return JSONBuilder.convertToJSONString(j.getPages(a.getId()));
            }, true);
    public static final Route addAccount = (Request request, Response response) -> {
        String firstName = request.queryParams("firstName");
        String lastName = request.queryParams("lastName");
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        Date accountCreation = new Date();
        Date dateOfBirth;
        try {
            dateOfBirth = (new SimpleDateFormat("yyyy-MM-dd")).parse(request.queryParams("dateOfBirth"));
        } catch (ParseException | NullPointerException e) {
            dateOfBirth = null;
        }
        String bio = request.queryParams("bio");
        String livingLocation = request.queryParams("livingLocation");
        AccountData p = new AccountData(firstName, lastName, accountCreation, dateOfBirth, bio, livingLocation);
        Account a = new Account(0, username, passwordHash, p);
        DBCommunication.addAccount(a);
        System.out.println("Added account with username " + username);
        response.status(200);
        return "OK";
    };
    static final AuthorizedRoute addJournalAuthorized = (request, response, account) -> {
        String name = request.queryParams("name");
        Boolean isPrivate = Boolean.parseBoolean(request.queryParams("isPrivate"));
        Boolean hasLikes = Boolean.parseBoolean(request.queryParams("hasLikes"));
        Boolean hasFollowers = Boolean.parseBoolean(request.queryParams("hasFollowers"));
        HashSet<Long> owners = new HashSet<>();
        String[] ownersString = request.queryParamsValues("owners");
        owners.add(account.getId());
        if (ownersString != null) {
            for (String s : ownersString) {
                owners.add(Long.parseLong(s));
            }
        }
        HashSet<Long> contributers = new HashSet<>();
        String[] contributersString = request.queryParamsValues("contributers");
        if (contributersString != null) {
            for (String s : contributersString) {
                contributers.add(Long.parseLong(s));
            }
        }
        HashSet<Long> viewers = new HashSet<>();
        if (isPrivate) {
            String[] viewersString = request.queryParamsValues("viewers");
            viewers.add(account.getId());
            if (viewersString != null) {
                for (String s : viewersString) {
                    viewers.add(Long.parseLong(s));
                }
            }
        }
        ArrayList<PageId> pages = new ArrayList<>();
        JournalStatistics stats = new JournalStatistics(new HashSet<>(), new HashSet<>(), new HashSet<>());
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, owners, contributers, viewers);
        Journal j = new Journal(0, name, pages, stats, options);
        long jId = DBCommunication.addJournal(j);
        account.addJournalId(new JournalId(jId));
        DBCommunication.editAccount(account.getId(), account);
        return "OK";
    };
    public static final Route addJournal = (Request request, Response response) ->
            runAuthorized(request, response, addJournalAuthorized);
    static final AuthorizedRoute addPageAuthorized = (request, response, account) -> {
        String newName = request.queryParams("name");
        String content = request.queryParams("content");
        long authorId = Long.parseLong(request.queryParams("authorId"));
        System.out.println(request.queryParams("parentJournalId"));
        long parentJournalId = Long.parseLong(request.queryParams("parentJournalId"));
        Journal parentJournal = DBCommunication.getJournal(parentJournalId);
        Page p = new Page(0, newName, content, authorId, parentJournal);
        PageId pageId = new PageId(DBCommunication.addPage(p));
        System.out.println(account.getId());
        parentJournal.addPage(pageId, account.getId());
        System.out.println(parentJournal.asJson());
        DBCommunication.editJournal(parentJournalId, parentJournal);
        return "OK";
    };
    public static final Route addPage = (Request request, Response response) ->
            runAuthorized(request, response, addPageAuthorized, true);
    public static final Route createSession = (Request request, Response response) -> {
        int id = Integer.parseInt(request.queryParams("id"));
        String password = request.queryParams("password");
        Account a = DBCommunication.getAccount(id);
        if (a.checkPassword(password)) {
            request.session(true).attribute("created", true);
            request.session().attribute("account", a);
            response.status(200);
            return "OK";
        } else {
            response.status(403);
            return "FAIL";
        }
    };
    static final AuthorizedRoute editAccountAuthorized = (request, response, account) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        if (id != account.getId()) {
            response.status(403);
            return "FAIL";
        }
        Account toModify = DBCommunication.getAccount(id);
        String firstName = request.queryParamOrDefault("firstName", toModify.getProfile().getFirstName());
        String lastName = request.queryParamOrDefault("lastName", toModify.getProfile().getLastName());
        String username = request.queryParamOrDefault("username", toModify.getUsername());
        String password = request.queryParams("password");
        String passwordHash;
        if (password != null) {
            String salt = BCrypt.gensalt(10);
            passwordHash = BCrypt.hashpw(password, salt);
        } else {
            passwordHash = toModify.getPasswordHash();
        }
        Date accountCreation = toModify.getProfile().getAccountCreation();
        Date dateOfBirth;
        try {
            dateOfBirth = DateFormat.getDateInstance().parse(request.queryParams("dateOfBirth"));
        } catch (NullPointerException | ParseException e) {
            dateOfBirth = toModify.getProfile().getDateOfBirth();
        }
        String bio = request.queryParamOrDefault("bio", toModify.getProfile().getBio());
        String livingLocation = request.queryParamOrDefault("livingLocation", toModify.getProfile().getLivingLocation());
        AccountData p = new AccountData(username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        Account a = new Account(id, firstName, lastName, p, toModify.getSubscribed(), toModify.getFeed(), toModify.getJournals(), toModify.getStats());
        DBCommunication.editAccount(id, a);
        response.status(200);
        return "OK";
    };
    public static final Route editAccount = (Request request, Response response) ->
            runAuthorized(request, response, editAccountAuthorized, true);
    static final AuthorizedRoute editJournalAuthorized = (request, response, account) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        if (id != account.getId()) {
            response.status(403);
            return "FAIL";
        }
        Journal toModify = DBCommunication.getJournal(id);
        String name = request.queryParamOrDefault("name", toModify.getName());
        Boolean isPrivate;
        try {
            isPrivate = Boolean.parseBoolean(request.queryParams("isPrivate"));
        } catch (NullPointerException n) {
            isPrivate = toModify.isPrivate();
        }
        Boolean hasLikes;
        try {
            hasLikes = Boolean.parseBoolean(request.queryParams("hasLikes"));
        } catch (NullPointerException n) {
            hasLikes = toModify.hasLikes();
        }
        Boolean hasFollowers;
        try {
            hasFollowers = Boolean.parseBoolean(request.queryParams("hasFollowers"));
        } catch (NullPointerException n) {
            hasFollowers = toModify.hasFollowers();
        }
        String[] ownersString = request.queryParamsValues("owners");
        if (ownersString != null) {
            for (String s : ownersString) {
                long newId = Long.parseLong(s);
                if(newId > 0) {
                    toModify.addOwners(newId, account.getId());
                } else {
                    newId *= -1;
                    toModify.removeOwners(newId, account.getId());
                }
            }
        }
        String[] contributersString = request.queryParamsValues("contributers");
        if (contributersString != null) {
            for (String s : contributersString) {
                long newId = Long.parseLong(s);
                if(newId > 0) {
                    toModify.addContributer(newId, account.getId());
                } else {
                    newId *= -1;
                    toModify.removeContributer(newId, account.getId());
                }
            }
        }
        HashSet<Long> viewers = new HashSet<>();
        if (isPrivate) {
            String[] viewersString = request.queryParamsValues("viewers");
            viewers.add(account.getId());
            if (viewersString != null) {
                for (String s : viewersString) {
                    long newId = Long.parseLong(s);
                    if(newId > 0) {
                        toModify.addViewer(newId, account.getId());
                    } else {
                        newId *= -1;
                        toModify.removeViewer(newId, account.getId());
                    }
                }
            }
        }
        ArrayList<PageId> pageIds = toModify.getPageIds();
        JournalStatistics stats = toModify.getStats();
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, toModify.getOwners(),
                toModify.getContributers(), toModify.getViewers());
        Journal j = new Journal(id, name, pageIds, stats, options);
        DBCommunication.editJournal(id, j);
        response.status(200);
        return "OK";
    };
    public static final Route editJournal = (Request request, Response response) ->
            runAuthorized(request, response, editJournalAuthorized, true);
    public static final Route subscribeAccount = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, account) -> {
                Long subscribingId = account.getId();
                Long subscribedId = Long.parseLong(r.queryParams("id"));
                Account subscribedAccount = DBCommunication.getAccount(subscribedId);
                subscribedAccount.getFollowers().add(subscribingId);
                account.subscribeTo(AccountId.from(subscribedAccount));
                DBCommunication.editAccount(subscribingId, account);
                DBCommunication.editAccount(subscribedId, subscribedAccount);
                q.status(200);
                return "OK";
            }, true);
    public static final Route subscribeJournal = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, account) -> {
                Long subscribingId = account.getId();
                Long journalId = Long.parseLong(r.queryParams("id"));
                Journal journal = DBCommunication.getJournal(journalId);
                journal.getFollowers().add(subscribingId);
                account.subscribeTo(JournalId.from(journal));
                DBCommunication.editJournal(journalId, journal);
                DBCommunication.editAccount(subscribingId, account);
                q.status(200);
                return "OK";
            }, true);
    public static final Route deleteAccount = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, a) -> {
                Long id = Long.parseLong(r.queryParams("id"));
                if(a.getId() == id) {
                    DBCommunication.deleteAccount(id);
                    return "OK";
                }
                else {
                    q.status(403);
                    return "UNAUTHORIZED";
                }
            }, true);
    public static final Route deleteJournal = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, a) -> {
                Long id = Long.parseLong(r.queryParams("id"));
                Journal toDelete = DBCommunication.getJournal(id);
                if(toDelete.isOwner(a.getId())) {
                    DBCommunication.deleteJournal(id);
                    a.removeJournal(id);
                    DBCommunication.editAccount(a.getId(), a);
                    return "OK";
                }
                else {
                    q.status(403);
                    return "UNAUTHORIZED";
                }
            }, true);
    public static final Route deletePage = (Request request, Response response) ->
            runAuthorized(request, response, (r, q, a) -> {
                Long id = Long.parseLong(r.queryParams("id"));
                Page toDelete = DBCommunication.getPage(id);
                if(toDelete.getParentJournal().isOwner(a.getId())) {
                    Journal j = toDelete.getParentJournal();
                    j.removePage(toDelete, a.getId());
                    DBCommunication.deletePage(id);
                    DBCommunication.editJournal(j.getId(), j);
                    return "OK";
                }
                else {
                    q.status(403);
                    return "UNAUTHORIZED";
                }
            }, true);
    public static final AuthorizedRoute likePageAuthorized = (request, response, account) -> {
        long likingId = account.getId();
        try {
            long pageId = Long.parseLong(request.queryParams("id"));
            Page toLike = DBCommunication.getPage(pageId);
            toLike.addLiker(likingId);
            DBCommunication.editPage(toLike.getId(), toLike);
            return "OK";
        }
        catch(NullPointerException | NumberFormatException e) {
            response.status(401);
            return "NO ID";
        }
    };
    public static final Route likePage = (Request request, Response response) ->
            runAuthorized(request, response, likePageAuthorized, true);

    public static final AuthorizedRoute likeJournalAuthorized = (request, response, account) -> {
        long likingId = account.getId();
        try {
            long journalId = Long.parseLong(request.queryParams("id"));
            Journal toLike = DBCommunication.getJournal(journalId);
            toLike.addLiker(likingId);
            DBCommunication.editJournal(toLike.getId(), toLike);
            return "OK";
        }
        catch(NullPointerException | NumberFormatException e) {
            response.status(401);
            return "NO ID";
        }
    };
    public static final Route likeJournal = (Request request, Response response) ->
            runAuthorized(request, response, likeJournalAuthorized, true);

    static final AuthorizedRoute unlikePageAuthorized = (request, response, account) -> {
        long likingId = account.getId();
        try {
            long pageId = Long.parseLong(request.queryParams("id"));
            Page toLike = DBCommunication.getPage(pageId);
            toLike.removeLiker(likingId);
            DBCommunication.editPage(toLike.getId(), toLike);
            return "OK";
        }
        catch(NullPointerException | NumberFormatException e) {
            response.status(401);
            return "NO ID";
        }
    };
    public static final Route unlikePage = (Request request, Response response) ->
            runAuthorized(request, response, unlikePageAuthorized, true);

    static final AuthorizedRoute unlikeJournalAuthorized = (request, response, account) -> {
        long likingId = account.getId();
        try {
            long journalId = Long.parseLong(request.queryParams("id"));
            Journal toLike = DBCommunication.getJournal(journalId);
            toLike.removeLiker(likingId);
            DBCommunication.editJournal(toLike.getId(), toLike);
            return "OK";
        }
        catch(NullPointerException | NumberFormatException e) {
            response.status(401);
            return "NO ID";
        }
    };
    public static final Route unlikeJournal = (Request request, Response response) ->
            runAuthorized(request, response, unlikeJournalAuthorized, true);

    public static void ensureAuthorized(Request r) {
        if (r.session().isNew() || !(r.session().attribute("account") instanceof Account))
            throw new UnauthenticatedSessionException();
    }

    public static Account getSessionAccount(Request r) {
        return r.session().attribute("account");
    }

    public static Object runAuthorized(Request request, Response response, AuthorizedRoute route) {
        return runAuthorized(request, response, route, false);
    }

    public static Object runAuthorized(Request request, Response response, AuthorizedRoute route, boolean failOnUnauthorized) {
        try {
            ensureAuthorized(request);
            Account a = getSessionAccount(request);
            return route.run(request, response, a);
        } catch (UnauthenticatedSessionException e) {
            if (!failOnUnauthorized) {
                Account n = new PublicAccount();
                return route.run(request, response, n);
            } else {
                response.status(403);
                return "FORBIDDEN";
            }
        }
    }
}
