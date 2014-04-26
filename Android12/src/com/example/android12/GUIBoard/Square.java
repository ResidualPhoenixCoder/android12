package com.example.android12.GUIBoard;

import Pieces.Piece;
import android.content.Context;

/**
 * @author Mark Labrador
 * 
 * This class is just an instantiable type for the ASquare class.
 * We are not sure if there are special operations required of different
 * squares yet, so we are keeping the design flexible.
 */
public class Square extends ASquare {
	public Square(Context ctx, Piece piece, String position) {
		super(ctx, piece, position);
	}
}
