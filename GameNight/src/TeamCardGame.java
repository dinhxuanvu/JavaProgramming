/*
 * TeamCardGame.java
 *
 * Version: 
 *	$Id: TeamCardGame.java,v 1.1 2013/10/02 04:17:11 vxd9797 Exp $
 *
 * Revisions:
 *	$Log: TeamCardGame.java,v $
 *	Revision 1.1  2013/10/02 04:17:11  vxd9797
 *	Initial Revision
 *
 */

import java.util.ArrayList;

/**
 * TeamCardGame class represents a card game played with two teams of two.
 * @author vxd9797
 */

public class TeamCardGame extends Game {
	
	/**
	 * Constructor. Assumes all such games are played by four players.
	 * @param n String of name
	 */
	public TeamCardGame(String n) {
		super(n, 4);
	}
	
	/**
	 * Plays the game, selecting a winning team according to total team intelligence. 
	 * This is computed for each team as the team's higher intelligence value 
	 * plus twice the team's lower intelligence value.
	 */
	public void play() {
		
		// Local variables field
		int scoreA = 0;
		int scoreB = 0;
		int scoreA1 = 0;
		int scoreA2 = 0;
		int scoreB1 = 0;
		int scoreB2 = 0;
		
		// Create two teams with two member each
		ArrayList<Player> teamA = new ArrayList<Player>(2);
		ArrayList<Player> teamB = new ArrayList<Player>(2);
		
		for (int i = 0; i < players.size(); i++) {
			if ((i % 2) == 0) {
				teamA.add(players.get(i));
			}
			else
				teamB.add(players.get(i));
		}
		
		System.out.println("Playing " + name + "...");
		
		for (int i = 0; i < players.size(); i++) {
			
			if ((i % 2) == 0) {
				if (i == 0)
					scoreA1 = players.get(i).getIntelligence();
				else
					scoreA2 = players.get(i).getIntelligence();
			}
			else {
				if (i == 1)
					scoreB1 = players.get(i).getIntelligence();
				else
					scoreB2 = players.get(i).getIntelligence();
			}
				
		}
		
		// Compare the scores
		if (scoreA1 <= scoreA2)
			scoreA = scoreA2 + 2*scoreA1;
		else
			scoreA = scoreA1 + 2*scoreA2;
		
		if (scoreB1 <= scoreB2)
			scoreB = scoreB2 + 2*scoreB1;
		else
			scoreB = scoreB1 + 2*scoreB2;
		
		// Determine the winner
		if (scoreA < scoreB) {
			for (int x = 0; x < teamB.size(); x++) {
				teamB.get(x).youWin();
				System.out.println("The winner is " + teamB.get(x).getName());
			}
		}
		else {
			for (int y = 0; y < teamA.size(); y++) {
				teamA.get(y).youWin();
				System.out.println("The winner is " + teamA.get(y).getName());
			}
		}
	}
}
