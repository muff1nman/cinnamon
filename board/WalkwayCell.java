/*
 * Class: WalkwayCell, child of BoardCell
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class is used to keep track of the walkway cells in the game.
 * At the current moment is barebones, but has the boolean function "isWalkway" returning true and will be used to draw differently on the gui
 */

package board;

import java.awt.Color;
import java.awt.Graphics;

// WalkwayCell class body, extending BoardCell
public class WalkwayCell extends BoardCell {
	
	//boolean highlight;
	
	public WalkwayCell() {
		super();
		highlight = false;
	}
	
	// Overridden function from the parent class. Used to signify that this cell is a walkway
	@Override
	public boolean isWalkway() {
		return true;
	}

	@Override
	public void draw(Graphics g, Board b, int z, boolean highlight) {		
		int numColumns = b.getNumColumns();
		int numRows = b.getNumRows();
		int row = z/numColumns;
		int column = z - (row*numColumns);
		int pixelModifier = Math.min(b.size().width/numColumns, b.size().height/numRows);
		g.setColor(Color.yellow);
		if (this.highlight) {
			g.setColor(Color.GREEN);
		}
		g.fillRect(column*pixelModifier, row*pixelModifier, pixelModifier, pixelModifier);
		g.setColor(Color.black);
		g.drawRect(column*pixelModifier, row*pixelModifier, pixelModifier, pixelModifier);
		
		
	}
	
	
	

}
