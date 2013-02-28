package board;

public class RoomCell extends BoardCell {
	public enum DoorDirection {
		NONE,UP,DOWN,LEFT,RIGHT
	}
	private DoorDirection doorDirection;
	private char roomClassifier;
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
