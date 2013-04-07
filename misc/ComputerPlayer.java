package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

import misc.Card.CardType;

import board.Board;
import board.BoardCell;
import board.RoomCell;
import java.awt.Graphics;
import java.awt.Color;

public class ComputerPlayer extends Player {
	
	private char lastRoomVisited;

	public Suggestion createSuggestion(int row, int column, ArrayList<Card> deck, Board board) {
		Suggestion suggestion = new Suggestion();
		String room = board.getRooms().get(board.getRoomCellAt(row, column).getRoomClassifier());
		suggestion.setRoom(new Card (room, CardType.ROOM));
		Collections.shuffle(deck);
		suggestion.setPerson(findValidCard(deck, CardType.PERSON));
		suggestion.setWeapon(findValidCard(deck, CardType.WEAPON));
		return suggestion;
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
		int random = generator.nextInt();
		random = Math.abs(random % targets.size());
		Object[] targArr =  targets.toArray();
		return (BoardCell) targArr[random];
	}

	public void makeMove (Board board) {
	//	int location = 0;
		System.out.println(this.getRow() + " " + this.getColumn());
		board.startTargets(board.calcIndex(this.getRow(), this.getColumn()), 2);
		BoardCell choice = pickLocation(board.getTargets());
		for(int x = 0; x < board.getNumColumns(); x++) {
			for(int y = 0; y < board.getNumRows(); y++) {
				if (board.getCellAt(y, x).equals(choice)) {
					this.setColumn(x);
					this.setRow(y);
				}
			}
		}
		//this.setColumn(this.getColumn()-1);
		//this.setRow(this.getRow()-1);
		//this.draw(board.getGraphics(), board);
		//board.paintComponent(board.getGraphics());
		board.repaint();
		
	}
	
	public ComputerPlayer(String name, String color, int row, int column) {
		super(name, color, row, column);
	}

	public ComputerPlayer() {
		super();
	}
	
	public void updateSeen(Card seen) {
		knownCards.add(seen);
	}
	
	public void updateSeen(ArrayList<Card> seen) {
		knownCards.addAll(seen);
	}

	public char getLastRoomVisited() {
		return lastRoomVisited;
	}

	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}
	
}
