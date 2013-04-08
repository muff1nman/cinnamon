package misc;

import board.Board;

public class HumanPlayer extends Player{

	public HumanPlayer() {
		super();
		this.setHuman(true);
	}
	
	public HumanPlayer(String name, String color, int row, int column) {
		super(name, color, row, column);
	}

	public void makeMove(Board board) {
		board.highlightTargets( this.getRow(), this.getColumn());
	}
	
}
