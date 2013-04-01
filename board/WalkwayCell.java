/*
 * Class: WalkwayCell, child of BoardCell
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class is used to keep track of the walkway cells in the game.
 * At the current moment is barebones, but has the boolean function "isWalkway" returning true and will be used to draw differently on the gui
 */

package board;

import java.awt.Graphics;

// WalkwayCell class body, extending BoardCell
public class WalkwayCell extends BoardCell {
	
	// Default constructor. Simply makes a BoardCell and calls it a WalkwayCell
	public WalkwayCell() {
		super();
	}
	
	// Overridden function from the parent class. Used to signify that this cell is a walkway
	@Override
	public boolean isWalkway() {
		return true;
	}

	@Override
	public void draw(Graphics g, Board b) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
