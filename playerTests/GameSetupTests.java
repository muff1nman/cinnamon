package playerTests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import misc.ClueGame;
import misc.ComputerPlayer;
import misc.HumanPlayer;
import misc.Player;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameSetupTests {
	
	ClueGame testGame;

	@BeforeClass
	public void setup() {
		testGame = new ClueGame();
		testGame.loadConfigFiles();
	}
	
	// Each tests name, color, starting location
	@Test
	public void testLoadingPeople() {
		Assert.assertEquals(new HumanPlayer("Bob Lob Lah","Yellow",9,0), testGame.getBob());
		// Tests person at beginning
		Assert.assertEquals(new ComputerPlayer("Colonel Mustard","Orange",0,19), testGame.getCpuPlayers().get(0));
		// Tests person at end
		Assert.assertEquals(new ComputerPlayer("Mr. White","Blue",0,6), testGame.getCpuPlayers().get(3));
	}
	
	@Test
	public void testLoadingCards() {
		// Tests Deck has proper number of cards
		// Tests deck contains proper number of each type of card
		// Tests one room
		// Tests one weapon
		// Tests one person
	}
	
	@Test
	public void testDealingCards() {
		// Tests all cards are dealt
		// Tests all players have within 1 card of each other
		// Tests one card is not given to multiple players
	}

}
