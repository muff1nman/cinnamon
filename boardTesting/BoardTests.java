package boardTesting;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import board.BadConfigFormatException;
import board.Board;
import board.RoomCell;

public class BoardTests {
	static Board newBoard;
	@BeforeClass
	public static void beforeClass() throws Exception {
		newBoard = new Board();
		newBoard.loadConfigFiles("RoomLayout.csv","legend.txt");
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
		Assert.assertEquals("Walkway", newBoard.getRooms().get('W'));
		Assert.assertEquals("Closet", newBoard.getRooms().get('X'));
	}
	@Test
	public void testBoardConfiguration() {
		Assert.assertEquals(21, newBoard.getNumRows());
		Assert.assertEquals(26, newBoard.getNumColumns());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, newBoard.getRoomCellAt(0, 15).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, newBoard.getRoomCellAt(1, 5).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, newBoard.getRoomCellAt(5, 23).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.UP, newBoard.getRoomCellAt(12, 10).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, newBoard.getRoomCellAt(13, 7).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.UP, newBoard.getRoomCellAt(9, 16).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, newBoard.getRoomCellAt(10, 24).getDoorDirection());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, newBoard.getRoomCellAt(17, 3).getDoorDirection());

		// test # of doors
		int doorways = 0;
		for(int i = 0; i < newBoard.getNumRows(); ++i) {
			for(int j = 0; j < newBoard.getNumColumns(); ++j) {
				if(newBoard.getCellAt(i, j).isDoorway()) {
					doorways++;
				}
			}
		}
//		System.out.println(doorways);
		Assert.assertEquals(21, doorways);
	}
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
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(0, newBoard.calcIndex(0,0));
		Assert.assertEquals(25, newBoard.calcIndex(0,25));
		Assert.assertEquals(28, newBoard.calcIndex(1, 3));
		Assert.assertEquals(545, newBoard.calcIndex(21, 25));
		Assert.assertEquals(520, newBoard.calcIndex(21, 0));
	}
	@Test (expected = BadConfigFormatException.class)
	public void testExceptionThrown() throws BadConfigFormatException  {
		newBoard.loadConfigFiles("BadFilename", "Invalid;'[]Filename,.,.()^*&");
	}

}
