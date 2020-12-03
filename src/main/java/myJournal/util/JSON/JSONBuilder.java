package myJournal.util.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

/**
 * Object to more easily build JSON from Objects.
 */
public class JSONBuilder {
    /**
     * The Root element.
     */
    JSONElement rootElement;
    /**
     * The Last elements.
     */
    Stack<JSONElement> lastElements;

    /**
     * Instantiates a new Json builder.
     *
     * @param rootElement the root element
     */
    public JSONBuilder(JSONElement rootElement) {
        this.rootElement = rootElement;
        lastElements = new Stack<>();
        lastElements.push(rootElement);
    }

    /**
     * Object json builder.
     *
     * @return the json builder
     */
    public static JSONBuilder object() {
        return new JSONBuilder(new JSONObject());
    }

    /**
     * Array json builder.
     *
     * @return the json builder
     */
    public static JSONBuilder array() {
        return new JSONBuilder(new JSONArray());
    }

    private JSONElement lastElement() {
        return lastElements.peek();
    }

    /**
     * Close the last element that was being modified in the json builder.
     *
     * @return the json builder
     */
    public JSONBuilder close() {
        lastElements.pop();
        return this;
    }

    /**
     * Put a new pair into current object. Only works if the currently modified element is JSONObject.
     *
     * @param key   the key
     * @param value the value
     * @return the json builder
     */
    public JSONBuilder pair(String key, JSONElement value) {
        if(value != null) {
            if (lastElement() instanceof JSONObject) {
                ((JSONObject) lastElement()).put(key, value);
            } else throw new IllegalStateException();
        }
        return this;
    }

    public JSONBuilder pair(String key, JSONSerializable value) {
        if(value != null) {
            pair(key, value.asJsonElement());
        }
        return this;
    }

    public JSONBuilder pair(String key, Object value) {
        pair(key, JSONValue.from(value));
        return this;
    }

    /**
     * Put a new pair into the object, creating a new JSONObject as the value,
     * which will become the new modified element. Only works if the currently modified element is JSONObject.
     *
     * @param key the key for the new object
     * @return the json builder
     */
    public JSONBuilder pairObject(String key) {
        JSONObject j = new JSONObject();
        pair(key, j);
        lastElements.push(j);
        return this;
    }

    /**
     * Put a new pair into the current object, creating a new JSONArray as the value,
     * which will become the new modified element. Only works if the currently modified element is JSONObject.
     *
     * @param key the key for the new array
     * @return the json builder
     */
    public JSONBuilder pairArray(String key) {
        JSONArray j = new JSONArray();
        pair(key, j);
        lastElements.push(j);
        return this;
    }

    /**
     * Add a new JSONElement into the array currently being modified. Only works if the currently modified element is
     * JSONArray.
     *
     * @param e the element to add
     * @return the json builder
     */
    public JSONBuilder add(JSONElement e) {
        if(e != null) {
            if (lastElement() instanceof JSONArray) {
                ((JSONArray) lastElement()).add(e);
            } else throw new IllegalStateException();
        }
        return this;
    }

    public JSONBuilder add(JSONElement ... es) {
        for (JSONElement e: es) {
            add(e);
        }
        return this;
    }

    public JSONBuilder add(JSONSerializable e) {
        add(e.asJsonElement());
        return this;
    }

    public JSONBuilder add(JSONSerializable ... es) {
        add(Arrays.asList(es));
        return this;
    }

    public <R extends JSONSerializable> JSONBuilder add(Collection<R> es) {
        if(es != null) {
            for (JSONSerializable e : es) {
                add(e);
            }
        }
        return this;
    }

    /**
     * Add a new element into the current array, creating a new JSONArray as the value,
     * which will become the new modified element. Only works if the currently modified element is JSONArray.
     *
     * @return the json builder
     */
    public JSONBuilder addArray() {
        JSONArray j = new JSONArray();
        add(j);
        lastElements.push(j);
        return this;
    }

    /**
     * Add a new element into the current array, creating a new JSONArray filled with elements as the value,
     * which will become the new modified element. Only works if the currently modified element is JSONArray.
     *
     * @param elements the elements
     * @return the json builder
     */
    public JSONBuilder addArray(JSONElement... elements) {
        JSONArray j = new JSONArray(elements);
        add(j);
        lastElements.push(j);
        return this;
    }

    /**
     * Add a new element into the current array, creating a new JSONObject as the value,
     * which will become the new modified element. Only works if the currently modified element is JSONArray.
     *
     * @return json builder
     */
    public JSONBuilder addObject() {
        JSONObject j = new JSONObject();
        add(j);
        lastElements.push(j);
        return this;
    }

    /**
     * Add a new element into the current array, creating a new JSONObject with key value pair as the value,
     * which will become the new modified element. Only works if the currently modified element is JSONArray.
     *
     * @param key   the key
     * @param value the value
     * @return the json builder
     */
    public JSONBuilder addObject(String key, JSONElement value) {
        JSONObject j = new JSONObject(key, value);
        add(j);
        lastElements.push(j);
        return this;
    }

    public String toString() {
        return rootElement.toJSONString();
    }

    /**
     * Get the JSONBuilder as a JSONElement.
     *
     * @return the json element
     */
    public JSONElement toJSONElement() {
        return rootElement;
    }
}
