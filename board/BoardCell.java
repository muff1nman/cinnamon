/*
 * ABSTRACT Class: BoardCell, parent of WalkwayCell and RoomCell
 * Authors: Brandon Rodriguez, Hunter Lang
 * This class provides the framework needed for the different types of cells in the game
 */
package board;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class BoardCell {
	
	protected int row;
	protected int column;
	protected boolean highlight;
	
	// "is" functions are set to false then overridden in the appropriate cell to suit that cell's needs
	public boolean isWalkway() {
		return false;
	}
	public boolean isRoom() {
		return false;
	}
	public boolean isDoorway() {
		return false;
	}
	
	public boolean containsClick(int x, int y) {
		Rectangle rect = new Rectangle(column*25, row*25, 25, 25);
		return rect.contains(new Point(x, y));
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public abstract void draw(Graphics g, Board b, int z, boolean highlight);
	
	
}
