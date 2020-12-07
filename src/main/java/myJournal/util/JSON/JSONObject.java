package myJournal.util.JSON;

import myJournal.DataStructures.Page;

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

    public JSONElement get(String key) {
        try{
            return map.get(key);
        }
        catch(NoSuchElementException e) {
            return null;
        }
    }
    public Object getValue(String key) {
        return ((JSONValue)get(key)).getValue();
    }

    public String getAsString(String key) {
        return (String) getValue(key);
    }

    public String getAsStringOrNull(String key) {
        try {
            return getAsString(key);
        } catch(NullPointerException n) {
            return null;
        }
    }

    public Long getAsLong(String key) {
        return (Long) getValue(key);
    }

    public Long getAsLongOrNull(String key) {
        try {
            return getAsLong(key);
        } catch(NullPointerException n) {
            return null;
        }
    }

    public Boolean getAsBoolean(String key) {
        return (Boolean) getValue(key);
    }

    public Boolean getAsBooleanOrNull(String key) {
        try {
            return getAsBoolean(key);
        } catch(NullPointerException n) {
            return null;
        }
    }
}
