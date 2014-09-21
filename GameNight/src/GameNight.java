
/*
 * GameNight.java
 *
 * Version: 
 *	$Id: GameNight.java,v 1.2 2013/10/02 04:41:25 vxd9797 Exp $
 *
 * Revisions:
 *	$Log: GameNight.java,v $
 *	Revision 1.2  2013/10/02 04:41:25  vxd9797
 *	Final Version
 *
 *	Revision 1.1  2013/10/02 04:17:09  vxd9797
 *	Initial Revision
 *
 */

import java.util.ArrayList;

/**
 * Uses the Player class and Game and its subclasses to simulate a night of game-playing.
 * @author vxd9797
 */

public class GameNight {

	/**
	 * Main function to perform games with players.
	 * Create and populate a list of at least 5 players.
	 * Create and populate a list of at least 5 games, using each subclass of Game at least once, and pick players for each game.
	 * Use a loop to play each game exactly once.
	 * Use a loop to print out each Player's ending stats and determine the big winner of the night.
	 * Print out the big winner.
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		
		// Create a list of players
		ArrayList<Player> pList = new ArrayList<Player>();
		pList.add(new Player("Vu", 17, 17, 17, 20));
		pList.add(new Player("Dan", 19, 11, 15, 17));
		pList.add(new Player("Avery", 15, 12, 14, 15));
		pList.add(new Player("Alex", 15, 18, 11, 22));
		pList.add(new Player("Amanda", 17, 18, 16, 9));
		pList.add(new Player("Neil", 17, 9, 16, 21));
		pList.add(new Player("Peter", 15, 19, 12, 5));
		pList.add(new Player("Cory", 17, 14, 16, 19));

		// Create a list of games
		ArrayList<Game> gList = new ArrayList<Game>();
		gList.add(new BoardGame("Brazil", 4, 5.5));
		gList.add(new ConsoleGame("Japan", 5, true));
		gList.add(new TeamCardGame("Australia"));
		gList.add(new GermanBoardGame("Italia", 3));
		gList.add(new ConsoleGame("Egypt", 7, false));
		gList.add(new ConsoleGame("Mexico", 4, false));
		gList.add(new TeamCardGame("Norway"));
		gList.add(new GermanBoardGame("Canada", 5));
		gList.add(new ConsoleGame("South Africa", 6, true));

		
		
		// Pick players for each game
		gList.get(0).pickPlayers(pList);
		gList.get(1).pickPlayers(pList, 2);
		gList.get(2).pickPlayers(pList, 7);
		gList.get(3).pickPlayers(pList);
		gList.get(4).pickPlayers(pList, 3);
		gList.get(5).pickPlayers(pList, 6);
		gList.get(6).pickPlayers(pList, 4);
		gList.get(7).pickPlayers(pList);
		gList.get(8).pickPlayers(pList, 5);
		
		// Play games
		for (int i = 0; i < gList.size(); i++) {
			gList.get(i).play();
		}
		
		int winnerWins = 0;
		Player winner = null;
		
		// Determine the winner for game night
		for (int x = 0; x < pList.size(); x++) {
			System.out.println(pList.get(x).toString());
			if (pList.get(x).getWins() > winnerWins) {
				winnerWins = pList.get(x).getWins();
				winner = pList.get(x);
			}
		}
		
		// Print out the big winner
		System.out.println("The GameNight winner is " + winner.getName());
	}

}
