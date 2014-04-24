package Pieces;

import java.util.ArrayList;
/**
 * @author Bharath Kannan
 *
 */
public interface Piece {

	public String getColor();
	
	public String getPos();
	
	public boolean move(String pos);
	
	public boolean isLegal(String pos);
	
	public void setPos(String pos);
	
	public void reduceMoveCount();
	
	public int getMoveCount();
	
	public ArrayList<String> attackingPositions();

}
