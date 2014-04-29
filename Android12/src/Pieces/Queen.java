package Pieces;

import java.util.ArrayList;

import Board.board;
/**
 * @author Bharath Kannan
 *
 */
public class Queen implements Piece{

	
	board b;
	String color;
	String pos;
	private int movecount;
	
	public Queen(board b, String color,String pos){
		this.b = b;
		this.color = color;
		this.pos = pos;
	}
	
	public Queen(board b, String color,String pos,int m){
		this.b = b;
		this.color = color;
		this.pos = pos;
		movecount = m;
	}
	@Override
	public boolean move(String pos) {
		// TODO Auto-generated method stub
		if(pos.length()!=2){
			return false;
		}
			if(isLegal(pos)){
				String og = this.pos;

				
				movecount++;
				this.pos = pos;
//				return b.move(toString(),og, pos);
				return b.move(og, pos);
			}
		return false;
	}

	@Override
	public boolean isLegal(String pos) {
		// TODO Auto-generated method stub
		Rook rook = new Rook(b, color, this.pos);
		Bishop bishop = new Bishop(b, color, this.pos);

		return rook.isLegal(pos)||bishop.isLegal(pos);
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public String getPos() {
		// TODO Auto-generated method stub
		return pos;
	}
	
	public String toString(){
		return color+"Q";
	}

	@Override
	public void setPos(String pos) {
		// TODO Auto-generated method stub
		this.pos = pos;
	}


	@Override
	public void reduceMoveCount() {
		// TODO Auto-generated method stub
		movecount--;	
	}

	@Override
	public int getMoveCount() {
		// TODO Auto-generated method stub
		return movecount;
	}
	
	public ArrayList<String> attackingPositions(){
		String current = pos;
		int c1 = board.convert(current.substring(0,1));
		int r1 = Integer.parseInt(current.substring(1,2))-1;
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0;i<8;i++){
			for( int j = 0; j<8;j++){
				String p = board.let[i]+(j+1);
				if(isLegal(p)){
					list.add(p);
				}
			}
		}
		return list;
	}

}
