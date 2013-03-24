package misc;
import java.util.ArrayList;

import misc.Card.CardType;

public class Player {

	private int location;
	private String name;
	private ArrayList<Card> cards = new ArrayList<Card>();
	protected ArrayList<Card> knownCards = new ArrayList<Card>();
	
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

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
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
	
}
