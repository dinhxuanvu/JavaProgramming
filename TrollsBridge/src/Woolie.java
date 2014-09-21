/**
 *
 * Woolie.java
 * 
 * $Id: Woolie.java,v 1.1 2013/12/04 06:58:44 vxd9797 Exp $
 *
 * $Log: Woolie.java,v $
 * Revision 1.1  2013/12/04 06:58:44  vxd9797
 * Final Version. Commented and tested.
 *
 * 
 */

/**
 * 
 * The Woolie simulates a Woolie crossing a TrollsBridge.
 * 
 * @author vxd9797
 */

public class Woolie extends Thread {
	
	// Time a Woolie need to cross the bridge
	private int crossTime;
	
	// Woolie's weight
	private int weight;
	
	// Woolie's destination
	private String destination;
	
	// The troll manages the bridge
	TrollsBridge bridgeGuard;
	
	/**
	 * Construct a new Woolie object that can run as a thread.
	 * @param name - Woolie's name
	 * 		  crossTime - Time for Woolie to cross
	 * 		  weight - Woolie's weight
	 * 		  destination - Woolie's destination
	 *        bridgeGuard - the Troll handling the bridge
	 */
	public Woolie(String name, int crossTime, int weight, String destination, TrollsBridge bridgeGuard) {
		super(name);
		this.crossTime = crossTime;
		this.weight = weight;
		this.destination = destination;
		this.bridgeGuard = bridgeGuard;
	}

	/**
	 * Get the weight of the Woolie
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * CThe run method handles a Woolie's behavior as it crosses the bridge. 
	 * The well-behaved Woolie asks the troll at the bridge to cross. 
	 * While it is crossing the bridge, it reports its progress each second as it works its way across, 
	 * and the woolie lastly tells the troll that it has gotten off the bridge.
	 */
	public void run() {
	
		bridgeGuard.enterBridgePlease(this);
		
		for (int i = 0; i < crossTime; i++) {
			if (i == 0) {
				System.out.println(getName() + " is starting to cross.");
			}
			else
				System.out.println("\t" + getName() + " " + i + " second(s).");
			
			try 
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException ie) 
			{
			}
		}
		System.out.println(getName() + " leaves at " + destination + ".");
		bridgeGuard.leave(this);
	}
}
