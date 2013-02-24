package board;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private final static int COLUMNS = 4;
	private final static int ROWS = 4;
	private boolean visited[] = new boolean[ROWS * COLUMNS];
	private Map<Integer, LinkedList<Integer>> adjacencyLists;
	private Set<Integer> targets;
	public IntBoard() {
		adjacencyLists = new HashMap<Integer, LinkedList<Integer>>();
		visited = new boolean[ROWS * COLUMNS];
		for (int i = 0; i < ROWS * COLUMNS; ++i) {
			visited[i] = false;
		}
		targets = new HashSet<Integer>();
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
		visited[location] = true;
		calcTargets(location, steps);
	}
	private void calcTargets(int location, int steps) {
		LinkedList<Integer> adjacentCells = new LinkedList<Integer>();
		for(int adjCell : adjacencyLists.get(location)) {
			if(visited[adjCell] == false) {
				adjacentCells.add(adjCell);
			}
		}
		for(int adjCell : adjacentCells) {
			visited[adjCell] = true;
			if(steps == 1) {
				targets.add(adjCell);
			} else {
				System.out.println("adjCell " + adjCell + " steps " + (steps - 1));
				calcTargets(adjCell, steps - 1);
			}
			visited[adjCell] = false;
		}
	}
	public Set<Integer> getTargets() {
		return targets;
		
	}
	public LinkedList<Integer> getAdjList(int location) {
		return adjacencyLists.get(location);
	}
	public int calcIndex(int row, int col) {
		if (row >= ROWS) return -1;
		else if (col >= COLUMNS) return -1;
		else if (col < 0) return -1;
		else if (row < 0) return -1;
		else {
			return row * COLUMNS + col;
		}
			
	}
	
}
