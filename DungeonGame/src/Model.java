/**
 *
 * Model.java
 * 
 * $Id: Model.java,v 1.1 2013/12/10 04:46:06 vxd9797 Exp $
 *
 * $Log: Model.java,v $
 * Revision 1.1  2013/12/10 04:46:06  vxd9797
 * Initial Revision
 *
 * 
 */

/**
 * 
 * Simple class to represent Model of the GUI.
 * 
 * @author vxd9797
 */

import java.util.LinkedList;


public class Model {
	
	private User user;
	private LinkedList<Room> roomList;
	private LinkedList<Hallway> hallList;
	
	private LinkedList<String> directionList = new LinkedList<String>();
	
	private int currentRoom;
	
	/**
	 * 
	 * Model constructor of the GUI.
	 * 
	 */
	public Model(User user, LinkedList<Room> roomList, LinkedList<Hallway> hallList)
	{
		this.user = user;
		this.roomList = roomList;
		this.hallList = hallList;
		currentRoom = 0;
	}
	
	/**
	 * 
	 * Update the user's health
	 * 
	 */
	public void updateLife(int damage) {
		int newLife = user.getLife() - damage;
		user.setLife(newLife);
	}
	
	/**
	 * 
	 * Update user's level
	 * 
	 */
	public void updateLevel() {
		user.setLevel(user.getLevel() + 1);
	}
	
	/**
	 * 
	 * Get the list of room
	 * 
	 */
	public Room getRoom(int num) {
		return roomList.get(num);
	}
	
	/**
	 * 
	 * Update the list of hall
	 * 
	 */
	public LinkedList<String> updateDirection(int num) {
		
		int ID = roomList.get(num).getID();
		directionList.clear();
		for (int i = 0; i < hallList.size(); i++) {
			if (hallList.get(i).enter == ID) {
				directionList.add(hallList.get(i).getName());
			}
			for (int j = 0; j < hallList.get(i).getExits().size(); j++) {
				if (hallList.get(i).getExits().get(j) == ID) {
					directionList.add(hallList.get(i).getName());
				}
			}
		}
		return directionList;
	}
	
	/**
	 * 
	 * Update the list of items in current room
	 * 
	 */
	public LinkedList<String> updateItem(int num) {

		return roomList.get(num).getItem();
	}
	
	/**
	 * 
	 * Get the user's object
	 * 
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * 
	 * Update the user's carried items
	 * 
	 */
	public LinkedList<String> updateCarry() {

		return user.getCarry();
	}
	
	/**
	 * 
	 * Get the list of room
	 * 
	 */
	public LinkedList<Room> updateRoom() {

		return roomList;
	}
	
	/**
	 * 
	 * Get the current room's ID
	 * 
	 */
	public int getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * 
	 * Get the list of hallway
	 * 
	 */
	public LinkedList<Hallway> getHallway() {
		return hallList;
	}
	
	/**
	 * 
	 * Set the current room's ID
	 * 
	 */
	public void setCurrentRoom(int num) {
		currentRoom = num;
	}
  }
