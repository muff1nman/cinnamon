package board;

public class RoomCell extends BoardCell {
	private boolean isDoor = false;
	public enum DoorDirection {
		NONE(0,0),UP(-1,0),DOWN(1,0),LEFT(0,-1),RIGHT(0,1);
		DoorDirection(int X, int Y) {
			x = X;
			y = Y;
		}
		private int x,y;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	private DoorDirection doorDirection;
	private char roomClassifier;
	public RoomCell(String roomName) {
		roomClassifier = roomName.charAt(0);
		if(roomName.length() == 2 && roomName.charAt(1) != 'N') {
			isDoor = true;
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
	public boolean isDoorway() {
		return isDoor;
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
