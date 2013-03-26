package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
		cpuPlayers = new ArrayList<ComputerPlayer>();
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
				Bob = new HumanPlayer(peopleSplit[0].substring(1), peopleSplit[1], Integer.parseInt(peopleSplit[2]), 
						Integer.parseInt(peopleSplit[3]));
			} else {
				cpuPlayers.add(new ComputerPlayer(peopleSplit[0], peopleSplit[1], Integer.parseInt(peopleSplit[2]), 
						Integer.parseInt(peopleSplit[3])));
			}
		}
		peopleFile.close();
	}
	public void loadDeck() {
		deck = new ArrayList<Card>();
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
				deck.add(new Card(peopleSplit[0].substring(1), CardType.PERSON));
			} else {
				deck.add(new Card(peopleSplit[0], CardType.PERSON));
			}
		}
		peopleFile.close();
		
		Scanner roomFile = null;
		try {
			roomFile = new Scanner(new File(legend));
		} catch (FileNotFoundException e) {
			System.out.println("Legend file not found");
		}
		String[] roomSplit;
		while(roomFile.hasNextLine()) {
			roomSplit = roomFile.nextLine().split(", ");
			if(!roomSplit[1].equalsIgnoreCase("Closet")) {
				deck.add(new Card(roomSplit[1], CardType.ROOM));
			}
		}
		roomFile.close();
		
		Scanner weaponFile = null;
		try {
			weaponFile = new Scanner(new File(weapons));
		} catch (FileNotFoundException e) {
			System.out.println("Weapons file not found");
		}
		String weaponSplit;
		while(weaponFile.hasNextLine()) {
			weaponSplit = weaponFile.nextLine();
			deck.add(new Card(weaponSplit, CardType.WEAPON));
		}
		weaponFile.close();
	}
	public void deal() {
		int i = 0;
		Bob.resetCards();
		for(Player a : cpuPlayers)
			a.resetCards();
		boolean weaponInSolution = false;
		boolean personInSolution = false;
		boolean roomInSolution = false;
		for(Card a : deck) {
			CardType theType = a.getCardType();
			if((!weaponInSolution)&&(theType==CardType.WEAPON))
				closetCards.add(a);
			else if((!personInSolution)&&(theType==CardType.PERSON))
				closetCards.add(a);
			else if((!roomInSolution)&&(theType==CardType.ROOM))
				closetCards.add(a);
			else if(i == 0)
				Bob.giveCard(a);
			else if(i > 0)
				cpuPlayers.get(i - 1).giveCard(a);
			i++;
			if(i > cpuPlayers.size())
				i = 0;
		}
	}
	public boolean checkAccusation(Solution solution) {
		String person = null;
		String weapon = null;
		String room = null;
		for(Card a : closetCards) {
			if(a.getCardType() == CardType.PERSON)
				person = a.getName();
			else if(a.getCardType() == CardType.WEAPON)
				weapon = a.getName();
			else if(a.getCardType() == CardType.ROOM)
				room = a.getName();
		}
		return solution.checkSolution(person, weapon, room);
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
	public Object handleSuggestion(String thePerson, String theRoom, String theWeapon, Player thePlayer) {
		ArrayList<Player> thesePlayers = new ArrayList<Player>();
		ArrayList<String> theseStrings = new ArrayList<String>();
		ArrayList<Card> theseCards = new ArrayList<Card>();
		Object answer = null;
		theseStrings.add(theRoom);
		theseStrings.add(theWeapon);
		theseStrings.add(thePerson);
		
		thesePlayers.add(Bob);
		for(Player a : cpuPlayers)
			thesePlayers.add(a);
		Collections.shuffle(thesePlayers);
		thesePlayers.remove(thePlayer);
		
		for(Player a : thesePlayers) {
			for(Card b : a.getCards()) {
				theseCards.add(b);
			}
		}
		System.out.println("thesecards size"+theseCards.size());
		Collections.shuffle(theseCards);
		System.out.println("thesecards size"+theseCards.size());
		
		for(Card a : theseCards) {
			System.out.println(a.getName());
			if(theseStrings.contains(a.getName())) {
				answer = a;
				break;
			}
		}

		return answer;
	}
	public void addPlayer(ComputerPlayer player) {
		System.out.println("size" + cpuPlayers.size());
		cpuPlayers.add(player);
	}
	public void addPlayer(HumanPlayer player) {
		Bob = player;
		System.out.println("human");
	}
	public void resetPlayers() {
		Bob = null;
		cpuPlayers = new ArrayList<ComputerPlayer>();
	}

	
}
