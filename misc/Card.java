package misc;

import misc.Card.CardType;


public class Card {

	private String name;

	public enum CardType {PERSON, WEAPON, ROOM;}
	private CardType type;

	
	
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
	
	public Card(String name, CardType type) {
		super();
		this.name = name;
		this.type = type;
	}

	//overriding the equals function
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	//still overidding the equal function. spell check is for casuals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	
}
