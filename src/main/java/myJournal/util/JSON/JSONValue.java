package myJournal.util.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Json value.
 */
public class JSONValue implements JSONElement{
    /**
     * The Value.
     */
    Object value;

    /**
     * Instantiates a new Json value.
     *
     * @param value the value
     */
    public JSONValue(Object value) {
        this.value = value;
    }

    /**
     * Create a JSONValue from another object.
     *
     * @param value the value
     * @return json value
     */
    public static JSONValue from(Object value) {
        if(value != null) {
            return new JSONValue(value);
        }
        else return null;
    }
    public String toJSONString() {
        if(value instanceof Number) {
            return value.toString();
        }
        if(value instanceof Date) {
            StringBuilder sb = new StringBuilder();
            sb.append('"');
            sb.append(new SimpleDateFormat("yyyy-MM-dd").format(value));
            sb.append('"');
            return sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        sb.append(value.toString());
        sb.append('"');
        return sb.toString();
    }
    public Object getValue() {
        return value;
    }
}
