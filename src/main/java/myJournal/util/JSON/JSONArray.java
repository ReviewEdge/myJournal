package myJournal.util.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Json array.
 */
public class JSONArray implements JSONElement{
    /**
     * The List.
     */
    List<JSONElement> list;

    /**
     * Instantiates a new Json array.
     */
    public JSONArray() {
        list = new ArrayList<>();
    }

    /**
     * Instantiates a new Json array prefilled with JSONElements.
     *
     * @param elements the elements
     */
    public JSONArray(JSONElement ... elements) {
        this();
        for(JSONElement element : elements) {
            list.add(element);
        }
    }

    /**
     * Creates a new JSON array from a list of JSONElements.
     *
     * @param elements the elements
     * @return json array
     */
    public static JSONArray from(JSONElement ... elements) {
        return new JSONArray(elements);
    }

    /**
     * Add an element to the array.
     *
     * @param e the e
     */
    public void add(JSONElement e) {
        list.add(e);
    }

    @Override
    public String toJSONString() {
        StringBuilder out = new StringBuilder();
        out.append('[');
        char[] prefix = {' '};
        list.forEach((v) -> {
            out.append(prefix[0]);
            out.append(v.toJSONString());
            prefix[0] = ',';
        });
        return out.toString();
    }
}
