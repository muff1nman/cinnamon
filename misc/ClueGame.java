package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import board.BadConfigFormatException;
import board.Board;

import misc.Card.CardType;

public class ClueGame {

	private Solution solution;
	
	private ArrayList<Card> deck;
	private ArrayList<Card> closetCards;
	private ArrayList<ComputerPlayer> cpuPlayers;
	private HumanPlayer Bob;
	private Player whosTurn;
	private Board board;
	private String legend;
	private String layout;
	private String players;
	private String weapons;

	public ClueGame(String legend, String layout, String players, String weapons) {
		this.legend = legend;
		this.layout = layout;
		this.players = players;
		this.weapons = weapons;
		deck = new ArrayList<Card>();
		closetCards = new ArrayList<Card>();
		cpuPlayers = new ArrayList<ComputerPlayer>();
		Bob = new HumanPlayer();
		board = new Board(layout, legend);
	}
	public ClueGame() {
		legend = "legend.txt";
		layout = "RoomLayout.csv";
		players = "players.txt";
		weapons = "weapons.txt";
		deck = new ArrayList<Card>();
		closetCards = new ArrayList<Card>();
		cpuPlayers = new ArrayList<ComputerPlayer>();
		Bob = new HumanPlayer();
		board = new Board(layout, legend);
	}
	
	public void loadConfigFiles() {
		board.loadConfigFiles();
		loadPeople();
		loadDeck();
	}
	public void loadPeople() {
		Scanner peopleFile = null;
		try {
			peopleFile = new Scanner(new File(players));
		} catch (FileNotFoundException e) {
			System.out.println("Players file not found");
		}
		String[] peopleSplit;
		while(peopleFile.hasNextLine()) {
			peopleSplit = peopleFile.nextLine().split(",");
			if(peopleSplit[0].charAt(0) == '+') {
				Bob = new HumanPlayer(peopleSplit[0].substring(1), peopleSplit[1], Integer.parseInt(peopleSplit[2]), Integer.parseInt(peopleSplit[3]));
			} else {
				cpuPlayers.add(new ComputerPlayer(peopleSplit[0], peopleSplit[1], Integer.parseInt(peopleSplit[2]), Integer.parseInt(peopleSplit[3])));
			}
		}
		peopleFile.close();
	}
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
