/*
 * Class: Board
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class is the main class pertaining to the clue board.
 * Also contains logic to calculate targets and adjacencies
 * Relies on BadConfigFormatException, BoardCell, RoomCell, and WalkwayCell
 */

package board;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JPanel;

import misc.Player;

// Board class body
public class Board extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void highlightTargets(int row, int column) {
		this.startTargets(this.calcIndex(row,column), 5);
		for (BoardCell x : this.getTargets()) {
			//x.draw(this.getGraphics(), this, this.calcIndex(row, column), true);
			x.highlight = true;
			this.repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		int z = 0;
		//this.startTargets(location, steps)
		for (BoardCell x: cells) {
			
			x.draw(g, this, z, false);
			z++;
		}
		for (Player y: players) {
			y.draw(g, this);
			//this.startTargets(this.calcIndex(y.getRow(), y.getColumn()), 2);
			//for (BoardCell x : this.getTargets()) {
			//	x.draw(g, this, this.calcIndex(x.row, x.column), true);
			//}
		}
		
		
	}
	
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	private ArrayList<Player> players;
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	private boolean highlight;

	// Filepaths for the configuration files
	private String csvFilepath, legendFilepath;

	// Array used to keep track of visited cells when the fucntion calcAdjacencies is called
	private boolean visited[];
	private Map<Integer, LinkedList<Integer>> adjacencyLists;
	private Set<BoardCell> targets;

	// Default constructor for board. Simply initializes the values, nothing else
	public Board() {
		initialize();
	}

	// Parameterized constructor, sets all the fields of board using the configuration files
	public Board(String csv, String legend) {
		setSize(700,700);
		initialize();
		csvFilepath = csv;
		legendFilepath = legend;
		adjacencyLists = new HashMap<Integer, LinkedList<Integer>>();
		targets = new HashSet<BoardCell>();
		//calcAdjacencies();
		//sntoeuthnsueotnssnhtoetudnhttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt
		//loadConfigFiles();
	}
	
	// Initializes default values of cells, rooms, numRows, and numColumns
	private void initialize() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRows = 0;
		numColumns = 0;
	}

	public void loadConfigFiles() {
		try {
			loadRoomConfig();
			loadBoardConfig();
			calcAdjacencies();

		} catch(BadConfigFormatException e) { // If one of those throws an error, catch it, print it to the screen
			System.out.println(e);
		}
	}

	// One of the heavy lifter functions for the loadConfigFiles method
	// Throws: BadConfigFormatException
	// Loads up the legend file, and populates the legend in the board
	public void loadRoomConfig() throws BadConfigFormatException  {
		Scanner legendFile = null;
		try {
			legendFile = new Scanner(new File(legendFilepath));
		} catch (FileNotFoundException e) {
			// If we can't find the legend, throw an I/O exception under the BadConfigFormatException
			throw new BadConfigFormatException("I/O Error: " + legendFilepath + "not found!");
		}
		String[] legendSplit;

		while(legendFile.hasNextLine()) {
			legendSplit = legendFile.nextLine().split(", ");
			// If the line has more than one comma, throw an exception
			if(legendSplit.length > 2) {
				throw new BadConfigFormatException(legendFilepath + " contains data in an invalid format");
			}
			// Create a room definition using the legend information and put it into rooms 
			rooms.put(legendSplit[0].charAt(0), legendSplit[1]);
		}
		legendFile.close();
	}

	// Second heavy lifter function for the loadConfigFiles method
	// Throws: BadConfigFormatException
	// Load up the config for the board, and create the cells
	public void loadBoardConfig() throws BadConfigFormatException {
		Scanner csvFile = null;
		try {
			// Attempt to create the scanner on the csv file
			csvFile = new Scanner(new File(csvFilepath));
		} catch (FileNotFoundException e) {
			// If something goes wrong, throw an exception
			throw new BadConfigFormatException("csv Filepath was invalid");
		}
		String csvLine;
		String[] csvSplit;

		while(csvFile.hasNextLine()) {
			csvLine = csvFile.nextLine();
			csvSplit = csvLine.split(",");
			++numRows;
			for(int i = 0; i < csvSplit.length; ++i) {

				// If the classifier is a w, make a new walkway cell
				if(csvSplit[i].charAt(0) == 'W') { 
					cells.add(new WalkwayCell());
				} 
				// Otherwise, it must be a room cell, or an invalid cell
				else {
					// If we can't find this cell type in the legend, throw an exception
					if(!rooms.containsKey(csvSplit[i].charAt(0))) throw new BadConfigFormatException("Unrecognized room detected");
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

		csvFile.close();
		System.out.println(numRows + " " + numColumns);
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
		LinkedList<Integer> adjacency;

		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				adjacency = new LinkedList<Integer>();
				if(cells.get(calcIndex(i,j)).isDoorway()) {
					RoomCell thisCell = (RoomCell) cells.get(calcIndex(i,j));
					adjacency.add(calcIndex(i + thisCell.getDoorDirection().getX(), j + thisCell.getDoorDirection().getY()));
				} 
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
				adjacencyLists.put(calcIndex(i, j), adjacency);
			}
		}
	}
	
	// This function verifies that the adjacency is correct for the given cell, (i0,j0)
		private boolean adjacencyLogic(int i0, int j0, int i1, int j1) {
			// If calcIndex detects an issue, it is not correct. return false.
			if(calcIndex(i1,j1) != -1) {
				if(cells.get(calcIndex(i1,j1)).isDoorway()) {
					// Make a room cell from the doorway cell we're checking
					RoomCell thisRoom = (RoomCell) cells.get(calcIndex(i1,j1));
					// If the you remove the differences in distance between the cells and now the x's and y's are equal, return true 
					if(i1 + thisRoom.getDoorDirection().getX() == i0 && j1 + thisRoom.getDoorDirection().getY() == j0) return true;
					return false;
				}
				else {
					return !cells.get(calcIndex(i1,j1)).isRoom();
				}
			}
			return false;
		}
	
	// Start targets uses calcTargets to calculate the correct targets for moving in the game
	public void startTargets(int location, int steps) {
		targets = new HashSet<BoardCell>();
		//System.out.println("location size");
		visited[location] = true;
		calcTargets(location, steps);
	}
	
	// Does the heavy lifting for startTargets, populates the targets list for a current location given a number of steps
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
			} 
			else {
				calcTargets(adjCell, steps - 1);
			}
			visited[adjCell] = false;
		}
	}
	
	
	public RoomCell getRoomCellAt(int row, int column) {
		if(cells.get(calcIndex(row, column)).isRoom()) {
			return (RoomCell) cells.get(calcIndex(row, column));
		} else {
			return null;
		}
	}
	
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
