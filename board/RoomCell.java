package board;

public class RoomCell extends BoardCell {
	public enum DoorDirection {
		NONE,UP,DOWN,LEFT,RIGHT
	}
	private DoorDirection doorDirection;
	private char roomClassifier;
	public RoomCell(String roomName) {
		roomClassifier = roomName.charAt(0);
		if(roomName.length() == 2) {
			if(roomName.charAt(1) == 'U') {
				doorDirection = DoorDirection.UP;
			} else if(roomName.charAt(1) == 'D') {
				doorDirection = DoorDirection.DOWN;
			} else if(roomName.charAt(1) == 'L') {
				doorDirection = DoorDirection.LEFT;
			} else if(roomName.charAt(1) == 'R') {
				doorDirection = DoorDirection.RIGHT;
			}
		} else {
			doorDirection = DoorDirection.NONE;
		}
	}
	@Override
	public boolean isRoom() {
		return true;
	}
	@Override
	void draw() {
		// TODO Auto-generated method stub
		
	}
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	public char getRoomClassifier() {
		return roomClassifier;
	}
}
