package myJournal.DataStructures;

import java.util.HashSet;
import java.util.Objects;


/**
 * Interface which adds methods for viewing/editing security.
 */

public interface Permissions {

    /**
     *
     * @return a HashSet of the viewers of the object
     */
    HashSet<Long> getViewers();

    /**
     * @return a HashSet of the editors of the object
     */
    HashSet<Long> getEditors();
    
    /**
     * @return a HashSet of the owners of the object
     */
    HashSet<Long> getOwners();
	
    /**
     * @return true if the id is of an account which can view the object
     */
    default boolean canView(Long id) {
    	if (getViewers().equals(new HashSet<Long>()))
    		return true;
    	
    	for (Long i: getViewers())
    		if (Objects.equals(id, i))
    			return true;
    	return canEdit(id);
    }
    
    /**
     * @return true if the id is of an account which can edit the object
     */
    default boolean canEdit(Long id) {
    	for (Long i: getEditors())
    		if (Objects.equals(id, i))
    			return true;
    	return isOwner(id);
    }
    
    /**
     * @return true if the id is of an account which owns the object
     */
    default boolean isOwner(Long id) {
    	for (Long i: getOwners())
    		if (Objects.equals(id, i))
    			return true;
    	
    	return false;
    }
    
}
