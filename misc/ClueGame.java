package misc;

import java.util.ArrayList;



public class ClueGame {

	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> closetCards = new ArrayList<Card>();
	
	private ArrayList<ComputerPlayer> cpuPlayers = new ArrayList<ComputerPlayer>();
	private HumanPlayer Bob = new HumanPlayer();
	
	private Player whosTurn;
	
}
