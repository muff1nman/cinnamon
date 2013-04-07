package misc;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private ClueGame game;
	
	public ControlPanel(ClueGame g) {
		game = g;
		createLayout();
	}
	
	private class ButtonListener implements ActionListener {
		  public void actionPerformed(ActionEvent e)
		  {
			  if(game.isHumanMustFinish()) {
					JOptionPane.showMessageDialog(game, "You must finish your turn", "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
			  } else {
				  for(int i = 0; i < game.getAllPlayers().size(); i++) {
					  if(game.getAllPlayers().get(i).equals(game.getWhosTurn())){
						  game.setWhosTurn(game.getAllPlayers().get((i+1) % game.getAllPlayers().size()));
					  }
				  }
				  if(game.getWhosTurn().isHuman()) {
					  game.startHumanTurn();
				  } else {
					  ComputerPlayer cpu = (ComputerPlayer) game.getWhosTurn();
					  game.startComputerTurn(cpu);
				  }
				  updatePanel();
			  }
		  }
	}
	
	private void updatePanel() {
		whoseturn.setText(game.getWhosTurn().getName());
		response.setText("Hello");
		guesstext.setText("Guess");
		game.rollDie();
		
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

	public JTextField getWhoseturn() {
		return whoseturn;
	}
	public void setWhoseturn(JTextField whoseturn) {
		this.whoseturn = whoseturn;
	}
	
}
