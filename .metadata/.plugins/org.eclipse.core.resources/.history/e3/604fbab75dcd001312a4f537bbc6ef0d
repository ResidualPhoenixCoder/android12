package com.example.android12.GUIBoard;

import android.content.Context;
import android.widget.ImageButton;
import Pieces.Piece;

/**
 * @author Mark Labrador
 * 
 * The ASquare is loaded drawn onto the board.  Any events occurring on the board
 * will deal with this class as it is what fires events for the listeners to react.
 * All the listeners are registered and stored in the controller classes.
 */
public abstract class ASquare extends ImageButton{
	private Piece piece;
	private String position;
	
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
}
