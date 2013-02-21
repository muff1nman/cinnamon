package experiment;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import board.IntBoard;

public class IntBoardTests {
	IntBoard board;

	@Before
	public void setUp() throws Exception {
		board = new IntBoard();
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
		// TODO Auto-generated method stub
		fail("Not implemented ");
	}

	@Test
	public void testAdjacency8() {
		// TODO Auto-generated method stub
		fail("Not implemented");
	}
	@Test
	public void testAdjacency7() {
		// TODO Auto-generated method stub
		fail("Not implemented");
	}
	@Test
	public void testAdjacency5() {
		// TODO Auto-generated method stub
		fail("Not implemented");
	}
	@Test
	public void testAdjacency10() {
		// TODO Auto-generated method stub
		fail("Not implemented");
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
		// TODO Auto-generated method stub
		fail("Not implemented");
	}

}
