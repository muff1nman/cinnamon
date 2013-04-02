package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog {
	JPanel people;
	JPanel rooms;
	JPanel weapons;
	JPanel personGuess;
	JPanel roomGuess;
	JPanel weaponGuess;
	
	public DetectiveNotes() {
		setSize(600,450);
		setTitle("Detective Notes");
		people = new JPanel();
		rooms = new JPanel();
		weapons = new JPanel();
		personGuess = new JPanel();
		roomGuess = new JPanel();
		weaponGuess = new JPanel();
		setLayout(new GridLayout(3,2));
		createLayout();
		setVisible(true);
	}
	
	public void createLayout() {
		createPeople();
		createRooms();
		createWeapons();
		createPersonGuess();
		createRoomGuess();
		createWeaponGuess();
		add(people);
		add(personGuess);
		add(rooms);
		add(roomGuess);
		add(weapons);
		add(weaponGuess);
	}
	
	public void createPeople() {
		people.setLayout(new GridLayout(3,3));
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		JCheckBox scarlet = new JCheckBox("Miss Scarlet");
		JCheckBox green = new JCheckBox("Mr. Green");
		JCheckBox peacock = new JCheckBox("Mrs. Peacock");
		JCheckBox mustard = new JCheckBox("Colonel Mustard");
		JCheckBox white = new JCheckBox("Mrs. White");
		JCheckBox plum = new JCheckBox("Professor Plum");
		people.add(scarlet);
		people.add(green);
		people.add(peacock);
		people.add(mustard);
		people.add(white);
		people.add(plum);
		
	}
	
	public void createRooms() {
		rooms.setLayout(new GridLayout(5,2));
		rooms.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		JCheckBox kitchen = new JCheckBox("Kitchen");
		JCheckBox lounge = new JCheckBox("Lounge");
		JCheckBox conservatory = new JCheckBox("Conservatory");
		JCheckBox study = new JCheckBox("Study");
		JCheckBox billiard = new JCheckBox("Billiard Room");
		JCheckBox dining = new JCheckBox("Dining Room");
		JCheckBox ballroom = new JCheckBox("Ballroom");
		JCheckBox hall = new JCheckBox("Hall");
		JCheckBox library = new JCheckBox("Library");
		rooms.add(kitchen);
		rooms.add(lounge);
		rooms.add(conservatory);
		rooms.add(study);
		rooms.add(billiard);
		rooms.add(dining);
		rooms.add(ballroom);
		rooms.add(hall);
		rooms.add(library);
	}
	
	public void createWeapons() {
		weapons.setLayout(new GridLayout(3,3));
		weapons.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		JCheckBox candlestick = new JCheckBox("Candlestick");
		JCheckBox pipe = new JCheckBox("Lead Pipe");
		JCheckBox rope = new JCheckBox("Rope");
		JCheckBox knife = new JCheckBox("Knife");
		JCheckBox revolver = new JCheckBox("Revolver");
		JCheckBox wrench = new JCheckBox("Wrench");
		weapons.add(candlestick);
		weapons.add(pipe);
		weapons.add(rope);
		weapons.add(knife);
		weapons.add(revolver);
		weapons.add(wrench);
	}
	
	public void createPersonGuess() {
		personGuess.setLayout(new BorderLayout());
		JComboBox person = new JComboBox();
		person.addItem("");
		person.addItem("Miss Scarlet");
		person.addItem("Mr. Green");
		person.addItem("Mrs. Peacock");
		person.addItem("Colonel Mustard");
		person.addItem("Mrs. White");
		person.addItem("Professor Plum");
		personGuess.add(person, BorderLayout.CENTER);
		personGuess.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
	}
	
	public void createRoomGuess() {
		roomGuess.setLayout(new BorderLayout());
		JComboBox room = new JComboBox();
		room.addItem("");
		room.addItem("Conservatory");
		room.addItem("Kitchen");
		room.addItem("Ballroom");
		room.addItem("Billiard Room");
		room.addItem("Library");
		room.addItem("Study");
		room.addItem("Dining Room");
		room.addItem("Lounge");
		room.addItem("Hall");
		roomGuess.add(room, BorderLayout.CENTER);
		roomGuess.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
	}
	
	public void createWeaponGuess() {
		weaponGuess.setLayout(new BorderLayout());
		JComboBox weapon = new JComboBox();
		weapon.addItem("");
		weapon.addItem("Candlestick");
		weapon.addItem("Knife");
		weapon.addItem("Rope");
		weapon.addItem("Revolver");
		weapon.addItem("Lead Pipe");
		weapon.addItem("Wrench");
		weaponGuess.add(weapon, BorderLayout.CENTER);
		weaponGuess.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
	}
}
