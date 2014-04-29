package Pieces;

import java.util.ArrayList;

import Board.board;
/**
 * @author Bharath Kannan
 *
 */
public class Bishop implements Piece {

	board b;
	String color;
	String pos;
	private int movecount;
	public Bishop(board b, String color,String pos){
		this.b = b;
		this.color = color;
		this.pos = pos;
	}
	
	public Bishop(board b, String color,String pos,int m){
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
		String current = this.pos;
		String moveTo = pos;
		int c1 = board.convert(current.substring(0,1));
		int r1 = Integer.parseInt(current.substring(1,2))-1;
		int c2 = board.convert(moveTo.substring(0,1));
		int r2 = Integer.parseInt(moveTo.substring(1,2))-1;	
		if(c2<0){
			return false;
		}
		if(r2>7){
			return false;
		}
		String t = b.getPiece(r2, c2);

		if(this.pos.equals(pos)){
			return false;
		}
		
		if(t.contains(this.color)){
			return false;
		}
		
		if(Math.abs(r2-r1) == Math.abs(c2-c1)){
			int x=r1-1;
			int y = c1-1;
			int x2 = r2+1;
			int y2 = c2+1;
			if(r2>r1){
				x = r1+1;
				x2 = r2-1;
			}
			if(c2>c1){
				y = c1+1;
				y2 = c2-1;
			}

			if(r2>r1 && c2>c1){
				for(int row = x; row<=x2;row++){
					for(int col = y; col<=y2;col++){
						if(Math.abs(row-r1) == Math.abs(col-c1)){
							if(b.isPiece(row, col)){
								return false;
							}
						}
					}
				}
			}
			
			else if(r2>r1 && c2<c1){
				for(int row = x; row<=x2;row++){
					for(int col = y; col>=y2;col--){
						if(Math.abs(row-r1) == Math.abs(col-c1)){
							if(b.isPiece(row, col)){
								return false;
							}
						}
					}
				}
			}
			
			else if(r2<r1 && c2>c1){
				for(int row = x; row>=x2;row--){
					for(int col = y; col<=y2;col++){
						if(Math.abs(row-r1) == Math.abs(col-c1)){
							if(b.isPiece(row, col)){
								return false;
							}
						}
					}
				}
			}
			
			else{
				for(int row = x; row>=x2;row--){
					for(int col = y; col>=y2;col--){
						if(Math.abs(row-r1) == Math.abs(col-c1)){
							if(b.isPiece(row, col)){
								return false;
							}
						}
					}
				}
			}
			return true;
		}
		return false;
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
		return color+"B";
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
