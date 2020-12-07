import myJournal.DataStructures.Account;
import myJournal.DataStructures.AccountData;
import myJournal.util.JSON.JSONElement;
import myJournal.util.JSON.JSONParser;
import myJournal.util.JSON.JSONValue;

public class TestJavaParser {
    public static void main(String[] args) {
        Account a = new Account(12, "spook12","spook12", new AccountData(null, null, null, null, null, null));
        System.out.println(a.asJson());
        JSONElement j = JSONParser.parse(a.asJson());
        System.out.println(j.toJSONString());
        System.out.println(JSONValue.from("POOP").toJSONString());
    }
}
