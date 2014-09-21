import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * User.java
 * 
 * $Id: Dungeon.java,v 1.2 2013/12/10 04:57:43 vxd9797 Exp $
 *
 * $Log: Dungeon.java,v $
 * Revision 1.2  2013/12/10 04:57:43  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/12/10 04:46:06  vxd9797
 * Initial Revision
 *
 * 
 */

/**
 * 
 * Simple class to represent dungeon and contains the main method.
 * 
 * @author vxd9797
 */

public class Dungeon {
	
	static Model model;
	
	/**
	 * 
	 * Main method to run the GUI and the game
	 * 
	 */
 	
	public static void main(String[] args) {
		
		// User object
		User user = new User();
		
		// List of rooms
		LinkedList<Room> roomList = new LinkedList<Room>();
		
		// List of hallways
		LinkedList<Hallway> hallList = new LinkedList<Hallway>();
		
		// List of traps
		LinkedList<Trap> trapList = new LinkedList<Trap>();
		
		// File's name
		String file = args[0].toString();
		
		String word;
		
		try {
			
			// Scanner to read from the file
			Scanner in = new Scanner(new File(file));
			
			word = in.nextLine();
			
			int numberOfTrap = Integer.parseInt(word);
			
			// Create the trap objects and add to the list
			for (int i = 0; i < numberOfTrap; i++) {
				word = in.nextLine();
				String[] words = word.split(" ");
				
				// Scatter trap
				if (words[1].equalsIgnoreCase("scatter")) {
					trapList.add(new Trap(words[0], words[1], 0, 0, words[2]));
				}
				// Warp trap
				else if (words[1].equalsIgnoreCase("warp")) {
					trapList.add(new Trap(words[0], words[1], 0, Integer.parseInt(words[2]), words[3]));
				}
				// Weaken trap
				else if (words[1].equalsIgnoreCase("weaken")) {
					trapList.add(new Trap(words[0], words[1], Integer.parseInt(words[2]), 0, words[3]));
				}
			}
			
			word = in.nextLine();
			
			String[] words = word.split(" ");
			
			int index = Integer.parseInt(words[0]);
			
			// Create the room object and add to the list
			for (int x = 0; x < index; x++) {
				String nameRoom = in.nextLine();
				String nameTrap = in.nextLine();
				String item = in.nextLine();
				String[] items = item.split(" ");
				LinkedList<String> itemList;
				if (items[0].equals("none")) {
					itemList = new LinkedList<String>();
				}
				else 
				{
					// Items in the room
					itemList = new LinkedList<String>(Arrays.asList(items));
				}
				Trap trap = null;

				// Trap in the room
				for (Trap tr : trapList) {
					if (tr.getName().equals(nameTrap)) {
						trap = tr;
						break;
					}
				}
				
				int roomAmulet = Integer.parseInt(words[1]);
				
				// Add Amulet to the specific room
				if (roomAmulet == x) {
					itemList.add("Amulet");
				}
				roomList.add(new Room(nameRoom, x, trap, itemList ));
			}
			
			word = in.nextLine();
			
			int numHall = Integer.parseInt(word);
			
			// Create the hallway object and add to the list
			for (int i = 0; i < numHall; i++) {
				word = in.nextLine();
				String[] halls = word.split(" ");
				if (halls.length == 3) {
					LinkedList<Integer> list = new LinkedList<Integer>();
					list.add(Integer.parseInt(halls[2]));
					hallList.add(new Hallway(halls[0], Integer.parseInt(halls[1]), list));
				}
				else
				{
					LinkedList<Integer> list = new LinkedList<Integer>();
					list.add(Integer.parseInt(halls[2]));
					list.add(Integer.parseInt(halls[3]));
					hallList.add(new Hallway(halls[0], Integer.parseInt(halls[1]), list));
				}
			}
			
			// Close Scanner
			in.close();
		} catch (IOException e) {
			// Throw IO Exception
			System.err.println("Can't read the file!");
		}
		
		
		// Create the model
		model = new Model(user,roomList,hallList);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View view = new View(model);
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
