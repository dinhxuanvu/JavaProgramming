/* 
 * Nurikabe.java 
 * 
 * Version: 
 *     $Id: Nurikabe.java,v 1.3 2013/11/13 15:51:34 vxd9797 Exp $ 
 * 
 * Revisions: 
 *     $Log: Nurikabe.java,v $
 *     Revision 1.3  2013/11/13 15:51:34  vxd9797
 *     Final Version.
 *
 *     Revision 1.2  2013/11/13 08:51:57  vxd9797
 *     Commented and Tested.
 *
 *     Revision 1.1  2013/11/11 08:34:55  vxd9797
 *     Initial Revision
 *
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * The program implements the functionality of Nurikabe puzzle.
 * @author vxd9797
 *
 */
public class Nurikabe extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Size of puzzle
	public static final int ROWS = 6;
    public static final int COLS = 6;
	
    // Five control buttons: P1, P2, P3, Check, Reset
	private JButton buttonP1 = new JButton("P1");
    private JButton buttonP2 = new JButton("P2");
    private JButton buttonP3 = new JButton("P3");
    private JButton buttonCheck = new JButton("Check");
    private JButton buttonReset = new JButton("Reset");
    
    // Current puzzle number
    public int current = 1;
    
    // 2D arrays of numbers in puzzle
    Integer[][] buttonText1 = new Integer[ROWS][COLS];
    Integer[][] buttonText2 = new Integer[ROWS][COLS];
    Integer[][] buttonText3 = new Integer[ROWS][COLS];
    	
    // 2D arrays of buttons in current puzzle
	JButton[][] buttonGrid = new JButton[ROWS][COLS];
	
    // 2D arrays of buttons in three default puzzle
	JButton[][] buttonGrid1 = new JButton[ROWS][COLS];
	JButton[][] buttonGrid2 = new JButton[ROWS][COLS];
	JButton[][] buttonGrid3 = new JButton[ROWS][COLS];

	/**
	 * 
	 * Nurikabe constructor with GUI implementation
	 *
	 */
    public Nurikabe() {   
    	
    	// Action listener for buttons
    	ButtonListener bListener = new ButtonListener();
    	
    	// Action listener for buttons in grid
    	GridListener gListener = new GridListener();
    	
    	// Set all default puzzles
    	setP1();
    	setP2();
    	setP3();
    	    	  
    	// GUI frame
		JFrame frame = new JFrame("Nurikabe");	
		
		// Get layout for frame
		frame.setLayout(new BorderLayout());
		  	
		// First panel contains 5 buttons with action listeners
    	JPanel panel1 = new JPanel();
    	panel1.add(buttonP1);
		buttonP1.addActionListener(bListener);
		panel1.add(buttonP2);
		buttonP2.addActionListener(bListener);
		panel1.add(buttonP3);
		buttonP3.addActionListener(bListener);
		panel1.add(buttonCheck);
		buttonCheck.addActionListener(bListener);
		panel1.add(buttonReset);
		buttonReset.addActionListener(bListener);

		// Second panel for grid of buttons
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(ROWS,COLS, 2, 2));
		String temp = "";
		
		// Initialize the grid of buttons
    	for (int row = 0; row < ROWS; row++ ) {
    		for (int col = 0; col < COLS; col++ ){
    			if (buttonText1[row][col] != 0) {
    				temp = Integer.toString(buttonText1[row][col]);
    			}
    			else
    				temp = "";
    			JButton button = new JButton(temp);
    			button.setBorderPainted(false);
    			button.setContentAreaFilled(false);
    			button.setOpaque(true);
    			button.setBackground(Color.WHITE);
    	        button.addActionListener(gListener);
    	        buttonGrid[row][col] = button;
    			panel2.add(buttonGrid[row][col]);  
    		}
    	}
    	
    	// Border layout for panels
    	JPanel panel = new JPanel(new BorderLayout());
    	
    	// Set position for 2 panels
    	panel.add(panel2, BorderLayout.CENTER);
    	
    	panel.add(panel1, BorderLayout.SOUTH);
    	
    	// Add panels to frame
    	frame.add(panel);
    	
    	// Name of frame, size of frame and postion
    	frame.setTitle("Nurikabe");
    	frame.setSize(500,500);
    	frame.setLocation(100,100);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	
    } 
    
    /**
	 * Action listener control for buttons in the grid.
	 * Flip the color when the button is clicked.
	 * Except for the buttons with numbers.
	 */
    class GridListener implements ActionListener {
    	
		public void actionPerformed(ActionEvent e) {
			JButton newButton = (JButton) e.getSource();
			// Check if the buttons have numbers
			if (newButton.getText() == null || newButton.getText() == "") {
				// Check current color
				if (newButton.getBackground() == Color.BLACK) {
					newButton.setBorderPainted(false);
	    			newButton.setContentAreaFilled(false);
					newButton.setOpaque(true);
					newButton.setBackground(Color.WHITE);
				}
				else
				{
					newButton.setBorderPainted(false);
	    			newButton.setContentAreaFilled(false);
					newButton.setOpaque(true);
					newButton.setBackground(Color.BLACK);
				}
			}
		}
    } // GridListener
    
    /**
	 * Action listener control for 5 bottom buttons
	 * P1, P2, P3: Set to specific puzzle
	 * Check: Check the result
	 * Reset: Clear the puzzle
	 */
    class ButtonListener implements ActionListener {

    	GridListener gListener = new GridListener();
    	public void actionPerformed(ActionEvent event) {
    		// Set to puzzle P1 or P2 or P3
            if (event.getSource().equals(buttonP1)) {
            	current = 1;
            	reset();
            	String temp1;
            	for (int row = 0; row < ROWS; row++ ) {
            		for (int col = 0; col < COLS; col++ ){
            			if (buttonText1[row][col] != 0) {
            				temp1 = Integer.toString(buttonText1[row][col]);
            			}
            			else
            				temp1 = "";
            			buttonGrid[row][col].setText(temp1);
            			buttonGrid[row][col].setBorderPainted(false);
            			buttonGrid[row][col].setContentAreaFilled(false);
            			buttonGrid[row][col].setOpaque(true);
            			buttonGrid[row][col].setBackground(Color.WHITE);
                  	}
            	}
            } 
            else if (event.getSource().equals(buttonP2)) {
            	current = 2;
            	reset();
            	String temp2;
            	for (int row = 0; row < ROWS; row++ ) {
            		for (int col = 0; col < COLS; col++ ){
            			if (buttonText2[row][col] != 0) {
            				temp2 = Integer.toString(buttonText2[row][col]);
            			}
            			else
            				temp2 = "";
            			buttonGrid[row][col].setText(temp2);
            			buttonGrid[row][col].setBorderPainted(false);
            			buttonGrid[row][col].setContentAreaFilled(false);
            			buttonGrid[row][col].setOpaque(true);
            			buttonGrid[row][col].setBackground(Color.WHITE);
            		}
            	}
            }
            else if (event.getSource().equals(buttonP3)) {
            	current = 3;
            	reset();
            	String temp3;
            	for (int row = 0; row < ROWS; row++ ) {
            		for (int col = 0; col < COLS; col++ ){
            			if (buttonText3[row][col] != 0) {
            				temp3 = Integer.toString(buttonText3[row][col]);
            			}
            			else
            				temp3 = "";
            			buttonGrid[row][col].setText(temp3);
            			buttonGrid[row][col].setBorderPainted(false);
            			buttonGrid[row][col].setContentAreaFilled(false);
            			buttonGrid[row][col].setOpaque(true);
            			buttonGrid[row][col].setBackground(Color.WHITE);
            		}
            	}
            }
            // Check the result and print out a confirmation statement
            else if (event.getSource().equals(buttonCheck)) {
            	int count = 0;
            	
            	// New frame for the result
            	JFrame frame = new JFrame("Result");
        		LayoutManager layout = new BorderLayout();
        		frame.setLayout(layout);
        		frame.setSize(100,100);
            	frame.setLocation(300,300);
        		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		
        		// Check if the current puzzle is P1 or P2 or O3
            	if (current == 1) {
            		for (int row = 0; row < ROWS; row++ ) {
                		for (int col = 0; col < COLS; col++ ){
                			if (buttonGrid[row][col].getBackground().equals(buttonGrid1[row][col].getBackground()))
                			{
                				count++;
                			}	
                		}
                	}
            	}
            	else if (current == 2) {
            		for (int row = 0; row < ROWS; row++ ) {
                		for (int col = 0; col < COLS; col++ ){
                			if (buttonGrid[row][col].getBackground().equals(buttonGrid2[row][col].getBackground()))
                			{
                				count++;
                			}	
                		}
                	}
            	}
            	else
            	{
            		for (int row = 0; row < ROWS; row++ ) {
                		for (int col = 0; col < COLS; col++ ){
                			if (buttonGrid[row][col].getBackground().equals(buttonGrid3[row][col].getBackground()))
                			{
                				count++;
                			}	
                		}
                	}  		
            	}    
            	
            	// Check the result to see all of buttons matched
            	if (count != 36)
        		{
        			frame.add(new JLabel("You lose.", JLabel.CENTER), BorderLayout.CENTER);
        		}
        		else
        		{
        			frame.add(new JLabel("You win.", JLabel.CENTER), BorderLayout.CENTER);
        		}
        		frame.setVisible(true);
            }
            // Reset the puzzle so user can start over
            else if (event.getSource().equals(buttonReset)) {
            	reset();
            }
        } // actionPerformed
    } // ButtonListener
    
    /**
   	 * Initialize the puzzle P1 with correct numbers and result
   	 */
    public void setP1() {

    	// Initialize puzzle with 0 and white color
    	for (int row = 0; row < ROWS; row++ ) {

    		for (int col = 0; col < COLS; col++ ){

    			buttonText1[row][col] = 0;

    			JButton button = new JButton();
    			button.setBorderPainted(false);
    			button.setContentAreaFilled(false);
    			button.setOpaque(true);
    			button.setBackground(Color.WHITE);
    			buttonGrid1[row][col] = button; 
    		}
    	}
    	
    	// Set specific numbers
    	buttonText1[0][1] = 2;
		buttonText1[1][2] = 3;
		buttonText1[2][0] = 5;
		buttonText1[5][1] = 2;
		buttonText1[5][3] = 5;
    	
		// Black out the specific buttons
		buttonGrid1[0][2].setBackground(Color.BLACK);
		buttonGrid1[0][3].setBackground(Color.BLACK);
		buttonGrid1[0][4].setBackground(Color.BLACK);
		buttonGrid1[0][5].setBackground(Color.BLACK);
		buttonGrid1[1][0].setBackground(Color.BLACK);
		buttonGrid1[1][1].setBackground(Color.BLACK);
		buttonGrid1[1][5].setBackground(Color.BLACK);
		buttonGrid1[2][1].setBackground(Color.BLACK);
		buttonGrid1[2][2].setBackground(Color.BLACK);
		buttonGrid1[2][3].setBackground(Color.BLACK);
		buttonGrid1[2][4].setBackground(Color.BLACK);
		buttonGrid1[2][5].setBackground(Color.BLACK);
		buttonGrid1[3][4].setBackground(Color.BLACK);
		buttonGrid1[4][0].setBackground(Color.BLACK);
		buttonGrid1[4][1].setBackground(Color.BLACK);
		buttonGrid1[4][2].setBackground(Color.BLACK);
		buttonGrid1[4][3].setBackground(Color.BLACK);
		buttonGrid1[4][4].setBackground(Color.BLACK);
		buttonGrid1[5][2].setBackground(Color.BLACK);
    }
    
    /**
   	 * Initialize the puzzle P2 with correct numbers and result
   	 */
    public void setP2() {
    	
    	// Initialize puzzle with 0 and white color
    	for (int row = 0; row < ROWS; row++ ) {

    		for (int col = 0; col < COLS; col++ ){

    			buttonText2[row][col] = 0;
    			
    			JButton button = new JButton();
    			button.setBorderPainted(false);
    			button.setContentAreaFilled(false);
    			button.setOpaque(true);
    			button.setBackground(Color.WHITE);
    			buttonGrid2[row][col] = button; 
    		}
    	}	
    	
    	// Set specific numbers
    	buttonText2[0][0] = 1;
		buttonText2[0][5] = 3;
		buttonText2[1][3] = 3;
		buttonText2[2][2] = 3;
		buttonText2[3][3] = 3;
		buttonText2[5][1] = 2;
		
		// Black out the specific buttons
		buttonGrid2[0][1].setBackground(Color.BLACK);
		buttonGrid2[0][4].setBackground(Color.BLACK);
		buttonGrid2[1][0].setBackground(Color.BLACK);
		buttonGrid2[1][1].setBackground(Color.BLACK);
		buttonGrid2[1][2].setBackground(Color.BLACK);
		buttonGrid2[1][4].setBackground(Color.BLACK);
		buttonGrid2[2][0].setBackground(Color.BLACK);
		buttonGrid2[2][3].setBackground(Color.BLACK);
		buttonGrid2[2][4].setBackground(Color.BLACK);
		buttonGrid2[3][0].setBackground(Color.BLACK);
		buttonGrid2[3][2].setBackground(Color.BLACK);
		buttonGrid2[3][4].setBackground(Color.BLACK);
		buttonGrid2[3][5].setBackground(Color.BLACK);
		buttonGrid2[4][0].setBackground(Color.BLACK);
		buttonGrid2[4][1].setBackground(Color.BLACK);
		buttonGrid2[4][2].setBackground(Color.BLACK);
		buttonGrid2[4][5].setBackground(Color.BLACK);
		buttonGrid2[5][2].setBackground(Color.BLACK);
		buttonGrid2[5][3].setBackground(Color.BLACK);
		buttonGrid2[5][4].setBackground(Color.BLACK);
		buttonGrid2[5][5].setBackground(Color.BLACK);

    }
    
    /**
   	 * Initialize the puzzle P3 with correct numbers and result
   	 */
    public void setP3() {
    	
    	// Initialize puzzle with 0 and white color
    	for (int row = 0; row < ROWS; row++ ) {

    		for (int col = 0; col < COLS; col++ ){

    			buttonText3[row][col] = 0;
    			JButton button = new JButton();
    			button.setBorderPainted(false);
    			button.setContentAreaFilled(false);
    			button.setOpaque(true);
    			button.setBackground(Color.WHITE);
    			buttonGrid3[row][col] = button; 
    		}
    	}
    	
    	// Set specific numbers
    	buttonText3[0][2] = 9;
		buttonText3[0][4] = 7;
		buttonText3[2][2] = 2;
		buttonText3[4][4] = 2;
    	
		// Black out the specific buttons
    	buttonGrid3[0][3].setBackground(Color.BLACK);
    	buttonGrid3[1][1].setBackground(Color.BLACK);
    	buttonGrid3[1][2].setBackground(Color.BLACK);
    	buttonGrid3[1][3].setBackground(Color.BLACK);
    	buttonGrid3[2][1].setBackground(Color.BLACK);
    	buttonGrid3[2][3].setBackground(Color.BLACK);
    	buttonGrid3[3][1].setBackground(Color.BLACK);
    	buttonGrid3[3][3].setBackground(Color.BLACK);
    	buttonGrid3[3][4].setBackground(Color.BLACK);
    	buttonGrid3[4][1].setBackground(Color.BLACK);
    	buttonGrid3[4][2].setBackground(Color.BLACK);
    	buttonGrid3[4][5].setBackground(Color.BLACK);
    	buttonGrid3[5][2].setBackground(Color.BLACK);
    	buttonGrid3[5][3].setBackground(Color.BLACK);
    	buttonGrid3[5][4].setBackground(Color.BLACK);
    	buttonGrid3[5][5].setBackground(Color.BLACK);
    }
    
    /**
   	 * Reset the current puzzle
   	 */
    public void reset() {
    	
    	// White out all of black buttons
    	for (int row = 0; row < ROWS; row++ ) {

    		for (int col = 0; col < COLS; col++ ){

    			buttonGrid[row][col].setBorderPainted(false);
    			buttonGrid[row][col].setContentAreaFilled(false);
    			buttonGrid[row][col].setOpaque(true);
    			buttonGrid[row][col].setBackground(Color.WHITE);
    		}
    	}
    }

    /**
   	 * Main function: Run the Nurikabe puzzle
   	 */
    public static void main(String args[]) {
    	new Nurikabe();   
    } // main

}
