package misc;

import java.util.ArrayList;



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
		return 0;
	}
	public int getDeckPlayerSize() {
		return 0;
	}
	public int getDeckRoomSize() {
		return 0;
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
		// TODO Auto-generated method stub
		
	}
	public void addPlayer(HumanPlayer player) {
		// TODO Auto-generated method stub
		
	}

	
}
