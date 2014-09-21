/*
 * Game.java
 *
 * Version: 
 *	$Id: Game.java,v 1.1 2013/10/02 04:17:07 vxd9797 Exp $
 *
 * Revisions:
 *	$Log: Game.java,v $
 *	Revision 1.1  2013/10/02 04:17:07  vxd9797
 *	Initial Revision
 *
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class Game is to represent a generic game and has instance variables for the name of the game (String), 
 * number of players that can play the game (int) and who is playing the game (array of Player).
 * @author vxd9797
 */

public abstract class Game {
	
	// Variable field
	protected String name;
	protected int totalPlayers;
	protected ArrayList<Player> players;
	
	/**
	 * Constructor, takes two args to set the instance variables
	 * @param n Name of the game
	 * @param np Number of players that can play the game at once
	 */
	public Game(String n, int np) {
		this.name = n;
		this.totalPlayers = np;
	}
	
	/**
	 * Chooses players at random from the array passed in to play the game. 
	 * Will pick as many players as can play the game at once.
	 * @param p ArrayList of Player
	 */
	public void pickPlayers(ArrayList<Player> p) {
		pickPlayers(p, totalPlayers);
		
	}
	
	/**
	 * Chooses players at random from the list passed in to play the game. 
	 * Will pick the number of players given as the second parameter.
	 * @param p ArrayList of Player
	 * @param num number of players in the list
	 */
	public void pickPlayers(ArrayList<Player> p, int num) {
		
		System.out.println("Picking players for " + name);
		players = new ArrayList<Player>(num);
		
		Random random = new Random();
		
		// Check if the number of player is larger than total players
		if (num > totalPlayers) {
			num = totalPlayers;
		}
				
		for (int i = 0; i < num; i++) {
			
			int randomNum;
			
			do {
				randomNum = random.nextInt(p.size());
			} while (isPlaying(p.get(randomNum)));
			
			players.add(p.get(randomNum));
			
			System.out.println(p.get(randomNum).getName());
			
			p.get(randomNum).play();
			
		}
	}
	
	/**
	 * Checks the list of current players to see if a particular player is playing the game
	 * @param p Player
	 */
	public boolean isPlaying(Player p) {
		
		for (int x = 0; x < players.size(); x++) {
			if (players.get(x) == p) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Play the game! Since this is a generic game, no default behavior.
	 */
	abstract public void play();
	
	/**
	 * Returns a String representation name of the game.
	 */
	public String toString() {
		return name;
	}

}
