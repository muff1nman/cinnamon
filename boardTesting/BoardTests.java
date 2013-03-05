/*
 * JUnit Test Case: BoardTests
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class encompasses the testing of the main clue board implementation
 * Relies on the JUnit 4 Test Suite
 */
package boardTesting;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import board.BadConfigFormatException;
import board.Board;
import board.RoomCell;

// BoardTests class. Allows for testing using JUnit 4
public class BoardTests {
	
	// Create a new board, static so that it may be used across all functions
	static Board newBoard;
	
	// BeforeClass method. Runs at the start of the test suite so that the board is properly initialized before the tests are run.
	// Can throw BadConfigFormatException
	@BeforeClass
	public static void beforeClass() throws BadConfigFormatException {
		
		//Instantiate the board, and load / apply the config files.
		newBoard = new Board("RoomLayout.csv","legend.txt");
		newBoard.loadConfigFiles();
		
	}
	
	// Testing Suite for the RoomLegend.
	// Assures the program reads the legend config file correctly and
	@Test
	public void testRoomLegend() {
		Assert.assertEquals(11,newBoard.getRooms().size());
		Assert.assertEquals("Conservatory",newBoard.getRooms().get('C'));
		Assert.assertEquals("Kitchen", newBoard.getRooms().get('K'));
		Assert.assertEquals("Ballroom", newBoard.getRooms().get('B'));
		Assert.assertEquals("Billiard Room", newBoard.getRooms().get('R'));
		Assert.assertEquals("Library", newBoard.getRooms().get('L'));
		Assert.assertEquals("Study", newBoard.getRooms().get('S'));
		Assert.assertEquals("Dining Room", newBoard.getRooms().get('D'));
		Assert.assertEquals("Lounge", newBoard.getRooms().get('O'));
		Assert.assertEquals("Walkway", newBoard.getRooms().get('W'));
		Assert.assertEquals("Closet", newBoard.getRooms().get('X'));
	}
	
	/*
	 * Testing suite for the BoardConfiguration
	 * Tests: 	correct number of rows
	 *		  	correct number of columns
	 *		  	two cells of each door direction
	 *			the number of doorways is correct
	 */
	@Test
	public void testBoardConfiguration() {
		
		// Testing the amount of rows and cols are correct
		Assert.assertEquals(21, newBoard.getNumRows());
		Assert.assertEquals(26, newBoard.getNumColumns());
		
		// Testing that the door directions are correct
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, newBoard.getRoomCellAt(0, 15).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, newBoard.getRoomCellAt(1, 5).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, newBoard.getRoomCellAt(5, 23).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.UP, newBoard.getRoomCellAt(12, 10).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, newBoard.getRoomCellAt(13, 7).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.UP, newBoard.getRoomCellAt(9, 16).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, newBoard.getRoomCellAt(10, 24).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, newBoard.getRoomCellAt(17, 3).getDoorDirection());

		// Testing the number of doors is correct
		int doorways = 0;
		for(int i = 0; i < newBoard.getNumRows(); ++i) {
			for(int j = 0; j < newBoard.getNumColumns(); ++j) {
				if(newBoard.getCellAt(i, j).isDoorway()) {
					doorways++;
				}
			}
		}
		Assert.assertEquals(20, doorways);
	}
	// Testing suite for assuring that the initial of the cells is correctly read and displayed on the board
	@Test
	public void testCorrectInitial() {
		Assert.assertEquals('C', newBoard.getRoomCellAt(0, 0).getRoomClassifier());
		Assert.assertEquals('R', newBoard.getRoomCellAt(0, 8).getRoomClassifier());
		Assert.assertEquals('R', newBoard.getRoomCellAt(1, 7).getRoomClassifier());
		Assert.assertEquals('D', newBoard.getRoomCellAt(12, 10).getRoomClassifier());
		Assert.assertEquals('K', newBoard.getRoomCellAt(17, 3).getRoomClassifier());
		Assert.assertEquals('O', newBoard.getRoomCellAt(20, 25).getRoomClassifier());
		Assert.assertEquals('L', newBoard.getRoomCellAt(15, 18).getRoomClassifier());
		Assert.assertEquals('S', newBoard.getRoomCellAt(4, 15).getRoomClassifier());
		Assert.assertEquals('B', newBoard.getRoomCellAt(0, 25).getRoomClassifier());
		Assert.assertEquals('K', newBoard.getRoomCellAt(20, 0).getRoomClassifier());
	}
	
	// Testing the calcIndex function yields a correct value
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(0, newBoard.calcIndex(0,0));
		Assert.assertEquals(25, newBoard.calcIndex(0,25));
		Assert.assertEquals(29, newBoard.calcIndex(1, 3));
		Assert.assertEquals(-1, newBoard.calcIndex(21, 25));
		Assert.assertEquals(520, newBoard.calcIndex(20, 0));
		Assert.assertEquals(545, newBoard.calcIndex(20, 25));
	}
	
	// Testing that an exception is thrown properly when the config formats are wrong
	// Throws: BadConfigFormatException (used for testing)
	@Test (expected = BadConfigFormatException.class) // Make sure we have this class at the end of the function instantiated
	public void testExceptionThrown() throws BadConfigFormatException  {
		// Try to make a bad board
		Board otherBoard = new Board("BadFilename", "Invalid;'[]Filename,.,.()^*&");
		
		// Try to call the config functions on a bad board.
		// Exception will be thrown
		otherBoard.loadRoomConfig();
		otherBoard.loadBoardConfig();
	}

}
