
/**
 *
 * User.java
 * 
 * $Id: User.java,v 1.2 2013/12/10 04:57:42 vxd9797 Exp $
 *
 * $Log: User.java,v $
 * Revision 1.2  2013/12/10 04:57:42  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/12/10 04:46:05  vxd9797
 * Initial Revision
 *
 * 
 */

import java.util.LinkedList;

/**
 * 
 * Simple class to represent user.
 * 
 * @author vxd9797
 */

public class User {
	
	int life;
	int level;
	LinkedList<String> carry;
	
	/**
	 * 
	 * User constructor.
	 * Life = 100
	 * Level = 1
	 * 
	 */
	public User() {
		this.life = 100;
		this.level = 1;
		carry = new LinkedList<String>();
	}
	
	/**
	 * 
	 * Get user's health
	 * 
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * 
	 * Set user's health
	 * 
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
	/**
	 * 
	 * Get user's level
	 * 
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * 
	 * Set user's level
	 * 
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * 
	 * Increase user's level
	 * 
	 */
	public void addLevel() {
		level++;
	}
	
	/**
	 * 
	 * Clear all carried items
	 * 
	 */
	public void clearCarry() {
		carry = new LinkedList<String>();;
	}
	
	/**
	 * 
	 * Add items to carried items list
	 * 
	 */
	public void addItem(String name) {
		carry.add(name);
	}
	
	/**
	 * 
	 * Remove item from the carried items list
	 * 
	 */
	public void removeItem(String name) {
		int location = 0;
		for (String item : carry) {
			if (item.equals(name)) {
				break;
			}
			location++;
		}
		carry.remove(location);
	}
	
	/**
	 * 
	 * Get the list of carried items
	 * 
	 */
	public LinkedList<String> getCarry() {
		return carry;
	}

}
