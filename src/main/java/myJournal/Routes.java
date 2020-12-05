
package myJournal;

import myJournal.DataStructures.*;
import myJournal.util.JSON.JSONArray;
import myJournal.util.JSON.JSONBuilder;
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
import java.util.Scanner;

public class Routes {
    public static final Route getTest = (Request request, Response response) -> {
        return "I DONT CARE";
    };
    public static final Route getAccount = (Request request, Response response) -> {
        String username = request.queryParams("username");
        if(username != null) {
            System.out.println("Accessing account with username " + username);
            System.out.println(DBCommunication.getAccountByUsername(username).asJson());
            return DBCommunication.getAccountByUsername(username).asJson();
        }
        String idString = request.queryParams("id");
        if(idString != null) {
            long id = Long.parseLong(idString);
            System.out.println("Accessing account with id " + id);
            System.out.println(DBCommunication.getAccount(id).asJson());
            return DBCommunication.getAccount(id).asJson();
        }
        System.out.println("No recognized query parameters.");
        if(request.session().attribute("account") != null) {
            request.session().attribute("account", DBCommunication.getAccount(
                    ((Account)request.session().attribute("account")).getId()));
            return ((Account) request.session().attribute("account")).asJson();
        }
        System.out.println("EVERYTHING FAILED");
        return "NONE";
    };
    public static final Route getJournal = (Request request, Response response) -> {
        long id = Long.parseLong(request.queryParams("id"));
        return DBCommunication.getJournal(id).asJson();
    };
    public static final Route getPage = (Request request, Response response) -> {
        long id = Long.parseLong(request.queryParams("id"));
        return DBCommunication.getPage(id).asJson();
    };
    public static final Route getFeed = (Request request, Response response) -> {
        int numPages = Integer.parseInt(request.queryParams("numPages"));
        return JSONBuilder.convertToJSONString(((Account)request.session().attribute("account")).getFeed().getPages(10));
    };
    public static final Route getFeedNext = (Request request, Response response) -> {
        return ((Account)request.session().attribute("account")).getFeed().getPage().asJson();
    };
    public static final Route getAccountPages = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        Account a = DBCommunication.getAccount(id);
        return JSONBuilder.convertToJSONString(a.getPages(((Account)request.session().attribute("account")).getId()));
    };
    public static final Route getJournalPages = (Request request, Response response) -> {
        System.out.println(request.queryParams("id"));
        Long id = Long.parseLong(request.queryParams("id"));
        Journal j = DBCommunication.getJournal(id);
        return JSONBuilder.convertToJSONString(j.getPages(((Account)request.session().attribute("account")).getId()));
    };
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
        }
        catch(ParseException | NullPointerException e) {
            dateOfBirth = null;
        }
        String bio = request.queryParams("bio");
        String livingLocation = request.queryParams("livingLocation");
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        Account a = new Account(0, p);
        DBCommunication.addAccount(a);
        System.out.println("Added account with username " + username);
        response.status(200);
        return "OK";
    };
    public static final Route addJournal = (Request request, Response response) -> {
        Account sessionAccount = request.session().attribute("account");
        String name = request.queryParams("name");
        Boolean isPrivate = Boolean.parseBoolean(request.queryParams("isPrivate"));
        Boolean hasLikes = Boolean.parseBoolean(request.queryParams("hasLikes"));
        Boolean hasFollowers = Boolean.parseBoolean(request.queryParams("hasFollowers"));
        HashSet<Long> owners = new HashSet<>();
        String[] ownersString = request.queryParamsValues("owners");
        owners.add(sessionAccount.getId());
        if(ownersString != null) {
            for (String s : ownersString) {
                owners.add(Long.parseLong(s));
            }
        }
        HashSet<Long> contributers = new HashSet<>();
        String[] contributersString = request.queryParamsValues("contributers");
        if(contributersString != null) {
            for (String s : contributersString) {
                contributers.add(Long.parseLong(s));
            }
        }
        HashSet<Long> viewers = new HashSet<>();
        if(isPrivate) {
            String[] viewersString = request.queryParamsValues("viewers");
            viewers.add(sessionAccount.getId());
            if(viewersString != null) {
                for (String s : viewersString) {
                    viewers.add(Long.parseLong(s));
                }
            }
        }
        ArrayList<Page> pages = new ArrayList<>();
        JournalStatistics stats = new JournalStatistics(new HashSet<>(), new HashSet<>(), new HashSet<>());
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, owners, contributers, viewers);
        Journal j = new Journal(0,name,pages,stats,options);
        DBCommunication.addJournal(j);
        sessionAccount.addJournalId(JournalId.from(j));
        DBCommunication.editAccount(sessionAccount.getId(), sessionAccount);
        return "OK";
    };
    public static final Route addPage = (Request request, Response response) -> {
        long newId = 0;
        Account currentAccount = request.session().attribute("account");
        String newName = request.queryParams("name");
        String content = request.queryParams("content");
        long authorId = Long.parseLong(request.queryParams("authorId"));
        System.out.println(request.queryParams("parentJournalId"));
        long parentJournalId = Long.parseLong(request.queryParams("parentJournalId"));
        Journal parentJournal = DBCommunication.getJournal(parentJournalId);
        Page p = new Page(newId, newName, content, authorId, parentJournal);
        parentJournal.addPage(p, currentAccount.getId());
        System.out.println(parentJournal.asJson());
        DBCommunication.editJournal(parentJournalId, parentJournal);
        return "OK";
    };
    public static final Route createSession = (Request request, Response response) -> {
        int id = Integer.parseInt(request.queryParams("id"));
        String password = request.queryParams("password");
        Account a = DBCommunication.getAccount(id);
        if(a.checkPassword(password)) {
            request.session(true).attribute("created", true);
            request.session().attribute("account", a);
            response.status(200);
            return "OK";
        } else {
            response.status(401);
            return "FAIL";
        }
    };
    public static final Route editAccount = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        if(id != ((Account)request.session().attribute("account")).getId()){
            response.status(401);
            return "FAIL";
        }
        Account toModify = DBCommunication.getAccount(id);
        String firstName = request.queryParamOrDefault("firstName", toModify.getProfile().getFirstName());
        String lastName = request.queryParamOrDefault("lastName", toModify.getProfile().getLastName());
        String username = request.queryParamOrDefault("username", toModify.getProfile().getUsername());
        String password = request.queryParams("password");
        String passwordHash;
        if(password != null) {
            String salt = BCrypt.gensalt(10);
            passwordHash = BCrypt.hashpw(password, salt);
        }
        else {
            passwordHash = toModify.getProfile().getPasswordHash();
        }
        Date accountCreation = toModify.getProfile().getAccountCreation();
        Date dateOfBirth;
        try {
            dateOfBirth = DateFormat.getDateInstance().parse(request.queryParams("dateOfBirth"));
        }
        catch (NullPointerException | ParseException e) {
            dateOfBirth = toModify.getProfile().getDateOfBirth();
        }
        String bio = request.queryParamOrDefault("bio",toModify.getProfile().getBio());
        String livingLocation = request.queryParamOrDefault("livingLocation", toModify.getProfile().getLivingLocation());
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        Account a = new Account(id, p, toModify.getSubscribed(), toModify.getFeed(), toModify.getJournals(), toModify.getStats());
        DBCommunication.editAccount(id, a);
        response.status(200);
        return "OK";
    };
    public static final Route editJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        Account sessionAccount = request.session().attribute("account");
        if(id != ((Account)request.session().attribute("account")).getId()){
            response.status(401);
            return "FAIL";
        }
        Journal toModify = DBCommunication.getJournal(id);
        String name = request.queryParamOrDefault("name", toModify.getName());
        Boolean isPrivate;
        try {
            isPrivate = Boolean.parseBoolean(request.queryParams("isPrivate"));
        }
        catch(NullPointerException n) {
            isPrivate = toModify.isPrivate();
        }
        Boolean hasLikes;
        try {
            hasLikes = Boolean.parseBoolean(request.queryParams("hasLikes"));
        }
        catch(NullPointerException n) {
            hasLikes = toModify.hasLikes();
        }
        Boolean hasFollowers;
        try {
            hasFollowers = Boolean.parseBoolean(request.queryParams("hasFollowers"));
        }
        catch(NullPointerException n) {
            hasFollowers = toModify.hasFollowers();
        }
        String[] ownersString = request.queryParamsValues("owners");
        if(ownersString != null) {
            for (String s : ownersString) {
                toModify.addOwners(Long.parseLong(s), sessionAccount.getId());
            }
        }
        String[] contributersString = request.queryParamsValues("contributers");
        if(contributersString != null) {
            for (String s : contributersString) {
                toModify.addContributer(Long.parseLong(s), sessionAccount.getId());
            }
        }
        HashSet<Long> viewers = new HashSet<>();
        if(isPrivate) {
            String[] viewersString = request.queryParamsValues("viewers");
            viewers.add(sessionAccount.getId());
            if(viewersString != null) {
                for (String s : viewersString) {
                    toModify.addViewer(Long.parseLong(s), sessionAccount.getId());
                }
            }
        }
        ArrayList<Page> pages = toModify.getPages();
        JournalStatistics stats = toModify.getStats();
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, toModify.getOwners(),
                toModify.getContributers(), toModify.getViewers());
        Journal j = new Journal(id,name,pages,stats,options);
        DBCommunication.editJournal(id, j);
        response.status(200);
        return "OK";
    };
    public static final Route subscribeAccount = (Request request, Response response) -> {
        Account subscribingAccount = request.session().attribute("account");
        Long subscribingId = subscribingAccount.getId();
        Long subscribedId = Long.parseLong(request.queryParams("id"));
        Account subscribedAccount = DBCommunication.getAccount(subscribedId);
        subscribedAccount.getFollowers().add(subscribingId);
        subscribingAccount.subscribeTo(AccountId.from(subscribedAccount));
        DBCommunication.editAccount(subscribingId, subscribingAccount);
        DBCommunication.editAccount(subscribedId, subscribedAccount);
        response.status(200);
        return "OK";
    };
    public static final Route subscribeJournal = (Request request, Response response) -> {
        Account subscribingAccount = request.session().attribute("account");
        Long subscribingId = subscribingAccount.getId();
        Long journalId = Long.parseLong(request.queryParams("id"));
        Journal journal = DBCommunication.getJournal(journalId);
        journal.getFollowers().add(subscribingId);
        subscribingAccount.subscribeTo(JournalId.from(journal));
        DBCommunication.editJournal(journalId, journal);
        DBCommunication.editAccount(subscribingId,subscribingAccount);
        response.status(200);
        return "OK";
    };
    public static final Route deleteAccount = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        DBCommunication.deleteAccount(id);
        return "OK";
    };
    public static final Route deleteJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        DBCommunication.deleteJournal(id);
        return "OK";
    };
    public static final Route deletePage = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        DBCommunication.deletePage(id);
        return "OK";
    };
}
