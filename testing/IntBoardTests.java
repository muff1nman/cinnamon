package testing;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import board.IntBoard;

public class IntBoardTests {
	IntBoard board;

	@Before
	public void setUp() {
		board = new IntBoard();
		board.calcAdjacencies();
	}

	@Test
	public void testAdjacency0() {
		LinkedList<Integer> testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}

	@Test
	public void testAdjacency15() {
		LinkedList<Integer> testList = board.getAdjList(15);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertEquals(2, testList.size());
	}

	@Test
	public void testAdjacency8() {
		LinkedList<Integer> testList = board.getAdjList(8);
		for (Integer i : testList) {
			System.out.println(i);
		}
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(12));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(3, testList.size());
	}
	@Test
	public void testAdjacency7() {
		LinkedList<Integer> testList = board.getAdjList(7);
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(11));
		Assert.assertEquals(3, testList.size());
	}
	@Test
	public void testAdjacency5() {
		LinkedList<Integer> testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(4, testList.size());
	}
	@Test
	public void testAdjacency10() {
		LinkedList<Integer> testList = board.getAdjList(10);
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
	}
	@Test
	public void testTargets0_3() {
		board.startTargets(0, 3);
		Set<Integer> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}

	@Test
	public void testTargets15_3() {
		board.startTargets(15, 3);
		Set<Integer> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(11));
	}

	@Test
	public void testTargets8_6() {
		board.startTargets(8, 6);
		Set<Integer> targets = board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(0));	
	}

	@Test
	public void testTargets6_4() {
		board.startTargets(6, 4);
		Set<Integer> targets = board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(3));
	}

	@Test
	public void testcalcIndex() {
		Assert.assertEquals(0,board.calcIndex(0, 0));
		Assert.assertEquals(3,board.calcIndex(0, 3));
		Assert.assertEquals(4,board.calcIndex(1, 0));
		Assert.assertEquals(8,board.calcIndex(2, 0));
		Assert.assertEquals(10,board.calcIndex(2, 2));
		Assert.assertEquals(13,board.calcIndex(3, 1));
		Assert.assertEquals(15,board.calcIndex(3, 3));
	}

}
