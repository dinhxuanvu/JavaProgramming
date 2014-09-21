/**
 *
 * Room.java
 * 
 * $Id: Room.java,v 1.2 2013/12/10 04:57:43 vxd9797 Exp $
 *
 * $Log: Room.java,v $
 * Revision 1.2  2013/12/10 04:57:43  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/12/10 04:46:06  vxd9797
 * Initial Revision
 *
 * 
 */

import java.util.LinkedList;

/**
 * 
 * Simple class to represent room.
 * 
 * @author vxd9797
 */

public class Room {
	
	private String name;
	private int ID;
	private Trap trap;
	private LinkedList<String> items;
	private LinkedList<String> trash;
	private LinkedList<String> addition;
	
	/**
	 * 
	 * Room constructor
	 * 
	 */
	public Room(String name, int ID, Trap trap, LinkedList<String> items) {
		this.name = name;
		this.ID = ID;
		this.trap = trap;
		this.items = items;
		trash = new LinkedList<String>();
		addition = new LinkedList<String>();
	}
	
	/**
	 * 
	 * Get Room ID
	 * 
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * Get the name of the room
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * Get the trap inside the room
	 * 
	 */
	public Trap getTrap() {
		return trap;
	}
	
	/**
	 * 
	 * Get the list of items
	 * 
	 */
	public LinkedList<String> getItem() {
		return items;
	}
	
	/**
	 * 
	 * Remove item from the list
	 * 
	 */
	public void removeItem(String name) {
		items.remove(name);
		trash.add(name);
	}
	
	/**
	 * 
	 * Add item to the list
	 * 
	 */
	public void addItem(String name) {
		items.add(name);
		addition.add(name);
	}
	
	/**
	 * 
	 * Restore the list of items in the room
	 * 
	 */
	public void restore() {
		if (!trash.isEmpty()) {
			for (String string : trash) {
				items.add(string);
			}
			trash.clear();
		}
		if (!addition.isEmpty()) {
			for (String string : addition) {
				items.remove(string);
			}
			addition.clear();
		}
	}
 	
}
