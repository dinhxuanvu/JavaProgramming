/*
 * ConsoleGame.java
 *
 * Version: 
 *	$Id: ConsoleGame.java,v 1.1 2013/10/02 04:17:05 vxd9797 Exp $
 *
 * Revisions:
 *	$Log: ConsoleGame.java,v $
 *	Revision 1.1  2013/10/02 04:17:05  vxd9797
 *	Initial Revision
 *
 */

/**
 * The ConsoleGame class represents a console game. 
 * Success at console games always depends on dexterity, and may optionally depend on intelligence as well.
 * @author vxd9797
 */

public class ConsoleGame extends Game {
	
	private boolean usesBrains;
	
	/**
	 * Constructor, takes three args to set the instance variables
	 * @param name Name of the game
	 * @param np Number of players that can play the game at once
	 * @param usesBrains Boolean value
	 */
	public ConsoleGame(String name, int np, boolean usesBrains) {
		super(name, np);
		this.usesBrains = usesBrains;
	}
	
	/**
	 * Plays the game and chooses a winner. 
	 * Winner is chosen either as the player with highest dexterity or highest (dexterity + intelligence) otherwise.
	 */
	public void play() {
		
		int bestScore = 0;
		Player winner = null;
		
		System.out.println("Playing " + name + "...");
		
		for (int i = 0; i < players.size(); i++) {
			
			int score = players.get(i).getDexterity();
			
			if (usesBrains) {
				score += players.get(i).getIntelligence();
			}
			
			if (score > bestScore) {
				bestScore = score;
				winner = players.get(i);
			}
		}
		
		System.out.println("The winner is " + winner.getName());
		winner.youWin();
	}

}
