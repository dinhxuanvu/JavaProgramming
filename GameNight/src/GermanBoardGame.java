/*
 * GermanBoardGame.java
 *
 * Version: 
 *	$Id: GermanBoardGame.java,v 1.1 2013/10/02 04:17:04 vxd9797 Exp $
 *
 * Revisions:
 *	$Log: GermanBoardGame.java,v $
 *	Revision 1.1  2013/10/02 04:17:04  vxd9797
 *	Initial Revision
 *
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * GermanBoardGame class represents German board games. 
 * These games are like regular board games with two exceptions. 
 * They never rely on any luck, and only people over 10 years of age play them.
 * 
 * @author vxd9797
 */

public class GermanBoardGame extends BoardGame {
	
	/**
	 * Constructor, takes two args to set the instance variables
	 * @param name Name of the game
	 * @param np Number of players that can play the game at once
	 */
	public GermanBoardGame(String n, int np) {
		super(n, np, 0);
	}
	
	/**
	 * Chooses players to play the game at random, but will not choose any player age 10 or under.
	 * @param p ArrayList of players
	 * @param num Number of players in the list
	 */
	public void pickPlayers(ArrayList<Player> p, int num) {
		
		System.out.println("Picking players for " + name);
		players = new ArrayList<Player>(num);
		
		Random random = new Random();
		
		if (num > totalPlayers) {
			num = totalPlayers;
		}
		
		for (int i = 0; i < num; i++) {
			
			int randomNum;
			
			do {
				randomNum = random.nextInt(p.size());
			} while (isPlaying(p.get(randomNum)) || p.get(randomNum).getAge() < 10);
			
			players.add(p.get(randomNum));
			
			System.out.println(p.get(randomNum).getName());
			
			p.get(randomNum).play();
			
		}		
	}
}
