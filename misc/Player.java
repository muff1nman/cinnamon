package misc;
import java.util.ArrayList;
import board.BoardCell;
import misc.Card.CardType;

public class Player {

	private int row;
	private int column;
	//private BoardCell room;
	private char room;
	//private int location;
	private String name;

	
	public char getRoom() {
		return room;
	}

	public void setRoom(char room) {
		this.room = room;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	protected ArrayList<Card> knownCards;

	private String color;
	private ArrayList<Card> cards;
	
	public Player(String name, String color, int row, int column) {
		this.row = row;
		this.column = column;
		this.name = name;
		this.color = color;
		cards = new ArrayList<Card>();
		knownCards = new ArrayList<Card>();
	}
	
	public Player() {
		cards = new ArrayList<Card>();
		knownCards = new ArrayList<Card>();
	}

	
	public Card disproveSuggestion(String person, String room, String weapon) {
		if(cards.contains(new Card(person, CardType.PERSON)))
			return cards.get(cards.indexOf(new Card(person, CardType.PERSON)));
		if(cards.contains(new Card(room, CardType.ROOM)))
			return cards.get(cards.indexOf(new Card(room, CardType.ROOM)));
		if(cards.contains(new Card(weapon, CardType.WEAPON)))
			return cards.get(cards.indexOf(new Card(weapon, CardType.WEAPON)));
		return null;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	/*
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void giveCard(Card card) {
		cards.add(card);
	}
	
	public ArrayList<Card> getKnownCards() {
		return knownCards;
	}

	public void setKnownCards(ArrayList<Card> knownCards) {
		this.knownCards = knownCards;
	}
	
}
