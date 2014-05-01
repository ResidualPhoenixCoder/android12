package com.example.android12.GUIBoard;

import Board.board;
import Pieces.Piece;
import Pieces.Queen;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.example.android12.R;

/**
 * @author Mark Labrador
 * 
 * The ASquare is loaded drawn onto the board.  Any events occurring on the board
 * will deal with this class as it is what fires events for the listeners to react.
 * All the listeners are registered and stored in the controller classes.
 */
public abstract class ASquare extends ImageView{
	public static final int selectedSquareColor = Color.YELLOW;
	public static final int blackSquareColor = Color.DKGRAY;
	public static final int whiteSquareColor = Color.LTGRAY;
	
	private Piece piece;
	private String position;
	private boolean promotion;
	private Class<?> promotionType;

	protected int bgColor;
	
	public ASquare(Context ctx, Piece piece, String position) {
		super(ctx);
		this.piece = piece;
		this.position = position;
		this.promotion = false;
		this.promotionType = Queen.class;
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
			this.setBackgroundColor(whiteSquareColor);
			bgColor = whiteSquareColor;
		}
		else{
			this.setBackgroundColor(blackSquareColor);
			bgColor = blackSquareColor;
		}
	}
	
	public int getBackgroundColor(){
		return bgColor;
	}
	
	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}

	public Class<?> getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(Class<?> promotionType) {
		this.promotionType = promotionType;
	}
	
	@Override
	public String toString(){	
		if(getPiece()==null){
			if(bgColor == whiteSquareColor){
				return "  ";
			}
			else{
				return "##";
			}
		}
		return getPiece().toString();
		
	}
	
	public void setChessImage(){
		switch(toString()){
		case("wp"):
			setImageResource(R.drawable.pawn_nw);
		break;
		
		case("bp"):
			setImageResource(R.drawable.pawn_nb);
		break;
		
		case("wR"):
			setImageResource(R.drawable.rook_nw);
		break;
		
		case("bR"):
			setImageResource(R.drawable.rook_nb);
		break;
		
		case("wN"):
			setImageResource(R.drawable.knight_nw);
		break;
		
		case("bN"):
			setImageResource(R.drawable.knight_nb);
		break;
		
		case("wB"):
			setImageResource(R.drawable.bishop_nw);
		break;
		
		case("bB"):
			setImageResource(R.drawable.bishop_nb);
		break;
		
		case("wK"):
			setImageResource(R.drawable.king_nw);
		break;
		
		case("bK"):
			setImageResource(R.drawable.king_nb);
		break;
		
		case("wQ"):
			setImageResource(R.drawable.queen_nw);
		break;
		
		case("bQ"):
			setImageResource(R.drawable.queen_nb);
		break;
		
		case("  "):
			setImageResource(android.R.color.transparent);			
			break;
		
		case("##"):
			setImageResource(android.R.color.transparent);
			break;
		
		}
		resetBackgroundColor();
	}
	
}
