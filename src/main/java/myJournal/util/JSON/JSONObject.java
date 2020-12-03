package myJournal.util.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The type Json object.
 */
public class JSONObject implements JSONElement{
    /**
     * The Map.
     */
    Map<String, JSONElement> map;

    /**
     * Instantiates a new Json object.
     */
    public JSONObject() {
        map = new HashMap<>();
    }

    /**
     * Instantiates a new Json object.
     *
     * @param key   the key
     * @param value the value
     */
    public JSONObject(String key, JSONElement value) {
        this();
        this.put(key, value);
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(String key, JSONElement value) {
        map.put(key, value);
    }


    @Override
    public String toJSONString() {
        StringBuilder out = new StringBuilder();
        out.append('{');
        char[] prefix = {' '};
        map.forEach((k, v) -> {
            out.append(prefix[0]);
            out.append('"');
            out.append(k);
            out.append('"');
            out.append(':');
            out.append(v.toJSONString());
            prefix[0] = ',';
        });
        out.append('}');
        return out.toString();
    }
}
