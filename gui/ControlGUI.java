package gui;
import java.awt.BorderLayout;

import javax.swing.*;

public class ControlGUI extends JFrame{

	private JTextField whoseTurn;
	private JButton nextPlayer;
	private JButton makeAccusation;
	private JPanel panel;
	
	public ControlGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		panel = new JPanel();
		
		createLayout();
	}
	
	
	
	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
		

	}
	
	public void createLayout() {
		JLabel nameLabel = new JLabel("Whose Turn?");
		whoseTurn = new JTextField(10);
		nextPlayer = new JButton("Next Player");
		makeAccusation = new JButton("Make an accusation");
		
		panel.add(nameLabel);
		panel.add(whoseTurn);
		panel.add(nextPlayer);
		panel.add(makeAccusation);
		//panel.add(whoseTurn);
		add (panel);
		/*
		add (nameLabel, BorderLayout.NORTH);
		add (whoseTurn, BorderLayout.CENTER);
		nextPlayer = new JButton("Next Player");
		makeAccusation = new JButton("makeAccusation");
		add(nextPlayer, BorderLayout.NORTH);
		add(makeAccusation, BorderLayout.SOUTH);
		*/
	}

}
