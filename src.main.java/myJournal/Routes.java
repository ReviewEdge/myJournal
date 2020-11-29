package myJournal;

import myJournal.DataStructures.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Routes {
    public static Route getAccount = (Request request, Response response) -> {
        String username = request.params("username");
        return DBCommunication.getAccountByUsername(username);
    };
    public static Route getJournal = (Request request, Response response) -> {
        long id = Long.parseLong(request.params("id"));
        return DBCommunication.getJournal(id);
    };
    public static Route getPage = (Request request, Response response) -> {
        long id = Long.parseLong(request.params("id"));
        return DBCommunication.getPage(id);
    };
    public static Route getFeed = (Request request, Response response) -> {
        return ((Account)request.session().attribute("account")).getFeed().getPage();
    };
    public static Route addAccount = (Request request, Response response) -> {
        String firstName = request.params("firstName");
        String lastName = request.params("lastName");
        String username = request.params("username");
        String passwordHash = request.params("passwordHash");
        Date accountCreation = new Date();
        Date dateOfBirth = DateFormat.getDateInstance().parse(request.params("dateOfBirth"));
        String bio = request.params("bio");
        String livingLocation = request.params("livingLocation");
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        ArrayList<Followable> s = new ArrayList<>();
        Feed f = new Feed(s);
        Account a = new Account(0, p, s, f);
        DBCommunication.addAccount(a);
        response.status(200);
        return "OK";
    };
    public static Route addJournal = (Request request, Response response) -> {
        String name = request.params("name");
        Boolean isPrivate = Boolean.parseBoolean(request.params("isPrivate"));
        Boolean hasLikes = Boolean.parseBoolean(request.params("hasLikes"));
        Boolean hasFollowers = Boolean.parseBoolean(request.params("hasFollowers"));
        HashSet<Long> owners = new HashSet<>();
        Scanner ownerScanner = new Scanner(request.params("owners"));
        while(ownerScanner.hasNextLong()) {
            owners.add(ownerScanner.nextLong());
        }
        HashSet<Long> contributers = new HashSet<>();
        Scanner contributerScanner = new Scanner(request.params("contributers"));
        while(contributerScanner.hasNextLong()) {
            owners.add(contributerScanner.nextLong());
        }
        ArrayList<Page> pages = new ArrayList<>();
        JournalStatistics stats = new JournalStatistics(new HashSet<>(), new HashSet<>(), new HashSet<>());
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, owners, contributers);
        Journal j = new Journal(0,name,pages,stats,options);
        DBCommunication.addJournal(j);
        return "OK";
    };
    public static Route addPage = (Request request, Response response) -> {
        long newId = 0;
        String newName = request.params("name");
        String content = request.params("content");
        long authorId = Long.parseLong(request.params("authorId"));
        long parentJournalId = Long.parseLong(request.params("parentJournalId"));
        Journal parentJournal = DBCommunication.getJournal(parentJournalId);
        boolean hasLikes = Boolean.parseBoolean(request.params("hasLikes"));
        boolean hasViews = Boolean.parseBoolean(request.params("hasViews"));
        Page p = new Page(newId, newName, content, authorId, parentJournal, hasLikes, hasViews);
        return "OK";
    };
    public static Route createSession = (Request request, Response response) -> {
        String username = request.params("username");
        String passwordHash = request.params("phash");
        Account a = DBCommunication.getAccountByUsername(username);
        if(a.checkPasswordHash(passwordHash)) {
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
        Long id = Long.parseLong(request.params("id"));
        String firstName = request.params("firstName");
        String lastName = request.params("lastName");
        String username = request.params("username");
        String passwordHash = request.params("passwordHash");
        Date accountCreation = new Date();
        Date dateOfBirth = DateFormat.getDateInstance().parse(request.params("dateOfBirth"));
        String bio = request.params("bio");
        String livingLocation = request.params("livingLocation");
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        ArrayList<Followable> s = new ArrayList<>();
        Feed f = new Feed(s);
        Account a = new Account(id, p, s, f);
        DBCommunication.editAccount(id,a);
        response.status(200);
        return "OK";
    };
    public static Route editJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.params("id"));
        String name = request.params("name");
        Boolean isPrivate = Boolean.parseBoolean(request.params("isPrivate"));
        Boolean hasLikes = Boolean.parseBoolean(request.params("hasLikes"));
        Boolean hasFollowers = Boolean.parseBoolean(request.params("hasFollowers"));
        HashSet<Long> owners = new HashSet<>();
        Scanner ownerScanner = new Scanner(request.params("owners"));
        while(ownerScanner.hasNextLong()) {
            owners.add(ownerScanner.nextLong());
        }
        HashSet<Long> contributers = new HashSet<>();
        Scanner contributerScanner = new Scanner(request.params("contributers"));
        while(contributerScanner.hasNextLong()) {
            owners.add(contributerScanner.nextLong());
        }
        ArrayList<Page> pages = new ArrayList<>();
        JournalStatistics stats = new JournalStatistics(new HashSet<>(), new HashSet<>(), new HashSet<>());
        JournalOptions options = new JournalOptions(isPrivate, hasLikes, hasFollowers, owners, contributers);
        Journal j = new Journal(id,name,pages,stats,options);
        DBCommunication.editJournal(id, j);
        return "OK";
    };
    public static Route deleteAccount = (Request request, Response response) -> {
        Long id = Long.parseLong(request.params("id"));
        DBCommunication.deleteAccount(id);
        return "OK";
    };
    public static Route deleteJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.params("id"));
        DBCommunication.deleteJournal(id);
        return "OK";
    };
    public static Route deletePage = (Request request, Response response) -> {
        Long id = Long.parseLong(request.params("id"));
        DBCommunication.deletePage(id);
        return "OK";
    };
    public static Route getAccountPages = (Request request, Response response) -> {
        Long id = Long.parseLong(request.params("id"));
        Account a = DBCommunication.getAccount(id);
        return a.getPages(((Account)request.session().attribute("account")).getId());
    };
    public static Route getJournalPages = (Request request, Response response) -> {
        Long id = Long.parseLong(request.params("id"));
        Journal j = DBCommunication.getJournal(id);
        return j.getPages(((Account)request.session().attribute("account")).getId());
    };
}
