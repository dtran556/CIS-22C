
/**
 * CompareSecondaryKey.java
 * Helper class implementing object comparison using
 * secondary key
 * 
 * @author Kim Bui
 * @author 
 * @author 
 * CIS 22C, Final Project
 */

import java.util.Comparator;

public class CompareSecondaryKey implements Comparator <Game> {

	/**
	 * Compares two Game objects by secondary key order
	 * 
	 * @param o1  the first object to be compared
	 * @param o2  the second object to be compared
	 * return -1  o1 < o2
	 *         0  o1 = o2
	 *         1  o1 > o2
	 * @precondition objects are not null
	 * @throws NullPointerException when either 
	 *         of the objects is null.
	 * @throws ClassCastException when two objects
	 *         are different object types
	 *
	 */
	@Override
	public int compare(Game o1, Game o2) throws NullPointerException, ClassCastException {
		if (o1 == null || o2 == null) {
			throw new NullPointerException("compare(): Object cannot be null.");
		} 
		
		if (o1.getClass() != o2.getClass()) {
			throw new ClassCastException("compare(): Two objects are not the same type.");
		}
		
		return o1.getDeveloper().compareToIgnoreCase(o2.getDeveloper());		
	}
	
}
