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
        if(value != null) {
            return new JSONValue(value);
        }
        else return null;
    }
    public String toJSONString() {
        if(value instanceof Number) {
            return value.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        sb.append(value.toString());
        sb.append('"');
        return sb.toString();
    }
}
