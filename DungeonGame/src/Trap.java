/**
 *
 * Trap.java
 * 
 * $Id: Trap.java,v 1.2 2013/12/10 04:57:43 vxd9797 Exp $
 *
 * $Log: Trap.java,v $
 * Revision 1.2  2013/12/10 04:57:43  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/12/10 04:46:07  vxd9797
 * Initial Revision
 *
 * 
 */

/**
 * 
 * Simple class to represent trap.
 * 
 * @author vxd9797
 */

public class Trap {
	
	private String name;
	private String type;
	private int damage;
	private int warpRoom;
	private String protection;
	
	/**
	 * 
	 * Trap constructor with multiple parameters
	 * 
	 */
	
	public Trap (String name, String type, int damage, int warpRoom, String protection) {
		this.name = name;
		this.type = type;
		this.damage = damage;
		this.warpRoom = warpRoom;
		this.protection = protection;
	}
	
	/**
	 * 
	 * Get name of the trap
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * Get trap type
	 * 
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * Get the protection for this trap
	 * 
	 */
	public String getProtect() {
		return protection;
	}
	
	/**
	 * 
	 * Get the warp location
	 * 
	 */
	public int getWarp() {
		return warpRoom;
	}
	
	/**
	 * 
	 * Get trap damage
	 * 
	 */
	public int getDamage() {
		return damage;
	}
}
