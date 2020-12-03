package myJournal.util.JSON;

/**
 * Represents an object that can be serialized as JSON.
 */
public interface JSONSerializable {
    /**
     * As json element json element.
     *
     * @return the json element
     */
    JSONElement asJsonElement();

    /**
     * As json string.
     *
     * @return the string
     */
    String asJson();
}
