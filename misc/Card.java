package misc;

import misc.Card.CardType;


public class Card {

	private String name;
	public enum CardType {PERSON, WEAPON, ROOM;}
	private CardType type;
	
	public Card(String string, CardType room) {}
	public CardType getCardType() {
		return type;
	}
	public void setCardType(CardType cardType) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
