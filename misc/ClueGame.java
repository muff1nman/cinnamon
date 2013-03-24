package misc;

import java.util.ArrayList;

import misc.Card.CardType;

public class ClueGame {

	private Solution solution;
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> closetCards = new ArrayList<Card>();
	private ArrayList<ComputerPlayer> cpuPlayers = new ArrayList<ComputerPlayer>();
	private HumanPlayer Bob = new HumanPlayer();
	private Player whosTurn;

	public ClueGame(String legend, String layout, String players, String weapons) {}
	public ClueGame() {}
	
	public void loadConfigFiles() {}
	public void loadPeople() {}
	public void loadDeck() {}
	
	public boolean checkAccusation(Solution solution) {
		return false;
	}

	public int getDeckWeaponSize() {
		int weapons = 0;
		for(Card a : deck) {
			if(a.getCardType() == CardType.WEAPON)
				weapons++;
		}
		return weapons;
	}
	public int getDeckPlayerSize() {
		int players = 0;
		for(Card a : deck) {
			if(a.getCardType() == CardType.PERSON)
				players++;
		}
		return players;
	}
	public int getDeckRoomSize() {
		int rooms = 0;
		for(Card a : deck) {
			if(a.getCardType() == CardType.ROOM)
				rooms++;
		}
		return rooms;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	public ArrayList<Card> getClosetCards() {
		return closetCards;
	}
	public void setClosetCards(ArrayList<Card> closetCards) {
		this.closetCards = closetCards;
	}
	public ArrayList<ComputerPlayer> getCpuPlayers() {
		return cpuPlayers;
	}
	public void setCpuPlayers(ArrayList<ComputerPlayer> cpuPlayers) {
		this.cpuPlayers = cpuPlayers;
	}
	public HumanPlayer getBob() {
		return Bob;
	}
	public void setBob(HumanPlayer bob) {
		Bob = bob;
	}
	public Player getWhosTurn() {
		return whosTurn;
	}
	public void setWhosTurn(Player whosTurn) {
		this.whosTurn = whosTurn;
	}
	public Object handleSuggestion(String person, String room, String weapon, Player playerOne) {
		// TODO Auto-generated method stub
		return null;
	}
	public void addPlayer(ComputerPlayer player) {
		cpuPlayers.add(player);
	}
	public void addPlayer(HumanPlayer player) {
		Bob = player;
	}

	
}
