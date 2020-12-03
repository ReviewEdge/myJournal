package myJournal.util.JSON;

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
        return new JSONValue(value);
    }
    public String toJSONString() {
        return value.toString();
    }
}
