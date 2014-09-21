
/**
 *
 * Hallway.java
 * 
 * $Id: Hallway.java,v 1.2 2013/12/10 04:57:42 vxd9797 Exp $
 *
 * $Log: Hallway.java,v $
 * Revision 1.2  2013/12/10 04:57:42  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/12/10 04:46:03  vxd9797
 * Initial Revision
 *
 * 
 */

import java.util.LinkedList;

/**
 * 
 * Simple class to represent hallway.
 * 
 * @author vxd9797
 */

public class Hallway {
	
	String name;
	int enter;
	LinkedList<Integer> exits;
	
	/**
	 * 
	 * Hallway constructor
	 * 
	 */
	public Hallway (String name, int enter, LinkedList<Integer> exits) {
		this.name = name;
		this.enter = enter;
		this.exits = exits;
	}
	
	
	/**
	 * 
	 * Get name of the hallway
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * Get the entrance of the hallway
	 * 
	 */
	public int getEnter() {
		return enter;
	}
	
	/**
	 * 
	 * Get the list of exits
	 * 
	 */
	public LinkedList<Integer> getExits() {
		return exits;
	}
}
