package fr.abes.sudoqual.sudoqual1.util.adapter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Interface IReference.
 *
 * @author Alain
 */
public interface IReference {

	/**
     * Gets the id.
     *
     * @return the id
     */
    long getId();

    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    JSONObject getAttributes();

    /**
     * Gets the unique value.
     *
     * @param attrName the attr name
     * @return the unique value
     */
    Object getUniqueValue(String attrName);


    /**
     * Gets the value associated with an attribute.
     *
     * @param attrName the attr name
     * @return the object
     */
    JSONArray getValue(String attrName);
 
}
