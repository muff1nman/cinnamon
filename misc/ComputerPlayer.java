package misc;

import java.util.Set;

import board.BoardCell;


public class ComputerPlayer extends Player {
	
	private char lastRoomVisited;
	
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}

	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}

	public void pickLocation() {}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}

	public ComputerPlayer(String string, String string2, int i, int j) {}

	public ComputerPlayer() {
		// TODO Auto-generated constructor stub
	}


	
}
