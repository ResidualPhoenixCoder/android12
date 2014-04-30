package com.example.android12.GUIBoard;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import Board.board;
import Pieces.Piece;

/**
 * @author Mark Labrador
 * 
 * The ASquare is loaded drawn onto the board.  Any events occurring on the board
 * will deal with this class as it is what fires events for the listeners to react.
 * All the listeners are registered and stored in the controller classes.
 */
public abstract class ASquare extends Button{
	private Piece piece;
	private String position;
	protected int bgColor;
	
	public ASquare(Context ctx, Piece piece, String position) {
		super(ctx);
		this.piece = piece;
		this.position = position;
	}

	/**
	 * @return The piece currently occupying this square.
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @param piece The piece to occupy this square.
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * @return Retrieves the position this square represents.
	 */
	public String getPosition() {
		return position;
	}

	public void resetBackgroundColor(){
		int r = board.convert(position.substring(0,1));
		int c = Integer.parseInt(position.substring(1));
		if((r+c)%2==0){
			this.setBackgroundColor(Color.LTGRAY);
			bgColor = Color.LTGRAY;
		}
		else{
			this.setBackgroundColor(Color.DKGRAY);
			bgColor = Color.DKGRAY;
		}
	}
	
	public int getBackgroundColor(){
		return bgColor;
	}
}
