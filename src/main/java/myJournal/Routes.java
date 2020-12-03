
package myJournal;

import myJournal.DataStructures.*;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import spark.Route;

import java.text.DateFormat;
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
        System.out.println("Accessing account with username " + username);
        System.out.println(DBCommunication.getAccountByUsername(username));
        return DBCommunication.getAccountByUsername(username);
    };
    public static Route getJournal = (Request request, Response response) -> {
        long id = Long.parseLong(request.queryParams("id"));
        return DBCommunication.getJournal(id);
    };
    public static Route getPage = (Request request, Response response) -> {
        long id = Long.parseLong(request.queryParams("id"));
        return DBCommunication.getPage(id);
    };
    public static Route getFeed = (Request request, Response response) -> {
        return ((Account)request.session().attribute("account")).getFeed().getPage();
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
        Date dateOfBirth = (new SimpleDateFormat("yyyy-MM-DD")).parse(request.queryParams("dateOfBirth"));
        String bio = request.queryParams("bio");
        String livingLocation = request.queryParams("livingLocation");
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        ArrayList<Followable> s = new ArrayList<>();
        Feed f = new Feed(s);
        Account a = new Account(0, p, s, f);
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
        String newName = request.queryParams("name");
        String content = request.queryParams("content");
        long authorId = Long.parseLong(request.queryParams("authorId"));
        long parentJournalId = Long.parseLong(request.queryParams("parentJournalId"));
        Journal parentJournal = DBCommunication.getJournal(parentJournalId);
        Page p = new Page(newId, newName, content, authorId, parentJournal);
        return "OK";
    };
    public static Route createSession = (Request request, Response response) -> {
//        String username = request.queryParams("username");
        int id = Integer.parseInt(request.queryParams("id"));
        String passwordHash = request.queryParams("phash");
        Account a = DBCommunication.getAccount(id);
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
        Long id = Long.parseLong(request.queryParams("id"));
        String firstName = request.queryParams("firstName");
        String lastName = request.queryParams("lastName");
        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String salt = BCrypt.gensalt(3);
        String passwordHash = BCrypt.hashpw(password, salt);
        Date accountCreation = new Date();
        Date dateOfBirth = DateFormat.getDateInstance().parse(request.queryParams("dateOfBirth"));
        String bio = request.queryParams("bio");
        String livingLocation = request.queryParams("livingLocation");
        AccountData p = new AccountData(firstName, lastName, username, passwordHash, accountCreation, dateOfBirth, bio, livingLocation);
        ArrayList<Followable> s = new ArrayList<>();
        Feed f = new Feed(s);
        Account a = new Account(id, p, s, f);
        DBCommunication.editAccount(id,a);
        response.status(200);
        return "OK";
    };
    public static Route editJournal = (Request request, Response response) -> {
        Long id = Long.parseLong(request.queryParams("id"));
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
