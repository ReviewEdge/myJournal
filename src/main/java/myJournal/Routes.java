
package myJournal;

import myJournal.DataStructures.*;
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
    public static Route getTest = (Request request, Response response) -> {
        return "I DONT CARE";
    };
    public static Route getAccount = (Request request, Response response) -> {
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
        if(request.session().attribute("account") != null) {
            request.session().attribute("account", DBCommunication.getAccount(
                    ((Account)request.session().attribute("account")).getId()));
            return request.session().attribute("account");
        }
        return "NONE";
    };
    public static Route getJournal = (Request request, Response response) -> {
        long id = Long.parseLong(request.queryParams("id"));
        return DBCommunication.getJournal(id).asJson();
    };
    public static Route getPage = (Request request, Response response) -> {
        long id = Long.parseLong(request.queryParams("id"));
        return DBCommunication.getPage(id).asJson();
    };
    public static Route getFeed = (Request request, Response response) -> {
        return ((Account)request.session().attribute("account")).getFeed().asJson();
    };
    public static Route getFeedNext = (Request request, Response response) -> {
        return ((Account)request.session().attribute("account")).getFeed().getPage().asJson();
    };
    public static Route addAccount = (Request request, Response response) -> {
        request.params().forEach((key, value) -> System.out.println(key));
        System.out.println(request.queryParams().toArray().length);
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
        catch(ParseException p) {
            dateOfBirth = null;
        }
        String bio = request.queryParams("bio");
        String livingLocation = request.queryParams("livingLocation");
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        ArrayList<FollowableId> s = new ArrayList<>();
        Feed f = new Feed(s);
        Account a = new Account(0, p);
        DBCommunication.addAccount(a);
        System.out.println("Added account with username " + username);
        response.status(200);
        return "OK";
    };
    public static Route addJournal = (Request request, Response response) -> {
        String name = request.queryParams("name");
        Boolean isPrivate = Boolean.parseBoolean(request.queryParams("isPrivate"));
        Boolean hasLikes = Boolean.parseBoolean(request.queryParams("hasLikes"));
        Boolean hasFollowers = Boolean.parseBoolean(request.queryParams("hasFollowers"));
        HashSet<Long> owners = new HashSet<>();
        Scanner ownerScanner = new Scanner(request.queryParams("owners"));
        while(ownerScanner.hasNextLong()) {
            owners.add(ownerScanner.nextLong());
        }
        HashSet<Long> contributers = new HashSet<>();
        Scanner contributerScanner = new Scanner(request.queryParams("contributers"));
        while(contributerScanner.hasNextLong()) {
            contributers.add(contributerScanner.nextLong());
        }
        ArrayList<Page> pages = new ArrayList<>();
        JournalStatistics stats = new JournalStatistics(new HashSet<>(), new HashSet<>(), new HashSet<>());
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, owners, contributers);
        Journal j = new Journal(0,name,pages,stats,options);
        DBCommunication.addJournal(j);

        Account sessionAccount = request.session().attribute("account");
        sessionAccount.addJournal(j);
        DBCommunication.editAccount(sessionAccount.getId(), sessionAccount);
        return "OK";
    };
    public static Route addPage = (Request request, Response response) -> {
        long newId = 0;
        String newName = request.queryParams("name");
        String content = request.queryParams("content");
        long authorId = Long.parseLong(request.queryParams("authorId"));
        long parentJournalId = Long.parseLong(request.queryParams("parentJournalId"));
        Journal parentJournal = DBCommunication.getJournal(parentJournalId);
        Page p = new Page(newId, newName, content, authorId, parentJournal);
        parentJournal.addPage(p);
        DBCommunication.editJournal(parentJournalId, parentJournal);
        return "OK";
    };
    public static Route createSession = (Request request, Response response) -> {
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
    public static Route editAccount = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
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
    public static Route editJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
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
        HashSet<Long> owners = toModify.getOwners();
        try {
            Scanner ownerScanner = new Scanner(request.queryParams("owners"));
            ownerScanner.useDelimiter("\\D+");
            while (ownerScanner.hasNextLong()) {
                owners.add(ownerScanner.nextLong());
            }
        }
        catch(NullPointerException n) {
            //DO NOTHING WOO
        }
        HashSet<Long> contributers = new HashSet<>();
        try {
            Scanner contributerScanner = new Scanner(request.queryParams("contributers"));
            contributerScanner.useDelimiter("\\D+");
            while(contributerScanner.hasNextLong()) {
                contributers.add(contributerScanner.nextLong());
            }
        }
        catch(NullPointerException n) {
            //DOO NOOTHING WOOOO
        }
        ArrayList<Page> pages = toModify.getPages();
        JournalStatistics stats = toModify.getStats();
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, owners, contributers);
        Journal j = new Journal(id,name,pages,stats,options);
        DBCommunication.editJournal(id, j);
        response.status(200);
        return "OK";
    };
    public static Route subscribeAccount = (Request request, Response response) -> {
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
    public static Route subscribeJournal = (Request request, Response response) -> {
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
    public static Route deleteAccount = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        DBCommunication.deleteAccount(id);
        return "OK";
    };
    public static Route deleteJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        DBCommunication.deleteJournal(id);
        return "OK";
    };
    public static Route deletePage = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        DBCommunication.deletePage(id);
        return "OK";
    };
    public static Route getAccountPages = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        Account a = DBCommunication.getAccount(id);
        return a.getPages(((Account)request.session().attribute("account")).getId());
    };
    public static Route getJournalPages = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
        Journal j = DBCommunication.getJournal(id);
        return j.getPages(((Account)request.session().attribute("account")).getId());
    };
}
