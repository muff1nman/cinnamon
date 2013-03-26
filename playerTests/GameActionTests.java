package playerTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import misc.Card;
import misc.ClueGame;
import misc.ComputerPlayer;
import misc.HumanPlayer;
import misc.Player;
import misc.Solution;
import misc.Suggestion;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import board.Board;
import board.BoardCell;



public class GameActionTests {
	private static ClueGame game;
	private static Board board;
	final int PERSON = 0;
	final int ROOM = 1;
	final int WEAPON = 2;
	
	private static Card mustardCard, whiteCard, knifeCard, pipeCard, kitchenCard, conservatoryCard;
	
	
	@Before
	public void setUp() {
		
		game = new ClueGame();
		board = new Board("RoomLayout.csv","legend.txt");
		board.loadConfigFiles();
		board.calcAdjacencies();
		ArrayList<Card> allCards = new ArrayList<Card>();
		mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
		allCards.add(mustardCard);
		whiteCard = new Card("Mr. White", Card.CardType.PERSON);
		allCards.add(whiteCard);
		knifeCard = new Card("Knife", Card.CardType.WEAPON);
		allCards.add(knifeCard);
		pipeCard = new Card("Pipe", Card.CardType.WEAPON);
		allCards.add(pipeCard);
		kitchenCard = new Card("Kitchen", Card.CardType.ROOM);
		allCards.add(kitchenCard);
		conservatoryCard = new Card("Conservatory", Card.CardType.ROOM);
		allCards.add(conservatoryCard);
		//set the deck to our reduced set
		game.setDeck(allCards);
		
	}
	
	
	@Test
	public void testAccusation() {
		ArrayList<Card> thisCloset = new ArrayList<Card>();
		thisCloset.add(mustardCard);
		thisCloset.add(pipeCard);
		thisCloset.add(kitchenCard);
		game.setClosetCards(thisCloset);
		Solution goodSolution = new Solution("Colonel Mustard", "Pipe", "Kitchen");
		Solution badSolution1 = new Solution("Mr. White", "pipe", "kitchen");
		Solution badSolution2 = new Solution("Colonel Mustard", "knife", "kitchen");
		Solution badSolution3 = new Solution("Colonel Mustard", "pipe", "conservatory");
		assertTrue(game.checkAccusation(goodSolution));
		assertFalse(game.checkAccusation(badSolution1));
		assertFalse(game.checkAccusation(badSolution2));
		assertFalse(game.checkAccusation(badSolution3));
		
	}
	
	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer cpuPlayer = new ComputerPlayer();
		//location with no rooms and 3 possible targets
		board.startTargets(board.calcIndex(0, 17), 2);
		int loc_2_17 = 0;
		int loc_1_18 = 0;
		int loc_0_19 = 0;
		//run lots of times to test that rooms are being randomly selected
		for (int x = 0; x < 100; x++) {
			BoardCell selection = cpuPlayer.pickLocation(board.getTargets());
			if (selection == board.getCellAt(2,17)) {
				loc_2_17++;
			}
			else if (selection == board.getCellAt(1,18)) {
				loc_1_18++;
			}
			else if (selection == board.getCellAt(0,19)) {
				loc_0_19++;
			}
			else {
				fail("invalid target");
			}
		}
		//check to make sure all location selections were successful
		assertEquals(100, loc_2_17 + loc_1_18 + loc_0_19);
		//make sure each target was chosen a reasonable number of times
		assertTrue(loc_2_17 > 10);
		assertTrue(loc_1_18 > 10);
		assertTrue(loc_0_19 > 10);
	}
	
	@Test
	public void testSelectUnvisitedRoom() {
		ComputerPlayer cpuPlayer = new ComputerPlayer();
		int loc_13_15 = 0;
		//location with an unvisited door in range.
		board.startTargets(board.calcIndex(15, 14), 3);
		
		//ensure it picks the correct room every time
		for(int x = 0; x<100; x++) {
			cpuPlayer.setLastRoomVisited('D');
			BoardCell selection = cpuPlayer.pickLocation(board.getTargets());
			if (selection == board.getCellAt(13, 15)) {
				loc_13_15++;
			}
		}
		//true if unvisited room was picked every time.
		Assert.assertTrue(loc_13_15 == 100);
		
	}
	
	@Test
	public void testSelectRandomIfRoomLastVisited() {
		ComputerPlayer cpuPlayer = new ComputerPlayer();
		int loc_20_19 = 0; 
		int loc_18_20 = 0;
		int loc_16_19 = 0;
		int loc_17_20 = 0;
		//location with only 1 door in range
		board.startTargets(board.calcIndex(18, 19), 2);
		cpuPlayer.setLastRoomVisited('O');
		
		for( int x = 0; x < 100; x++) {
			BoardCell selection = cpuPlayer.pickLocation(board.getTargets());
			if (selection == board.getCellAt(20, 19)) {
				loc_20_19++;
			}
			else if (selection == board.getCellAt(18, 20)) {
				loc_18_20++;
			}
			else if (selection == board.getCellAt(16, 19)) {
				loc_16_19++;
			}
			else if (selection == board.getCellAt(17, 20)) {
				loc_17_20++;
			}
			else fail("invalid targets");
		}
		//check to make sure all location selections were successful
				assertEquals(100, loc_20_19 + loc_18_20 + loc_16_19 + loc_17_20);
				//make sure each target was chosen a reasonable number of times
				assertTrue(loc_20_19 > 5);
				assertTrue(loc_18_20 > 5);
				assertTrue(loc_16_19 > 5);
				assertTrue(loc_17_20 > 5);
	}
	
	
	//only 1 possible suggestions
	@Test
	public void testComputerMakingSuggestion1() {
		ComputerPlayer cpu1 = new ComputerPlayer();
		cpu1.updateSeen(mustardCard);
		cpu1.updateSeen(pipeCard);
		cpu1.setRow(18);
		cpu1.setColumn(3);

		Suggestion suggestion = cpu1.createSuggestion(cpu1.getRow(), cpu1.getColumn(), game.getDeck(), board);
		
		//checks to see if the suggestion contains a person or weapon that has already been seen.
		assertFalse(cpu1.getKnownCards().contains(suggestion.getPerson()));
		assertFalse(cpu1.getKnownCards().contains(suggestion.getWeapon()));
		//since there is only one possible suggestion in this scenario
		assertTrue(suggestion.getPerson().equals(whiteCard));
		assertTrue(suggestion.getRoom().equals(kitchenCard));
		assertTrue(suggestion.getWeapon().equals(knifeCard));

	}
	
	//multiple possible suggestions (2 possible person cards)
	@Test
	public void testComputerMakingSuggestion2() {
		ComputerPlayer cpu1 = new ComputerPlayer();
		cpu1.updateSeen(pipeCard);
		cpu1.setRow(18);
		cpu1.setColumn(3);
		int mustard = 0;
		int white = 0;
		
		//make sure both options are chosen a reasonable amount
		for(int x = 0; x < 100; x++) {
			//createSuggestion returns an ArrayList of 3 cards. //the first entry is a person. 2nd entry is a room, and 3rd entry is a weapon
			Suggestion suggestion = cpu1.createSuggestion(cpu1.getRow(), cpu1.getColumn(), game.getDeck(), board);
			//checks to see if the suggestion contains a person or weapon that has already been seen.
			assertFalse(cpu1.getCards().contains(suggestion.getPerson()));
			assertFalse(cpu1.getCards().contains(suggestion.getWeapon()));
			//makes sure room is correct
			assertTrue(suggestion.getRoom().equals(kitchenCard));
			//makes sure weapon is correct
			assertTrue(suggestion.getWeapon().equals(knifeCard));
			if (suggestion.getPerson().equals(mustardCard)) {
				mustard++;
			}
			else if (suggestion.getPerson().equals(whiteCard)) {
				white++;
			}
			else fail("incorrect card");
		}
		//make sure totals are reasonable
		assertTrue(mustard > 10);
		assertTrue(white > 10);
	}
	

	@Test
	public void testDisprovingSuggestion() {
		// Computer Player
		ComputerPlayer playerOne = new ComputerPlayer("PlayerOne", "red", 0, 0);
		playerOne.giveCard(whiteCard);
				
		// Computer Player
		ComputerPlayer playerTwo = new ComputerPlayer("PlayerTwo", "orange", 0, 0);
		playerTwo.giveCard(mustardCard);
		playerTwo.giveCard(kitchenCard);
			
		// Human player
		HumanPlayer playerThree = new HumanPlayer("PlayerThree", "yellow", 0, 0);
		playerThree.giveCard(pipeCard);
				
		// Computer Player
		ComputerPlayer playerFour = new ComputerPlayer("PlayerFour", "blue", 0, 0);
		playerFour.giveCard(knifeCard);
		playerFour.giveCard(conservatoryCard);
		game.resetPlayers();
		game.addPlayer(playerOne);
		game.addPlayer(playerTwo);
		game.addPlayer(playerThree);
		game.addPlayer(playerFour);
		
		// Test one player, one correct match (Computer Player returns card)
		Assert.assertEquals(mustardCard, game.handleSuggestion("Colonel Mustard", "garbage1", "garbage2", playerOne));
		
		// Test one player, one correct match (Human player returns card)
		Assert.assertEquals(pipeCard, game.handleSuggestion("garbage1", "Pipe", "garbage2", playerOne));
		
		// Test player whose turn it is does not return card (Computer Player)
		Assert.assertNotSame(whiteCard, game.handleSuggestion("Mr. White", "garbage1", "garbage2", playerOne));
		
		// Test player whose turn it is does not return card (Human Player)
		Assert.assertNotSame(whiteCard, game.handleSuggestion("garbage1", "Pipe", "garbage2", playerThree));
		
		// Test one player, multiple possible matches
		int timesMustard = 0;
		int timesKitchen = 0;
		for(int i = 0; i < 100; ++i) {
			Card testCard = (Card) game.handleSuggestion("Colonel Mustard", "Kitchen", "garbage1", playerOne);
			if(testCard.equals(mustardCard)) {
				timesMustard++;
			} else if (testCard.equals(kitchenCard)) {
				timesKitchen++;
			}
		}
		Assert.assertFalse(timesMustard == 0);
		Assert.assertFalse(timesKitchen == 0);
		
		// Test all players are queried (Mix of human and computer players)
		timesMustard = 0;
		int timesPipe = 0;
		int timesConservatory = 0;
		for(int i = 0; i < 300; ++i) {
			Card testCard = (Card) game.handleSuggestion("Colonel Mustard", "Pipe", "Conservatory", playerOne);
			if(testCard.equals(mustardCard)) {
				timesMustard++;
			} else if (testCard.equals(pipeCard)) {
				timesPipe++;
			} else if (testCard.equals(conservatoryCard)) {
				timesConservatory++;
			}
		}
		Assert.assertFalse(timesMustard == 0);
		Assert.assertFalse(timesPipe == 0);
		Assert.assertFalse(timesConservatory == 0);
		
	}

}


