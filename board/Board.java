package board;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	private String csvFilepath, legendFilepath;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRows = 0;
		numColumns = 0;
	}
	
	public Board(String csv, String legend) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRows = 0;
		numColumns = 0;
		csvFilepath = csv;
		legendFilepath = legend;
	}
	public void loadConfigFiles() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch(BadConfigFormatException e) {
			e.toString();
		}
	}
	public void loadRoomConfig() throws BadConfigFormatException  {
		Scanner legendFile = null;
		try {
			legendFile = new Scanner(new File(legendFilepath));
		} catch (FileNotFoundException e) {
				throw new BadConfigFormatException("I/O Error: " + legendFilepath + "not found!");
		}
		String[] legendSplit;
		while(legendFile.hasNextLine()) {
			legendSplit = legendFile.nextLine().split(", ");
			if(legendSplit.length > 2) {
				throw new BadConfigFormatException(legendFilepath + " contains data in an invalid format");
			}
			rooms.put(legendSplit[0].charAt(0), legendSplit[1]);
		}
		legendFile.close();
	}
	public void loadBoardConfig() throws BadConfigFormatException {
		Scanner csvFile = null;
		try {
			csvFile = new Scanner(new File(csvFilepath));
		} catch (FileNotFoundException e) {
			throw new BadConfigFormatException("csvFilepath was invalid");
		}
		String csvLine;
		String[] csvSplit;
		while(csvFile.hasNextLine()) {
			csvLine = csvFile.nextLine();
			csvSplit = csvLine.split(",");
			++numRows;
			for(int i = 0; i < csvSplit.length; ++i) {
				if(csvSplit[i].charAt(0) == 'W') { 
					cells.add(new WalkwayCell());
				} else {
					if(!rooms.containsKey(csvSplit[i].charAt(0))) throw new BadConfigFormatException("Unrecognized room detected");
					cells.add(new RoomCell(csvSplit[i]));
				}
			}
		}
		numColumns = cells.size() / numRows;
		if(numColumns*numRows != cells.size()) {
			throw new BadConfigFormatException("Columns bad");
		}
		csvFile.close();
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

	public void calcAdjacencies() {
		// TODO Auto-generated method stub
		
	}
	public LinkedList<Integer> getAdjList(int location) {
		return null;
	}
	public void startTargets(int location, int steps) {
		
	}
	public Set<BoardCell> getTargets() {
		return null;
	}
}
