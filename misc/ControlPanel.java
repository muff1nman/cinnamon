package misc;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton nextPlayer;
	private JButton accusation;
	private JTextField whoseturn;
	
	
	public ControlPanel() {
		createLayout();
	}
	
	public void createLayout() {
		nextPlayer = new JButton("Next Player");
		accusation = new JButton("Make an Accusation");
		JLabel turn = new JLabel("Whose turn?");
		whoseturn = new JTextField(18);
		whoseturn.setEditable(false);
		whoseturn.enableInputMethods(false);
		JPanel panel1 = new JPanel();
		panel1.add(turn);
		panel1.add(whoseturn);
		setLayout(new GridLayout(0,3));
		add(panel1);
		add(nextPlayer);
		add(accusation);
		add(dieRoll());
		add(guess());
		add(result());
		
	}
	
	private JPanel dieRoll() {
		JPanel dieRoll = new JPanel();
		JLabel label = new JLabel("Roll");
		JTextField text = new JTextField(5);
		text.setEditable(false);
		dieRoll.add(label);
		dieRoll.add(text);
		dieRoll.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		return dieRoll;
	}
	
	private JPanel guess() {
		JPanel guess = new JPanel();
		JTextField text = new JTextField(18);
		text.setEditable(false);
		guess.add(text);
		guess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return guess;
	}
	
	private JPanel result() {
		JPanel result = new JPanel();
		JLabel resultLabel = new JLabel("Response");
		JTextField response = new JTextField(10);
		response.setEditable(false);
		result.add(resultLabel);
		result.add(response);
		result.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return result;
	}

}
