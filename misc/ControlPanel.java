package misc;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import board.Board;


public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton nextPlayer;
	private JButton accusation;
	private JTextField whoseturn;
	private JTextField response;
	private JTextField dietext;
	private JTextField guesstext;
	
	//private Board board;
	//private JButton nextPlayer;
	
	private class ButtonListener implements ActionListener {
		  public void actionPerformed(ActionEvent e)
		  {
		     System.out.println("fuck");
		  }
	}
	
	public ControlPanel() {
		//this.board
		createLayout();
	}
	
	public void createLayout() {
		response = new JTextField(10);
		dietext = new JTextField(5);
		guesstext = new JTextField(18);
		nextPlayer = new JButton("Next Player");
		nextPlayer.addActionListener(new ButtonListener());
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
		dietext.setEditable(false);
		dieRoll.add(label);
		dieRoll.add(dietext);
		dieRoll.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		return dieRoll;
	}
	
	private JPanel guess() {
		JPanel guess = new JPanel();
		guesstext.setEditable(false);
		guess.add(guesstext);
		guess.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return guess;
	}
	
	private JPanel result() {
		JPanel result = new JPanel();
		JLabel resultLabel = new JLabel("Response");
		response.setEditable(false);
		result.add(resultLabel);
		result.add(response);
		result.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return result;
	}
	
	public JTextField getDietext() {
		return dietext;
	}

	public void setDietext(JTextField dietext) {
		this.dietext = dietext;
	}

}
