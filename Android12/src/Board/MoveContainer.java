package Board;

import java.util.ArrayList;

import Pieces.Piece;


public class MoveContainer{
	private Piece p;
	private ArrayList<String> moves;
	
	public MoveContainer(Piece p){
		this.p = p;
		moves = new ArrayList<String>();
	}
	
	public MoveContainer(Piece p, ArrayList<String> moves){
		this.p = p;
		this.moves = moves;
	}
	
	
	public Piece getPiece(){
		return p;
	}
	
	public ArrayList<String> getMoves(){
		return moves;
	}
	
}