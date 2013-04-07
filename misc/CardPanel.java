package misc;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CardPanel extends JPanel {

	public CardPanel(ClueGame game) {
		setLayout(new GridLayout(4,1));
		add(new JLabel("My Cards"));
		
		JPanel people = new JPanel();
		JTextArea pcards = new JTextArea();
		JPanel rooms = new JPanel();
		JTextArea rcards = new JTextArea();
		JPanel weapons = new JPanel();
		JTextArea wcards = new JTextArea();
		
		for(Card c : game.getHumanPlayer().getCards()) {
			if(c.getCardType() == Card.CardType.PERSON) pcards.append(c.getName() + "\n");
			else if(c.getCardType() == Card.CardType.ROOM) rcards.append(c.getName() + "\n");
			else wcards.append(c.getName() + "\n");
		}
		pcards.setEditable(false);
		rcards.setEditable(false);
		wcards.setEditable(false);
		people.add(pcards);
		rooms.add(rcards);
		weapons.add(wcards);
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		rooms.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		weapons.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		add(people);
		add(rooms);
		add(weapons);

		
	}
}
