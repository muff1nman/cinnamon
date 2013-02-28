package boardTesting;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import board.BadConfigFormatException;
import board.Board;
import board.RoomCell;

public class BoardTests {
	Board newBoard;
	@BeforeClass
	public void beforeClass() throws Exception {
		newBoard = new Board();
		newBoard.loadConfigFiles();
	}

	@Test
	public void testRoomLegend() {
		Assert.assertEquals(10,newBoard.getRooms().size());
		Assert.assertEquals("Conservatory",newBoard.getRooms().get('C'));
		Assert.assertEquals("Kitchen", newBoard.getRooms().get('K'));
		Assert.assertEquals("Ballroom", newBoard.getRooms().get('B'));
		Assert.assertEquals("Billiard Room", newBoard.getRooms().get('R'));
		Assert.assertEquals("Library", newBoard.getRooms().get('L'));
		Assert.assertEquals("Study", newBoard.getRooms().get('S'));
		Assert.assertEquals("Dining Room", newBoard.getRooms().get('D'));
		Assert.assertEquals("Lounge", newBoard.getRooms().get('O'));
		Assert.assertEquals("Hall", newBoard.getRooms().get('H'));
		Assert.assertEquals("Closet", newBoard.getRooms().get('C'));
	}
	@Test
	public void testBoardConfiguration() {
		Assert.assertEquals(21, newBoard.getNumRows());
		Assert.assertEquals(26, newBoard.getNumColumns());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, newBoard.GetRoomCellAt(0, 15).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, newBoard.GetRoomCellAt(1, 5).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, newBoard.GetRoomCellAt(5, 23).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.UP, newBoard.GetRoomCellAt(12, 10).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, newBoard.GetRoomCellAt(13, 7).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.UP, newBoard.GetRoomCellAt(9, 16).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, newBoard.GetRoomCellAt(10, 24).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, newBoard.GetRoomCellAt(17, 3).getDoorDirection());
		
		// test # of doors
		int doorways = 0;
		for(int i = 0; i < newBoard.getNumRows(); ++i) {
			for(int j = 0; j < newBoard.getNumColumns(); ++j) {
				if(newBoard.GetRoomCellAt(i, j).isDoorway()) {
					doorways++;
				}
			}
		}
		Assert.assertEquals(21, doorways);
	}
	@Test
	public void testCorrectInitial() {
		// maybe I'm thinking of this wrong...
		// do we need to set a getter for
		// roomClassifier to do this test?
		fail("not implemented");
	}
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(0, newBoard.calcIndex(0,0));
		Assert.assertEquals(25, newBoard.calcIndex(0,25));
		Assert.assertEquals(28, newBoard.calcIndex(1, 3));
		Assert.assertEquals(545, newBoard.calcIndex(21, 25));
		Assert.assertEquals(520, newBoard.calcIndex(21, 0));
	}
	@Test (expected = BadConfigFormatException.class)
	public void testExceptionThrown() throws BadConfigFormatException {
		// test exception throwing
	}

}
