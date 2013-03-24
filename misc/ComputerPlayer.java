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
		System.out.println(suggestion.size());
		
		return suggestion;
		//first item in arrayList is person
		//2nd item is room
		//3rd item is weapon
	}
	
	public void updateSeen(Card seen) {}
	
	public void updateSeen(ArrayList<Card> seen) {}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}

	public ComputerPlayer(String string, String string2, int i, int j) {}

	public ComputerPlayer() {
		// TODO Auto-generated constructor stub
	}


	
}
