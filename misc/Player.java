package misc;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import board.Board;
import misc.Card.CardType;

public class Player {

	private int row;
	private int column;
	private char room;
	private String name;
	protected ArrayList<Card> knownCards;
	private String color;
	private ArrayList<Card> cards;
	private boolean highlight;
	private boolean human;
	
	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public void resetCards() {
		knownCards = new ArrayList<Card>();
		cards = new ArrayList<Card>();
	}
	
	public Player(String name, String color, int row, int column) {
		this.row = row;
		this.column = column;
		this.name = name;
		this.color = color;
		cards = new ArrayList<Card>();
		knownCards = new ArrayList<Card>();
		human = false;
	}
	
	public Player() {
		cards = new ArrayList<Card>();
		knownCards = new ArrayList<Card>();
		human = false;
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
	
	public ArrayList<Card> getCards() {
		return cards;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + column;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (column != other.column)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	public void draw(Graphics g, Board b) {
		int pixelModifier = Math.min(b.size().width/b.getNumColumns(), b.size().height/b.getNumRows());
		setColor(g);
		g.fillRoundRect(column*pixelModifier, row*pixelModifier, pixelModifier, pixelModifier, pixelModifier, pixelModifier);
	}
	
	public void setColor(Graphics g) {
		switch (color) {
		case "Yellow": g.setColor(Color.black);
			break;
		case "Orange": g.setColor(Color.orange);
			break;
		case "Red": g.setColor(Color.red);
			break;
		case "Green": g.setColor(Color.green);
			break;
		case "White" : g.setColor(Color.white);
		default: 
			break;
		}
	}
	

}



















