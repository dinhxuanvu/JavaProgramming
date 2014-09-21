/**
 *
 * TrollsBridge.java
 * 
 * $Id: TrollsBridge.java,v 1.1 2013/12/04 06:58:45 vxd9797 Exp $
 *
 * $Log: TrollsBridge.java,v $
 * Revision 1.1  2013/12/04 06:58:45  vxd9797
 * Final Version. Commented and tested.
 *
 * 
 */

import java.util.LinkedList;

/**
 * Create a TrollsBridge with a given capacity.
 * 
 * @author vxd9797
 */

public class TrollsBridge {
	
	public final Object lock = new Object();
	
	// Maximum capacity of the bridge
	public int maxCap;
	
	// Current weight on the bridge (from Woolies)
	public int currentCap;
	
	// The queue holds the Woolies arriving to the bridge
	LinkedList<Woolie> queue;
	
	/**
	 * Create a TrollsBridge with a given capacity.
	 * @param max - the maximum capacity of the TrollsBridge.
	 */
	public TrollsBridge(int max) {
		maxCap = max;
		currentCap = 0;
		queue = new LinkedList<Woolie>();
	}
	
	/**
	 * Request permission to go onto the troll's bridge. 
	 * Woolies call this method to ask the troll to put them on the queue of woolies trying to get on the bridge. 
	 * The Woolie (thread) waits until it becomes the head of the queue and there is room on the troll's bridge.
	 * 
	 * The troll of a TrollsBridge guards its bridge to make sure that woolies get on the bridge in the order of their arrival.
	 * 
	 * Precondition: The calling thread is the Woolie instance itself.
	 * 
	 * Postcondition: 
	 * +The woolie got permission and has climbed onto the bridge.
	 * +At some future time, the woolie must call leave() to get off.
	 * 
	 * @param thisWoolie - the Woolie trying to get on the bridge (the same object as Thread calling this method).
	 */
	public void enterBridgePlease(Woolie thisWoolie) {

		synchronized (this) {
			System.out.println("The troll scowls \"Get in line!\" when " + thisWoolie.getName() + " shows up at the bridge.");
			queue.add(thisWoolie);
		}
		
		while ((currentCap + thisWoolie.getWeight()) > maxCap || !(queue.getFirst().equals(thisWoolie)))
		{
			synchronized (lock) {
				try 
				{
					lock.wait();
				} catch (InterruptedException e) 
				{
					System.err.println("Error!");
				}
			}
		}

		synchronized (this) {
			queue.removeFirst();
			currentCap += thisWoolie.getWeight();
		}
	}
	
	/**
	 * Tell the troll of a TrollsBridge that a woolie has left the bridge 
	 * so that the troll can let other woolies get on if there is room.
	 *
	 * A well-behaved Woolie always informs the troll of a TrollsBridge that it (the caller) is getting off the bridge.
	 * 
	 * Precondition: The calling thread is a Woolie instance that has already called enterBridgePlease().
	 * 
	 * Postcondition: The given Woolie is no longer on this TrollsBridge.
	 * 
	 * @param thisWoolie - Woolie leaving the bridge
	 */
	public void leave(Woolie thisWoolie) {
		synchronized (this) {
			currentCap -= thisWoolie.getWeight();
		}
		synchronized (lock) {
			lock.notifyAll();
		}
	}

}
