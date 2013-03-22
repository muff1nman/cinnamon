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
	//private Solution solution;
	//private static Card mustardCard, whiteCard, plumCard, revolverCard, knifeCard, pipeCard, libraryCard, kitchenCard, conservatoryCard;
	private static Card mustardCard, whiteCard, knifeCard, pipeCard, kitchenCard, conservatoryCard;
	//temp stuff yo. delete this and add legit deal functionality i think.
	//ououeoeuo
	//oueiuoeuioeuioeuio
	
	@BeforeClass
	public static void setUp() {
		
		game = new ClueGame();
		board = new Board("RoomLayout.csv","legend.txt");
		board.loadConfigFiles();
		board.calcAdjacencies();
		ArrayList<Card> allCards = new ArrayList<Card>();
		mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
		allCards.add(mustardCard);
		whiteCard = new Card("Mr. White", Card.CardType.PERSON);
		allCards.add(whiteCard);
		knifeCard = new Card("knife", Card.CardType.WEAPON);
		allCards.add(knifeCard);
		pipeCard = new Card("pipe", Card.CardType.WEAPON);
		allCards.add(pipeCard);
		kitchenCard = new Card("kitchen", Card.CardType.ROOM);
		allCards.add(kitchenCard);
		conservatoryCard = new Card("conservatory", Card.CardType.ROOM);
		allCards.add(conservatoryCard);
		game.setDeck(allCards);
		
	}
	//@Before
	//public void setUp() {
	//	game = new ClueGame();
	//	//solution = new Solution();	
	//}
	
	
	@Test
	public void testAccusation() {
		Solution goodSolution = new Solution("Colonel Mustard", "pipe", "kitchen");
		Solution badSolution1 = new Solution("Mr. White", "pipe", "kitchen");
		Solution badSolution2 = new Solution("Colonel Mustard", "knife", "kitchen");
		Solution badSolution3 = new Solution("Colonel Mustard", "pipe", "conservatory");
		
		ArrayList<Card> testCards = new ArrayList<Card>();
		assertTrue(game.checkAccusation(goodSolution));
		assertFalse(game.checkAccusation(badSolution1));
		assertFalse(game.checkAccusation(badSolution2));
		assertFalse(game.checkAccusation(badSolution3));
		
	}
	
	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer cpuPlayer = new ComputerPlayer();
		//location with no rooms and 3 possible targets
		board.startTargets(0, 2);
		int loc_2 = 0;
		int loc_52 = 0;
		int loc_27 = 0;
		//run lots of times to test that rooms are being randomly selected
		for (int x = 0; x < 100; x++) {
			BoardCell selection = cpuPlayer.pickLocation(board.getTargets());
			if (selection == board.getCellAt(0, 2)) {
				loc_2++;
			}
			else if (selection == board.getCellAt(1, 1)) {
				loc_27++;
			}
			else if (selection == board.getCellAt(2, 0)) {
				loc_52++;
			}
			else {
				fail("invalid target");
			}
		}
		//check to make sure all location selections were successful
		assertEquals(100, loc_2 + loc_52 + loc_27);
		//make sure each target was chosen a reasonable number of times
		assertTrue(loc_2 > 10);
		assertTrue(loc_52 > 10);
		assertTrue(loc_27 > 10);
	}
	
	@Test
	public void testSelectUnvisitedRoom() {
		ComputerPlayer cpuPlayer = new ComputerPlayer();
		int loc_14_15 = 0;
		//location with an unvisited door in range.
		board.startTargets(board.calcIndex(15, 14), 3);
		
		//ensure it picks the correct room every time
		for(int x = 0; x<100; x++) {
			cpuPlayer.setLastRoomVisited('L');
			BoardCell selection = cpuPlayer.pickLocation(board.getTargets());
			if (selection == board.getCellAt(14, 15)) {
				loc_14_15++;
			}
		}
		//true if unvisited room was picked every time.
		assertTrue(loc_14_15 == 100);
	}
	
	@Test
	public void testSelectRandomIfRoomLastVisited() {
		ComputerPlayer cpuPlayer = new ComputerPlayer();
		int loc_19_20 = 0; 
		int loc_16_21 = 0;
		int loc_17_20 = 0;
		int loc_18_19 = 0;
		//location with only 1 door in range
		board.startTargets(board.calcIndex(18, 21), 2);
		cpuPlayer.setLastRoomVisited('O');
		
		for( int x = 0; x < 100; x++) {
			BoardCell selection = cpuPlayer.pickLocation(board.getTargets());
			if (selection == board.getCellAt(19, 20)) {
				loc_19_20++;
			}
			else if (selection == board.getCellAt(16, 21)) {
				loc_16_21++;
			}
			else if (selection == board.getCellAt(17, 20)) {
				loc_17_20++;
			}
			else if (selection == board.getCellAt(19, 20)) {
				loc_18_19++;
			}
			else fail("invalid targets");
		}
		//check to make sure all location selections were successful
				assertEquals(100, loc_19_20 + loc_16_21 + loc_17_20 + loc_18_19);
				//make sure each target was chosen a reasonable number of times
				assertTrue(loc_19_20 > 5);
				assertTrue(loc_16_21 > 5);
				assertTrue(loc_17_20 > 5);
				assertTrue(loc_18_19 > 5);
	}
	
	
	//only 1 possible suggestions
	@Test
	public void testComputerMakingSuggestion1() {
		ComputerPlayer cpu1 = new ComputerPlayer();
		cpu1.updateSeen(mustardCard);
		cpu1.updateSeen(pipeCard);
		cpu1.setLocation(board.calcIndex(18, 3));
		
		//createSuggestion returns an ArrayList of 3 cards. //the first entry is a person. 2nd entry is a room, and 3rd entry is a weapon
		ArrayList<Card> suggestion = cpu1.createSuggestion(cpu1.getLocation());
		//checks to see if the suggestion contains a person or weapon that has already been seen.
		assertFalse(cpu1.getCards().contains(suggestion.get(PERSON)));
		assertFalse(cpu1.getCards().contains(suggestion.get(WEAPON)));
		//since there is only one possible suggestion in this scenario
		assertTrue(suggestion.get(PERSON).equals(whiteCard));
		assertTrue(suggestion.get(ROOM).equals(kitchenCard));
		assertTrue(suggestion.get(WEAPON).equals(knifeCard));
	}
	
	//multiple possible suggestions (2 possible person cards)
	@Test
	public void testComputerMakingSuggestion2() {
		ComputerPlayer cpu1 = new ComputerPlayer();
		cpu1.updateSeen(pipeCard);
		cpu1.setLocation(board.calcIndex(18, 3));
		int mustard = 0;
		int white = 0;
		
		//make sure both options are chosen a reasonable amount
		for(int x = 0; x < 100; x++) {
			//createSuggestion returns an ArrayList of 3 cards. //the first entry is a person. 2nd entry is a room, and 3rd entry is a weapon
			ArrayList<Card> suggestion = cpu1.createSuggestion(cpu1.getLocation());
			//checks to see if the suggestion contains a person or weapon that has already been seen.
			assertFalse(cpu1.getCards().contains(suggestion.get(PERSON)));
			assertFalse(cpu1.getCards().contains(suggestion.get(WEAPON)));
			//makes sure room is correct
			assertTrue(suggestion.get(ROOM).equals(kitchenCard));
			//makes sure weapon is correct
			assertTrue(suggestion.get(WEAPON).equals(knifeCard));
			
			if (suggestion.get(PERSON).equals(mustardCard)) {
				mustard++;
			}
			else if (suggestion.get(PERSON).equals(whiteCard)) {
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
		ComputerPlayer playerOne = new ComputerPlayer();
		playerOne.giveCard(whiteCard);
		
		// Computer Player
		ComputerPlayer playerTwo = new ComputerPlayer();
		playerTwo.giveCard(mustardCard);
		playerTwo.giveCard(kitchenCard);
		
		// Human player
		HumanPlayer playerThree = new HumanPlayer();
		playerThree.giveCard(pipeCard);
		
		// Computer Player
		ComputerPlayer playerFour = new ComputerPlayer();
		playerFour.giveCard(knifeCard);
		playerFour.giveCard(conservatoryCard);
		
		game.addPlayer(playerOne);
		game.addPlayer(playerTwo);
		game.addPlayer(playerThree);
		game.addPlayer(playerFour);
		
		// Test one player, one correct match (Computer Player returns card)
		Assert.assertEquals(mustardCard, game.handleSuggestion("Colonel Mustard", "garbage1", "garbage2", playerOne));
		
		// Test one player, one correct match (Human player returns card)
		Assert.assertEquals(mustardCard, game.handleSuggestion("pipe", "garbage1", "garbage2", playerOne));
		
		// Test player whose turn it is does not return card (Computer Player)
		Assert.assertNotSame(whiteCard, game.handleSuggestion("Mr. White", "garbage1", "garbage2", playerOne));
		
		// Test player whose turn it is does not return card (Human Player)
		Assert.assertNotSame(whiteCard, game.handleSuggestion("pipe", "garbage1", "garbage2", playerThree));
		
		// Test one player, multiple possible matches
		int timesMustard = 0;
		int timesKitchen = 0;
		for(int i = 0; i < 100; ++i) {
			Card testCard = (Card) game.handleSuggestion("Colonel Mustard", "kitchen", "garbage1", playerOne);
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
			Card testCard = (Card) game.handleSuggestion("Colonel Mustard", "pipe", "conservatory", playerOne);
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


