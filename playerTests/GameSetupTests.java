package playerTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameSetupTests {

	@BeforeClass
	public void setup() {
		
	}
	
	// Each tests name, color, starting location
	@Test
	public void testLoadingPeople() {
		// Tests person at beginning
		// Tests arbitrary middle person
		// Tests person at end
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
		// Tests all cardds are dealt
		// Tests all players have within 1 card of each other
		// Tests one card is not given to multiple players
	}

}
