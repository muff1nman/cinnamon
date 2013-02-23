package board;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	final int COLUMNS = 4;
	final int ROWS = 4;
	private Map<Integer, LinkedList<Integer>> adjacencyLists;
	public IntBoard() {
		adjacencyLists = new HashMap<Integer, LinkedList<Integer>>();
	}
	public void calcAdjacencies() {
		LinkedList<Integer> adjacency;
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLUMNS; ++j) {
				adjacency = new LinkedList<Integer>();
				if(calcIndex(i, j + 1) != -1)
					adjacency.add(calcIndex(i,j+1));
				if(calcIndex(i, j - 1) != -1)
					adjacency.add(calcIndex(i,j-1));
				if(calcIndex(i + 1, j) != -1)
					adjacency.add(calcIndex(i + 1, j));
				if(calcIndex(i - 1, j) != -1)
					adjacency.add(calcIndex(i - 1, j));
				adjacencyLists.put(calcIndex(i, j), adjacency);
			}
		}

	}
	public void startTargets(int location, int steps) {
		// TODO Auto-generated method stub

	}
	public Set<Integer> getTargets() {
		return null;
		// TODO Auto-generated method stub
		
	}
	public LinkedList<Integer> getAdjList(int location) {
		return adjacencyLists.get(location);
		// TODO Auto-generated method stub

	}
	public int calcIndex(int row, int col) {
		if (row >= ROWS) return -1;
		else if (col >= COLUMNS) return -1;
		else return col + (row * COLUMNS);
	}
	
}
