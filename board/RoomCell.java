/*
 * Class: RoomCell, child of BoardCell
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class is used to keep track of the room cells in the game.
 * Also encapsulates the doorDirection attribute
 */
package board;

import java.awt.Color;
import java.awt.Graphics;

// BoardCell class body
public class RoomCell extends BoardCell {
	
	private String roomName;
	
	// Enumerated type for determining the door direction
	public enum DoorDirection {
		
		// Declaration of the enum. Done such that the direction also tells which way the player should move on the board
		NONE(0,0),UP(-1,0),DOWN(1,0),LEFT(0,-1),RIGHT(0,1);
		
		// Ints used in the functions to help define the direction
		private int x,y;
		
		// Parameterized constructor, used to make the default type based on an x and y value
		DoorDirection(int X, int Y) {
			x = X;
			y = Y;
		}
		
		// Getters for x and y
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	
	//Variables for the RoomCell's use
	private boolean isDoor = false; // Start with this cell not being a door
	private DoorDirection doorDirection; // Instantiate a blank door direction
	private char roomClassifier; // Create a blank classifier
	
	// Parameterized constructor for RoomCell
	public RoomCell(String roomName) {
		
		//-------------------------------------
		this.roomName = roomName;
		//-------------------------------
		
		// Set the classifier to the first char in the config at this cell
		roomClassifier = roomName.charAt(0);
		// If the length is 2 and the second char is not N
		if(roomName.length() == 2 && roomName.charAt(1) != 'N') {
			isDoor = true; // This cell is a doorway
			
			// Set the direction based on the second char
			// U = UP, D = DOWN, L = LEFT, R = RIGHT
			if(roomName.charAt(1) == 'U') {
				doorDirection = DoorDirection.UP;
			} else if(roomName.charAt(1) == 'D') {
				doorDirection = DoorDirection.DOWN;
			} else if(roomName.charAt(1) == 'L') {
				doorDirection = DoorDirection.LEFT;
			} else if(roomName.charAt(1) == 'R') {
				doorDirection = DoorDirection.RIGHT;
			}
		} else { // Otherwise, this is not a doorway, set the direction to none
			doorDirection = DoorDirection.NONE;
		}
	}
	
	//-------------------------------------------------
	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
//----------------------------------------------------------

	// Overridden isRoom function, used to identify that this cell is a room
	@Override
	public boolean isRoom() {
		return true;
	}
	
	// Overridden isDoorwayFunction, used to identify easily if the cell is a doorway
	@Override
	public boolean isDoorway() {
		return isDoor;
	}

	// Getter for doorDirection. Returns NONE if the cell is not a doorway
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	// Getter for the room classifier. Must return a char listed in the legend config file
	public char getRoomClassifier() {
		return roomClassifier;
	}

	@Override
	public void draw(Graphics g, Board b, int z) {
		int numColumns = b.getNumColumns();
		int numRows = b.getNumRows();
		int row = z/numColumns;
		int column = z - (row*numColumns);
		g.setColor(Color.red);
		g.fillRect(column*5, row*5, 5, 5);
		
		
	}
}
