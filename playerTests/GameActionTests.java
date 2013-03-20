package playerTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import misc.Card;
import misc.ClueGame;
import misc.ComputerPlayer;
import misc.Solution;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import board.Board;
import board.BoardCell;



public class GameActionTests {
	private ClueGame game;
	private Board board;
	//private Solution solution;
	private Card mustardCard, whiteCard, knifeCard, pipeCard, kitchenCard, conservatoryCard;
	//temp stuff yo. delete this and add legit deal functionality i think.
	//ououeoeuo
	//oueiuoeuioeuioeuio
	
	@BeforeClass
	public void setUp() {
		game = new ClueGame();
		board = new Board();
		mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
		whiteCard = new Card("Mr. White", Card.CardType.PERSON);
		knifeCard = new Card("knife", Card.CardType.WEAPON);
		pipeCard = new Card("pipe", Card.CardType.WEAPON);
		kitchenCard = new Card("kitchen", Card.CardType.ROOM);
		conservatoryCard = new Card("conservatory", Card.CardType.ROOM);
		
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
			//if (selected == board.getCellAt(0, 2))
		}
	}

}
