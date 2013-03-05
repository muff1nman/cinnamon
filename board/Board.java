/*
 * Class: Board
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class is the main class pertaining to the clue board.
 * Also contains logic to calculate targets and adjacencies
 * Relies on BadConfigFormatException, BoardCell, RoomCell, and WalkwayCell
 */

package board;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// Board class body
public class Board {

	// The list of cells for the board
	private ArrayList<BoardCell> cells;

	// The map of rooms in the board
	private Map<Character,String> rooms;

	// Number of rows and columns corresponding to the board
	private int numRows;
	private int numColumns;

	// Filepaths for the configuration files
	private String csvFilepath, legendFilepath;

	// Array used to keep track of visited cells when the fucntion calcAdjacencies is called
	private boolean visited[];

	// Map that lists the adjacencies between cells
	private Map<Integer, LinkedList<Integer>> adjacencyLists;

	// Set of tragets to be determined when calculated
	private Set<BoardCell> targets;

	// Default constructor for board. Simply initializes the values, nothing else
	public Board() {
		// Create the cells list as a blank
		cells = new ArrayList<BoardCell>();

		// Create the rooms map as a blank
		rooms = new HashMap<Character, String>();
		numRows = 0;
		numColumns = 0;
	}

	// Parameterized constructor, sets all the fields of board using the configuration files
	public Board(String csv, String legend) {
		// Create the cells list as a blank
		cells = new ArrayList<BoardCell>();

		// Create the rooms map as a blank
		rooms = new HashMap<Character, String>();

		// Initialize the rows and columns to 0
		numRows = 0;
		numColumns = 0;

		// Create the filepaths for the configuration files
		csvFilepath = csv;
		legendFilepath = legend;

		// Set up the adjacencies list as a blank map
		adjacencyLists = new HashMap<Integer, LinkedList<Integer>>();

		// Set up the targets as a blank set
		targets = new HashSet<BoardCell>();

	}

	// Method loadConfigFiles relies on two other functions to finish initializing the board
	public void loadConfigFiles() {
		try {
			// Call the two heavy lifter functions
			loadRoomConfig();
			loadBoardConfig();

		} catch(BadConfigFormatException e) { // If one of those throws an error, catch it, print it to the screen
			System.out.println(e);
		}
	}

	// One of the heavy lifter functions for the loadConfigFiles method
	// Throws: BadConfigFormatException
	// Loads up the legend file, and populates the legend in the board
	public void loadRoomConfig() throws BadConfigFormatException  {

		// Initialize the scanner
		Scanner legendFile = null;
		try {
			// Attempt to create a new scanner on the legend path
			legendFile = new Scanner(new File(legendFilepath));
		} catch (FileNotFoundException e) {
			// If we can't find the legend, throw an I/O exception under the BadConfigFormatException
			throw new BadConfigFormatException("I/O Error: " + legendFilepath + "not found!");
		}
		
		// Create an array to hold the legend lines after splitting
		String[] legendSplit;
		
		// While we have more lines:
		while(legendFile.hasNextLine()) {
			// Split the line at the comma
			legendSplit = legendFile.nextLine().split(", ");
			// If the line has more than one comma, throw an exception
			if(legendSplit.length > 2) {
				throw new BadConfigFormatException(legendFilepath + " contains data in an invalid format");
			}
			// Create a room definition using the legend information and put it into rooms 
			rooms.put(legendSplit[0].charAt(0), legendSplit[1]);
		}
		// Make sure we don't have resource leaks
		legendFile.close();
	}
	
	// Second heavy lifter function for the loadConfigFiles method
	// Throws: BadConfigFormatException
	// Load up the config for the board, and create the cells
	public void loadBoardConfig() throws BadConfigFormatException {
		
		// Initialize the scanner to null
		Scanner csvFile = null;
		try {
			// Attempt to create the scanner on the csv file
			csvFile = new Scanner(new File(csvFilepath));
		} catch (FileNotFoundException e) {
			// If something goes wrong, throw an exception
			throw new BadConfigFormatException("csv Filepath was invalid");
		}
		
		// Create a holder for the current csv line
		String csvLine;
		
		// Create an array to hold the line after splitting
		String[] csvSplit;
		
		// While we have more lines (rows) in the csv file:
		while(csvFile.hasNextLine()) {
			// Go to the next line
			csvLine = csvFile.nextLine();
			// Split the line, store it into csvSplit
			csvSplit = csvLine.split(",");
			// Count the row
			++numRows;
			// Process each room classifier in the csv file
			for(int i = 0; i < csvSplit.length; ++i) {
				
				// If the classifier is a w, make a new walkway cell
				if(csvSplit[i].charAt(0) == 'W') { 
					cells.add(new WalkwayCell());
				} 
				// Otherwise, it must be a room cell, or an invalid cell
				else {
					// If we can't find this cell type in the legend, throw an exception
					if(!rooms.containsKey(csvSplit[i].charAt(0))) throw new BadConfigFormatException("Unrecognized room detected");
					
					// Otherwise, add a new room cell
					cells.add(new RoomCell(csvSplit[i]));
				}
			}
		}
		
		// Calculate the number of columns from the rows and amount of cells
		numColumns = cells.size() / numRows;
		
		// If there was an error, i.e. the board is not square, throw an exception
		if(numColumns*numRows != cells.size()) {
			throw new BadConfigFormatException("Columns bad");
		}
		
		// Close resources
		csvFile.close();
		
		// Now that we have all the information, create the visited array.
		visited = new boolean[numRows * numColumns];
	}


	public int calcIndex(int row, int col) {
		if (row >= numRows) return -1;
		else if (col >= numColumns) return -1;
		else if (col < 0) return -1;
		else if (row < 0) return -1;
		else {
			return row * numColumns + col;
		}
	}

	public void calcAdjacencies() {
		LinkedList<Integer> adjacency;
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				adjacency = new LinkedList<Integer>();
				if(cells.get(calcIndex(i,j)).isDoorway()) {
					RoomCell thisCell = (RoomCell) cells.get(calcIndex(i,j));
					LinkedList<Integer> doorAdj = new LinkedList<Integer>();
					doorAdj.add(calcIndex(i,j));
					adjacency.add(calcIndex(i + thisCell.getDoorDirection().getX(), j + thisCell.getDoorDirection().getY()));
				} else if(!cells.get(calcIndex(i,j)).isRoom()) {
					if(adjacencyLogic(i,j,i,j+1))
						adjacency.add(calcIndex(i,j+1));
					if(adjacencyLogic(i,j,i,j-1))
						adjacency.add(calcIndex(i,j-1));
					if(adjacencyLogic(i,j,i+1,j))
						adjacency.add(calcIndex(i+1,j));
					if(adjacencyLogic(i,j,i-1,j))
						adjacency.add(calcIndex(i-1,j));
				}
				adjacencyLists.put(calcIndex(i, j), adjacency);
			}
		}

	}
	private boolean adjacencyLogic(int i0, int j0, int i1, int j1) {
		if(calcIndex(i1,j1) != -1) {
			if(cells.get(calcIndex(i1,j1)).isDoorway()) {
				RoomCell thisRoom = (RoomCell) cells.get(calcIndex(i1,j1));
				if(i1 + thisRoom.getDoorDirection().getX() == i0 && j1 + thisRoom.getDoorDirection().getY() == j0) return true;
				return false;
			} else {
				return !cells.get(calcIndex(i1,j1)).isRoom();
			}
		}
		return false;
	}
	public void startTargets(int location, int steps) {
		targets = new HashSet<BoardCell>();
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
			BoardCell thisCell = cells.get(adjCell);
			if(steps == 1 || thisCell.isDoorway()) {
				targets.add(thisCell);
			} else {
				calcTargets(adjCell, steps - 1);
			}
			visited[adjCell] = false;
		}
	}


	public Set<BoardCell> getTargets() {
		return targets;

	}
	public LinkedList<Integer> getAdjList(int location) {
		return adjacencyLists.get(location);
	}

	public BoardCell getCellAt(int row, int column) {
		return cells.get(calcIndex(row, column));
	}
	public RoomCell getRoomCellAt(int row, int column) {
		if(cells.get(calcIndex(row, column)).isRoom()) {
			return (RoomCell) cells.get(calcIndex(row, column));
		} else {
			return null;
		}
	}
	public ArrayList<BoardCell> getCells() {
		return cells;
	}
	public Map<Character, String> getRooms() {
		return rooms;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
}
