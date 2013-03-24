package misc;

import java.util.ArrayList;
import java.util.Set;

import misc.Card.CardType;

import board.BoardCell;


public class ComputerPlayer extends Player {
	
	private char lastRoomVisited;
	
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}

	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}

	public ArrayList<Card> createSuggestion(int location) {
		ArrayList<Card> suggestion = new ArrayList<Card>();
		suggestion.add(new Card("temp1", CardType.PERSON));
		suggestion.add(new Card("temp2", CardType.ROOM));
		suggestion.add(new Card("temp3", CardType.WEAPON));
		
		
		return suggestion;
		//first item in arrayList is person
		//2nd item is room
		//3rd item is weapon
	}
	
	public void updateSeen(Card seen) {
		knownCards.add(seen);
	}
	
	public void updateSeen(ArrayList<Card> seen) {
		knownCards.addAll(seen);
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
	
		for (BoardCell selection : targets) {
			if (selection.isRoom()) {
				
			}
		}
		
		return null;
	}

	public ComputerPlayer(String name, String color, int row, int column) {
		super(name, color, row, column);
	}

	public ComputerPlayer() {
		super();
	}


	
}
