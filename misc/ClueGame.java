package misc;

import java.util.ArrayList;



public class ClueGame {

	private Solution solution;
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> closetCards = new ArrayList<Card>();
	
	private ArrayList<ComputerPlayer> cpuPlayers = new ArrayList<ComputerPlayer>();
	private HumanPlayer Bob = new HumanPlayer();
	
	private Player whosTurn;

	public ArrayList<Card> getClosetCards() {
		return closetCards;
	}

	public void setClosetCards(ArrayList<Card> closetCards) {
		this.closetCards = closetCards;
	}
	
	public boolean checkAccusation(Solution solution) {
		return false;
	}
	
	
	
}
