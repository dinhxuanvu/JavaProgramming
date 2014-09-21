/**
 *
 * View.java
 * 
 * $Id: View.java,v 1.2 2013/12/10 04:57:42 vxd9797 Exp $
 *
 * $Log: View.java,v $
 * Revision 1.2  2013/12/10 04:57:42  vxd9797
 * Final Version.
 *
 * Revision 1.1  2013/12/10 04:46:04  vxd9797
 * Initial Revision
 *
 * 
 */

/**
 * 
 * Simple class to represent View of the GUI + the Controller.
 * 
 * @author vxd9797
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;


public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Main panel for GUI
	private JPanel Panel;
		
	// Text fields for user's info
	private JTextField lifeField;
	private JTextField levelField;
	
	// Labels for components in GUI
	private JLabel labelDirection;
	private JLabel labelLifeForce;
	private JLabel labelLevel;
	private JLabel labelStatus;
	private JLabel labelItem;
	private JLabel labelCarry;

	// JList to display lists of hallways, items and carried items
	private JList listDirection;
	private JList listItem;
	private JList listCarry;
	
	// Function buttons: Go, Reset, Add and Drop
	private JButton buttonGo;
	private JButton buttonReset;
	private JButton buttonAdd;
	private JButton buttonDrop;
	
	// Status' text field to update events
	private JTextArea infoArea;
	
	Model model;
	
	// Strings to hold the current items and hall
	String selectCarry;
	String selectItem;
	String selectHall;
	boolean selected = false;

	/**
	 * Create the frame of the GUI
	 */
	public View(Model model) {
		this.model = model;
		
		Panel = new JPanel();
		
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		
		// User's health field
		labelLifeForce = new JLabel("Life Force:");
		labelLifeForce.setBounds(6, 6, 78, 16);
		Panel.add(labelLifeForce);
		
		lifeField = new JTextField();
		lifeField.setBounds(79, 0, 90, 28);
		Panel.add(lifeField);
		lifeField.setEditable(false);
		lifeField.setColumns(10);
		
		// User's level field
		labelLevel = new JLabel("Level:");
		labelLevel.setBounds(189, 6, 61, 16);
		Panel.add(labelLevel);
		
		levelField = new JTextField();
		levelField.setBounds(234, 0, 90, 28);
		Panel.add(levelField);
		levelField.setEditable(false);
		levelField.setColumns(10);
		
		// Direction's field
		labelDirection = new JLabel("Direction:");
		labelDirection.setBounds(6, 34, 78, 16);
		Panel.add(labelDirection);
		
		JScrollPane scrollDirection = new JScrollPane();
		scrollDirection.setBounds(6, 62, 163, 165);
		Panel.add(scrollDirection);
		
		// Direction's list
		listDirection = new JList();
		scrollDirection.setViewportView(listDirection);
		listDirection.addListSelectionListener(new DirectionSelectionListener());
		listDirection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Status's field which display the current events
		labelStatus = new JLabel("Status:");
		labelStatus.setBounds(189, 34, 61, 16);
		Panel.add(labelStatus);
		
		// Go button to go to the hallway
		buttonGo = new JButton("Go!");
		buttonGo.setBounds(6, 239, 117, 29);
		buttonGo.addActionListener(new GoButtonListener());
		Panel.add(buttonGo);
		
		// Reset button to reset the game
		buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ResetButtonListener());

		buttonReset.setBounds(189, 239, 117, 29);
		Panel.add(buttonReset);
		
		// Items' field
		labelItem = new JLabel("Item:");
		labelItem.setBounds(6, 280, 78, 16);
		Panel.add(labelItem);
		
		// Carried items' field
		labelCarry = new JLabel("Carry:");
		labelCarry.setBounds(189, 280, 78, 16);
		Panel.add(labelCarry);
		
		JScrollPane scrollItem = new JScrollPane();
		scrollItem.setBounds(6, 308, 163, 165);
		Panel.add(scrollItem);
		
		// The list of items in the room
		listItem = new JList();
		scrollItem.setViewportView(listItem);
		listItem.addListSelectionListener(new ItemSelectionListener());
		listItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		JScrollPane scrollCarry = new JScrollPane();
		scrollCarry.setBounds(189, 308, 163, 165);
		Panel.add(scrollCarry);
		
		// List of carried items
		listCarry = new JList();
		scrollCarry.setViewportView(listCarry);
		listCarry.addListSelectionListener(new CarrySelectionListener());
		listCarry.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Add button to add the item from the room to carried items list
		buttonAdd = new JButton("Add");
		buttonAdd.setBounds(6, 485, 117, 29);
		buttonAdd.addActionListener(new AddButtonListener());
		Panel.add(buttonAdd);

		// Drop button to drop the item from the carried list
		buttonDrop = new JButton("Drop");
		buttonDrop.setBounds(189, 485, 117, 29);
		buttonDrop.addActionListener(new DropButtonListener());
		Panel.add(buttonDrop);
		
		JScrollPane scrollInfo = new JScrollPane();
		scrollInfo.setBounds(189, 62, 355, 165);
		Panel.add(scrollInfo);
		
		// Status's text field to display the current events
		infoArea = new JTextArea();
		infoArea.setEditable(false);
		scrollInfo.setViewportView(infoArea);
		
		// Reset at first
		reset();
				
		setTitle("Dungeon Crawler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
	}
	
	/**
	 * 
	 * Pop-up box for cases of winning, losing and errors.
	 * 
	 */
	public static void infoBox(String infoMessage, String error)
	{
		JOptionPane.showMessageDialog(null, infoMessage,error, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * 
	 * Reset the game
	 * 
	 */
	public void reset() {
				
		for (Room room : model.updateRoom()) {
			room.restore();
		}
		
		model.setCurrentRoom(0);
		model.getUser().setLevel(1);
		model.getUser().setLife(100);
		model.getUser().clearCarry();
		setLifeForce();
		setLevel();
		
		// Clear the status text field
		infoArea.setText("");
		
		// Display the first room
		String info = "You are in " + model.getRoom(model.getCurrentRoom()).getName() + ".\n\r";
		infoArea.append(info);
		
		// Update carried list
		updateCarry();
		
		// Update hallway list
		updateDirection();
		
		// Update items' list
		updateItem();
	}
	
	/**
	 * 
	 * Update the carried items list
	 * 
	 */
	public void updateCarry() {
		
		if (!model.getUser().getCarry().isEmpty()) {
			
			setCarryList(model.updateCarry().toArray(new String[model.updateCarry().size()]));
		}
		else {
			String[] array = new String[0];
			setCarryList(array);
		}
	}
	
	/**
	 * 
	 * Update the list of items in the room
	 * 
	 */
	public void updateItem() {
		
		if (!model.updateItem(model.getCurrentRoom()).isEmpty()) {
			setItemList(model.updateItem(model.getCurrentRoom()).toArray(new String[model.getRoom(model.getCurrentRoom()).getItem().size()]));
		}
		else {
			String[] array = new String[0];
			setItemList(array);
		}
	}
	
	/**
	 * 
	 * Update the list of hallway from the specific room
	 * 
	 */
	public void updateDirection() {
		
		if (!model.updateDirection(model.getCurrentRoom()).isEmpty()) {			
			setDirectionList(model.updateDirection(model.getCurrentRoom()).toArray(new String[model.updateDirection(model.getCurrentRoom()).size()]));
		}
		else {
			String[] array = new String[0];
			setDirectionList(array);
		}
	}
	
	/**
	 * 
	 * Update the Status
	 * 
	 */
	public void updateText(String string) {
		infoArea.append(string);
	}
	
	/**
	 * 
	 * Set the carried list
	 * 
	 */
	public void setCarryList(String[] array) {
		if (array.length == 0) {
			listCarry.setListData(new Object[0]);
		}
		else
			listCarry.setListData(array);
	}
	
	/**
	 * 
	 * Set the hallway list
	 * 
	 */
	public void setDirectionList(String[] array) {
		if (array.length == 0) {
			listDirection.setListData(new Object[0]);
		}
		else
			listDirection.setListData(array);
	}
	
	/**
	 * 
	 * Set the items list in the room
	 * 
	 */
	public void setItemList(String[] array) {
		if (array.length == 0) {
			listItem.setListData(new Object[0]);
		}
		else
			listItem.setListData(array);
	}
	
	/**
	 * 
	 * Update the user's health
	 * 
	 */
	public void setLifeForce() {
		int force = model.getUser().getLife();
		lifeField.setText(Integer.toString(force));
	}
	
	/**
	 * 
	 * Update user's level
	 * 
	 */
	public void setLevel() {
		int level = model.getUser().getLevel();
		levelField.setText(Integer.toString(level));
	}
	
	/**
	 * 
	 * Check if user is winning
	 * 
	 */
	public void isWinner() {
		for (String string : model.getRoom(model.getCurrentRoom()).getItem()) {
			if (string.equals("Amulet")) {
				View.infoBox("You found the amulet! You win!", "Winner");
				break;
			}
		}
	}
	
	/**
	 * 
	 * Get the trap in the room
	 * 
	 */
	public void getTrapped() {
    	
    	Trap trap = model.getRoom(model.getCurrentRoom()).getTrap();
    	// Check if the user has the protection (if the carried list is not empty)
		if (!model.updateCarry().isEmpty()) {
			for (String string : model.updateCarry()) {
				
				// User has protection
				if (string.contains(trap.getProtect())) {
					model.getUser().removeItem(string);
					String info = "You care protected by " + string + ".\n\r";
					infoArea.append(info);
				
					// Update GUI's fields
					updateDirection();
					updateCarry();
					updateItem();
					model.getUser().addLevel();
					setLifeForce();
					setLevel();
					return;
				}
			}
		}
		// If there is a trap
		if (trap != null) {
			String trapInfo = "You are trapped by " + model.getRoom(model.getCurrentRoom()).getTrap().getName() + ".\n\r";
			infoArea.append(trapInfo);
			String trapType = trap.getType();
			
			// The trap is weaken type
			if (trapType.equals("weaken")) {
				String info = "You are damaged by " + model.getRoom(model.getCurrentRoom()).getTrap().getDamage() + " point(s).\n\r";
				infoArea.append(info);
				// Damage user's health
				model.updateLife(trap.getDamage());
				if (model.getUser().getLife() > 0) {
					// Update level
					model.getUser().addLevel();
					updateDirection();
					updateCarry();
					updateItem();
					setLifeForce();
					setLevel();
				}
				// User dies due to lack of health
				else {
					View.infoBox("Life reaches zero. You die!", "Game Over!");
					reset();
					return;
				}
			}
			// Trap is scatter type
			else if (trapType.equals("scatter")) {
				String info = "Your carried items are scattered! \n\r";
				infoArea.append(info);
				// If there is no carried items
				if (model.getUser().getCarry().isEmpty()) {
					// Update user's info and GUI's fields
					model.getUser().addLevel();
					updateDirection();
					updateCarry();
					updateItem();
					setLifeForce();
					setLevel();
					return;
				}
				// User carries items
				else {
					int counter = 0;
					// Scatter the carried items
					for (int i = 0; i < model.getUser().getCarry().size(); i++) {
						if (model.updateRoom().size() <= counter) {
							counter = 0;
							model.getRoom(counter).addItem(model.getUser().getCarry().remove(i));
						}
						else {
							model.getRoom(counter).addItem(model.getUser().getCarry().remove(i));
						}
						counter++;
					}
					// Update GUI's field and user
					model.getUser().addLevel();
					updateDirection();
					updateCarry();
					updateItem();
					setLifeForce();
					setLevel();
					return;
				}
			}
			// Trap is warp type
			else if (trapType.equals("warp")) {
				// Warp to the new room
				model.setCurrentRoom(trap.getWarp());
				String info = "You are warped to " + model.getRoom(model.getCurrentRoom()).getName() + ".\n\r";
				infoArea.append(info);
				// Check for trap in the new room
				getTrapped();
				// Update GUI and user
				model.getUser().addLevel();
				updateCarry();
				updateDirection();
				updateItem();
				setLifeForce();
				setLevel();
				return;
			}
		}
		// If there is no trap
		else {
			model.getUser().addLevel();
			updateCarry();
			updateDirection();
			updateItem();
			setLifeForce();
			setLevel();
			return;
		}
    }
	
	/**
	 * 
	 * Reset button function
	 * 
	 */
    class ResetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			reset();
		}
    }
    
    /**
	 * 
	 * Go button function
	 * 
	 */
    class GoButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (selected == true) {
    			int count = 0;
    			for (Hallway hall : model.getHallway()) {
    				if (hall.getName().equals(selectHall)) {
    					break;
    				}
    				count++;
    			}

    			Hallway hall = model.getHallway().get(count);
    			// Go to the next room
    			if (hall.getExits().size() == 1) {
    				model.setCurrentRoom(hall.getExits().getFirst());
    			}
    			// Select of random exits from the list of exits
    			else {
    				Random r = new Random();
    				int random = r.nextInt(2);
    				model.setCurrentRoom(hall.getExits().get(random));
    			}
    			String info = "You are in " + model.getRoom(model.getCurrentRoom()).getName() + ".\n\r";
    			infoArea.append(info);
    			selected = false;
    			// Check of trap
    			getTrapped();
    			// Check if the new room has amulet
    			isWinner();
    		}
    	}
    }
    
    /**
	 * 
	 * Add button function
	 * 
	 */
    class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int currentLevel = model.getUser().getLevel();
			int numberOfCarry = model.getUser().getCarry().size();
			
			// Check to make sure use can't carry more item than level
			if (numberOfCarry < currentLevel) {
				int currentRoom = model.getCurrentRoom();
				// Remove item from the room
				model.getRoom(currentRoom).removeItem(selectItem);
				// Add to the carried list
				model.getUser().addItem(selectItem);
				updateItem();
				updateCarry();
			}
			// Warn user if trying to add more items than level
			else {
				String warning = "You cannot hold more than " + model.getUser().getLevel() + " item(s).";
				View.infoBox(warning, "Error!");
			}
		}
    }
    
    /**
	 * 
	 * Drop button function
	 * 
	 */
    class DropButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Remove item from carried list
			model.getUser().removeItem(selectCarry);
			int currentRoom = model.getCurrentRoom();
			// Add removed item to the room
			model.getRoom(currentRoom).addItem(selectCarry);
			updateCarry();
			updateItem();
		}
    }
    
    /**
	 * 
	 * Direction selection
	 * 
	 */
    class DirectionSelectionListener implements ListSelectionListener {
	
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			selectHall = (String) listDirection.getSelectedValue();
			selected = true;
		}
    }
    
    /**
	 * 
	 * Item selection
	 * 
	 */
    class ItemSelectionListener implements ListSelectionListener {
    	
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			selectItem = (String) listItem.getSelectedValue();
		}
    }
    
    /**
	 * 
	 * Carried item selection
	 * 
	 */
    class CarrySelectionListener implements ListSelectionListener {
    	
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			selectCarry = (String) listCarry.getSelectedValue();
		}
    }
}
