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
		// Initialize cells, rooms, numRows, numColumns
		initialize();
	}

	// Parameterized constructor, sets all the fields of board using the configuration files
	public Board(String csv, String legend) {
		// Initialize cells, rooms, numRows, numColumns
		initialize();
		// Create the filepaths for the configuration files
		csvFilepath = csv;
		legendFilepath = legend;
		// Set up the adjacencies list as a blank map
		adjacencyLists = new HashMap<Integer, LinkedList<Integer>>();
		// Set up the targets as a blank set
		targets = new HashSet<BoardCell>();
	}
	
	// Initializes default values of cells, rooms, numRows, and numColumns
	private void initialize() {
		// Create the cells list as a blank
		cells = new ArrayList<BoardCell>();
		// Create the rooms map as a blank
		rooms = new HashMap<Character, String>();
		// Initialize the rows and columns to 0
		numRows = 0;
		numColumns = 0;
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

	// Calculates the appropriate index on a 1D array given a row and column 
	public int calcIndex(int row, int col) {
		// Outlier / Bad Input Cases
		if (row >= numRows) return -1;
		else if (col >= numColumns) return -1;
		else if (col < 0) return -1;
		else if (row < 0) return -1;

		// If the input is valid, then calculate the index
		else {
			return row * numColumns + col;
		}
	}

	// Calculates the adjacencies for every cell on the board, stores them into the adjacency list
	public void calcAdjacencies() {

		// Create a temporary adjacency
		LinkedList<Integer> adjacency;

		// For all the cells on the board:
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				// Create a blank adjacency list
				adjacency = new LinkedList<Integer>();
				// If the current cells is a doorway
				if(cells.get(calcIndex(i,j)).isDoorway()) {
					// Create a room cell from the doorway cell so we can use it in the adjacency calculation 
					RoomCell thisCell = (RoomCell) cells.get(calcIndex(i,j));
					// Add the adjacencies to the their lists
					adjacency.add(calcIndex(i + thisCell.getDoorDirection().getX(), j + thisCell.getDoorDirection().getY()));
				} 
				// Otherwise, if this cell is not a room
				else if(!cells.get(calcIndex(i,j)).isRoom()) {
					// Add the appropriate adjacency based upon the adjacency list function
					if(adjacencyLogic(i,j,i,j+1))
						adjacency.add(calcIndex(i,j+1));
					if(adjacencyLogic(i,j,i,j-1))
						adjacency.add(calcIndex(i,j-1));
					if(adjacencyLogic(i,j,i+1,j))
						adjacency.add(calcIndex(i+1,j));
					if(adjacencyLogic(i,j,i-1,j))
						adjacency.add(calcIndex(i-1,j));
				}
				// Put this list onto the adjacency lists map
				adjacencyLists.put(calcIndex(i, j), adjacency);
			}
		}
	}
	
	// This function verifies that the adjacency is correct for the given cell, (i0,j0)
		private boolean adjacencyLogic(int i0, int j0, int i1, int j1) {
			// If calcIndex detects an issue, it is not correct. return false.
			if(calcIndex(i1,j1) != -1) {
				// Otherwise, if calcIndex says its fine and the cell is a doorway
				if(cells.get(calcIndex(i1,j1)).isDoorway()) {
					// Make a room cell from the doorway cell we're checking
					RoomCell thisRoom = (RoomCell) cells.get(calcIndex(i1,j1));
					// If the you remove the differences in distance between the cells and now the x's and y's are equal, return true 
					if(i1 + thisRoom.getDoorDirection().getX() == i0 && j1 + thisRoom.getDoorDirection().getY() == j0) return true;
					// Otherwise return false
					return false;
				}
				// If this is not a doorway cell, return the opposite of isRoom from the target cell
				else {
					return !cells.get(calcIndex(i1,j1)).isRoom();
				}
			}
			return false;
		}
	
	// Start targets uses calcTargets to calculate the correct targets for moving in the game
	public void startTargets(int location, int steps) {
		
		// Initialize targets to a blank set
		targets = new HashSet<BoardCell>();
		
		// Set the current location to true
		visited[location] = true;
		
		// Call calcTargets
		calcTargets(location, steps);
	}
	
	// Does the heavy lifting for startTargets, populates the targets list for a current location given a number of steps
	private void calcTargets(int location, int steps) {
		
		// Create a list to hold the adjacent cells
		LinkedList<Integer> adjacentCells = new LinkedList<Integer>();
		
		// For all the cells in the adjacency lists at this given location
		for(int adjCell : adjacencyLists.get(location)) {
			// If the cell has not yet been visited, add it to the adjacentCells list
			if(visited[adjCell] == false) {
				adjacentCells.add(adjCell);
			}
		}
		
		// For all cells in the adjacentCells list
		for(int adjCell : adjacentCells) {
			// Set the cell to visited
			visited[adjCell] = true;
			// Create a cell using the adjacent cell, generalizes the adjacent cell
			BoardCell thisCell = cells.get(adjCell);
			// If the steps is now 1 or this is a doorway, add the cell
			if(steps == 1 || thisCell.isDoorway()) {
				targets.add(thisCell);
			} 
			// Otherwise, recursively call calctargets until primary if is true
			else {
				calcTargets(adjCell, steps - 1);
			}
			// Set the visited back to false after the targets is done calculating
			visited[adjCell] = false;
		}
	}
	
	
	// Returns the appropriate cell given a row and column. Uses calcIndex to get cell location
	// If the cell is wrong, or cannot be found, returns null
	public RoomCell getRoomCellAt(int row, int column) {
		if(cells.get(calcIndex(row, column)).isRoom()) {
			return (RoomCell) cells.get(calcIndex(row, column));
		} else {
			return null;
		}
	}
	
	// Returns the appropriate board cell at a certain index
	public BoardCell getCellAt(int row, int column) {
		return cells.get(calcIndex(row, column));
	}
	
	//
	// Getters for all standard instance variables
	//
	
	public Set<BoardCell> getTargets() {
		return targets;

	}
	
	public LinkedList<Integer> getAdjList(int location) {
		return adjacencyLists.get(location);
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
