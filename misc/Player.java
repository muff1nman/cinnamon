package misc;
import java.util.ArrayList;




public class Player {

	private int location;
	private String name;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public boolean disproveSuggestion() {
		return false;
	}
	
	public ArrayList<Card> getCards() {
		return null;
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
		// TODO Auto-generated method stub
		
	}
	
}
