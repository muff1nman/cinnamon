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
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		numRows = 0;
		numColumns = 0;
	}
	
	public void loadConfigFiles(String csv, String legend) throws Exception {
		try {
			Scanner csvFile = new Scanner(new File(csv));
			String csvLine;
			String[] csvSplit;
			while(csvFile.hasNextLine()) {
				csvLine = csvFile.nextLine();
				csvSplit = csvLine.split(",");
				++numRows;
				for(int i = 0; i < csvSplit.length; ++i) {
					if(csvSplit[i].charAt(0) == 'W') { // how to determine if is walkway without hardcoding a w?
						cells.add(new WalkwayCell());
					} else {
						cells.add(new RoomCell(csvSplit[i]));
					}
				}
			}
			numColumns = cells.size() / numRows;
			// TODO throw exception if cols*rows > cells.size()
			csvFile.close();
			Scanner legendFile = new Scanner(new File(legend));
			String[] legendSplit;
			while(legendFile.hasNextLine()) {
				legendSplit = legendFile.nextLine().split(", ");
				rooms.put(legendSplit[0].charAt(0), legendSplit[1]);
			}
			legendFile.close();
		} catch(FileNotFoundException e) {
			throw new BadConfigFormatException("I/O error: Config file " + csv + "not found!");
		}
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
	public Set<Integer> getTargets() {
		return null;
	}
}
