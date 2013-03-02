package board;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
	
	public void loadConfigFiles(String csv, String legend) throws BadConfigFormatException {
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
//						System.out.println(csvSplit[i].charAt(0) + " is a walkway");
						cells.add(new WalkwayCell());
					} else {
//						System.out.println(csvSplit[i].charAt(0) + " is a room");
						cells.add(new RoomCell(csvSplit[i]));
					}
				}
			}
			numColumns = cells.size() / numRows;
			csvFile.close();
			Scanner legendFile = new Scanner(new File(legend));
			String[] legendSplit;
			while(legendFile.hasNextLine()) {
				legendSplit = legendFile.nextLine().split(", ");
				rooms.put(legendSplit[0].charAt(0), legendSplit[1]);
			}
			legendFile.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
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
}
