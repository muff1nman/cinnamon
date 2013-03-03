package boardTesting;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import board.Board;
import board.BoardCell;

public class AdjacencyTargets {
	public static Board newBoard;
	@BeforeClass
	public static void doOnce() throws Exception {
		newBoard = new Board("RoomLayout.csv","legend.txt");
		newBoard.loadConfigFiles();
		newBoard.calcAdjacencies();
	}
	// locations that are at each edge of the board (4)
	@Test
	public void edgeRight() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(1,25));
		Assert.assertEquals(0,testList.size());
	}
	@Test
	public void edgeUp() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(0,14));
		Assert.assertEquals(2,testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(1,14)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(0,15)));
	}
	@Test
	public void edgeLeft() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(9, 0));
		Assert.assertEquals(1,testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(9,1)));
	}
	@Test
	public void edgeDown() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(20,11));
		Assert.assertEquals(3,testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(20,10)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(20,12)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(19,11)));
	}
	// locations that are doorways (2)
	@Test
	public void door1() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(8, 3));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(8,4)));
	
	}
	@Test
	public void door2() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(6,  5));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(7,5)));
	}
	// locations that are adjacent to a doorway with needed direction (2)
	@Test
	public void enterAtDoor2() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(1, 6)); // W between CR and RL
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(1, 5))); // move into conservatory
		Assert.assertTrue(testList.contains(newBoard.calcIndex(1, 7))); // move into billiard room
		Assert.assertTrue(testList.contains(newBoard.calcIndex(0, 6))); // move up on walkway
		Assert.assertTrue(testList.contains(newBoard.calcIndex(2, 6))); // move down on walkway
	}
	@Test
	public void enterAtDoorUp() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(9, 3));
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(8, 3)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(9, 2)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(9, 4)));
	}
	// locations that are beside a room cell that is not a doorway (2)
	@Test
	public void hallwayUpDown() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(19, 4));
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(20, 4)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(18, 4)));
	}
	@Test
	public void hallwayLeftRight() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(6, 24));
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(6, 23)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(6, 25)));
	}
	// Locations with only walkways as adjacent locations (1)
	@Test
	public void hallwayCorner() {
		LinkedList<Integer> testList = newBoard.getAdjList(newBoard.calcIndex(18, 9));
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(newBoard.calcIndex(19, 9)));
		Assert.assertTrue(testList.contains(newBoard.calcIndex(18, 10)));
	}
	// targets along walkways (4)
	@Test
	public void targets1() {
		newBoard.startTargets(newBoard.calcIndex(18,9), 2);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(3,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(18,11)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(19,10)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(20,9)));
	}
	@Test
	public void targets2() {
		newBoard.startTargets(newBoard.calcIndex(19,14), 1);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(3,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(20,14)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(18,14)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(19,13)));

	}
	@Test
	public void target3() {
		newBoard.startTargets(newBoard.calcIndex(9,0), 4);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(1,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(9,3)));
	}
	@Test
	public void targets4() {
		newBoard.startTargets(newBoard.calcIndex(6,14), 3);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(4,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(4,15)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(3,14)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(8,15)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(9,14)));
	}
	// targets that allow the user to enter a room (2)
	@Test
	public void targetEnter1() {
		newBoard.startTargets(newBoard.calcIndex(0,14), 2);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(3,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(0,15)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(1,13)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(2,14)));
	}
	@Test
	public void targetEnter2() {
		newBoard.startTargets(newBoard.calcIndex(20,19), 4);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(3,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(18,20)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(17,20)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(16,19)));
	}
	// targets calculated when leaving a room (2)
	@Test
	public void targetLeave1() {
		newBoard.startTargets(newBoard.calcIndex(1,5), 2);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(3,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(1,7)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(0,6)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(2,6)));
	}
	@Test
	public void targetLeave2() {
		newBoard.startTargets(newBoard.calcIndex(12,10), 5);
		Set<BoardCell> testTargets = newBoard.getTargets();
		Assert.assertEquals(2,testTargets.size());
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(11,14)));
		Assert.assertTrue(testTargets.contains(newBoard.calcIndex(11,6)));
	}
}
