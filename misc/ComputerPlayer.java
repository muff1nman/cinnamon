package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

import misc.Card.CardType;

import board.Board;
import board.BoardCell;
import board.RoomCell;


public class ComputerPlayer extends Player {
	
	private char lastRoomVisited;
	
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}

	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}

	public Suggestion createSuggestion(int row, int column, ArrayList<Card> deck, Board board) {
		
		Suggestion suggestion = new Suggestion();
		String room = board.getRooms().get(board.getRoomCellAt(row, column).getRoomClassifier());
		
		suggestion.setRoom(new Card (room, CardType.ROOM));
		ArrayList<Card> knownCards = this.getKnownCards();
		Collections.shuffle(deck);
		
		suggestion.setPerson(findValidCard(deck, CardType.PERSON));
		suggestion.setWeapon(findValidCard(deck, CardType.WEAPON));
		
		return suggestion;
		
		//String room = board.getRooms().get(this.getRoom());
		//this.setRoom(board.getRoomCellAt(row, column).getRoomClassifier()); //possibly move to update position
		/*
		ArrayList<Card> suggestion = new ArrayList<Card>();
		ArrayList<Card> knownCards = this.getKnownCards();
		Card person = null;
		//Card room = null;
		Card weapon = null;
		
		for (Card x : deck) {
			if (x.getCardType().equals(CardType.PERSON) && !knownCards.contains(x)) {
				person = x;
			}
		}
		suggestion.add(person);
		//temp
		suggestion.add(new Card("kitchen", CardType.ROOM));
		for (Card x : deck) {
			if (x.getCardType().equals(CardType.WEAPON) && !knownCards.contains(x)) {
				weapon = x;
			}
		}
		suggestion.add(weapon);
		
		//suggestion.add(new Card("temp1", CardType.PERSON));
		//suggestion.add(new Card("temp2", CardType.ROOM));
		//suggestion.add(new Card("temp3", CardType.WEAPON));
		
		
		return suggestion;
		//first item in arrayList is person
		//2nd item is room
		//3rd item is weapon
		 * 
		 */
	}
	
	public Card findValidCard(ArrayList<Card> deck, CardType type) {
		ArrayList<Card> knownCards = this.getKnownCards();
		for (Card x : deck) {
			if (x.getCardType().equals(type) && !knownCards.contains(x)) {
				return x;
			}
		}
		return null;
		
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
				RoomCell room = (RoomCell) selection;
				if (room.getRoomClassifier() != lastRoomVisited) {
					return selection;
				}
			}
		}
		Random generator = new Random();
		//System.out.println("targets size in pick "+targets.size());
		int random = generator.nextInt();
		random = Math.abs(random % targets.size());
		//System.out.println("random value" + random);
		Object[] targArr =  targets.toArray();
		return (BoardCell) targArr[random];
	}

	public ComputerPlayer(String name, String color, int row, int column) {
		super(name, color, row, column);
	}

	public ComputerPlayer() {
		super();
	}


	
}
