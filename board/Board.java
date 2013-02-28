package board;
import java.util.ArrayList;
import java.util.Map;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numColumns;
	
	public void loadConfigFiles() {
		
	}
	public int calcIndex(int row, int col) {
		return 0;
	}
	public RoomCell GetRoomCellAt(int row, int column) {
		return null;
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
