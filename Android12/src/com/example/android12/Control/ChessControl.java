package com.example.android12.Control;



import Board.board;
import Pieces.Pawn;
import Pieces.Piece;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.android12.GUIBoard.IChessBoard;
import com.example.android12.GUIBoard.Square;

public class ChessControl {
	private board backend_board;
	private IChessBoard view_board;
	private String startPos=""; //TODO Suggestion, save a reference to the entire start view.
	private String endPos="";
	public ChessControl(board backend_board, IChessBoard view_board) {
		this.backend_board = backend_board;
		this.view_board = view_board;
		setup();
	}

	private void setup() {
		view_board.registerPositionAL(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * TODO FEATURES
				 * +After clicking the first button, disable non-board buttons to prevent confusion.
				 * +After a successful move is made or button is unselected, re-enable all other non-board functions.
				 */

				Square s = (Square)v;
				int ogColor = s.getBackgroundColor();
				if(startPos.equals("")&&s.getPiece()!=null){
					startPos = s.getPosition();
					s.setBackgroundColor(Color.YELLOW);
				}

				else if(s.getPosition().equals(startPos)&&s.getPiece()!=null){
					startPos = "";
					endPos = "";
					s.setBackgroundColor(ogColor);					
				}

				else{
					endPos = s.getPosition();
				}

				boolean b = false;
				if(startPos.length()>0&&endPos.length()>0){
					b= move();
				}

				if(b){
					view_board.reDraw(backend_board);
					for(Square[] ar :view_board.getSquares()){
						for(Square x : ar){
							if(x.getPosition().equals(startPos)){
								x.resetBackgroundColor();
							}
						}
					}
					
					startPos = "";
					endPos = "";
				}
				/*
				 * v.getId() this is your position.
				 * Parse position.
				 * 
				 * DECIDE WHETHER TO HIGHLIGHT
				 * Check whether the current is occupied.
				 * + If it is, check whether the piece is the same color as whoever's turn.
				 * ++Then you highlight the square.
				 * Otherwise, break.
				 * 
				 * 
				 * if startPos is empty, load this into the startPos.
				 * else if startPos is the same as the square selected again, unselect the current square and break.
				 * Otherwise, fill the end.
				 * 
				 * if both startPos and endPos are filled, then perform the move.
				 * if move returns false, then don't redraw and keep startPos highlighted.
				 * if move returns true, then redraw and clear all highlights. 
				 * 
				 * Basically, call move.
				 */
			}
		});
	}


	private boolean move() {
		boolean result = false;
		board b = backend_board;
		boolean draw = false;
		boolean piecehere = false;
		
		//basically we set up this string with the commands separated by spaces. First type
		//is just commands, which is all ive implemented for now. But we will also need to append things to this 
		//string if there is a draw offered or a promotion being made. We probably could do away with this string, but
		//i didnt want to mess with working code until we have a working version of this.
		String s = startPos+" "+endPos;
		
		//first checks person agrees to draw that has been offered.
		if(draw && s.equalsIgnoreCase("draw")){
			
		}
		
		//goes into appropriate block based on whose turn it is.
		if(b.getMoveCtr()%2==0){
			
			if(s.equalsIgnoreCase("resign")){
				
			}
			
			String cmd[] = s.split(" ");
			
			// if the move is a normal move, it will look for the piece and call its move
			if(cmd.length==2){
				for(Piece p : b.WhiteP){
					if(p.getPos().equals(cmd[0])){
						piecehere = true;
						if(p.move(cmd[1])){
							draw = false;
							result = true;
							break;
						}
						
						//if it doesnt find a piece with that position, do something (i.e message).
						else{
							
						}
					}
				}
			}
			
			// If there are three commands (2 positions +1 more), check if the person is offering a draw.
			if(cmd.length==3){
				if(cmd[2].equalsIgnoreCase("draw?")){
					draw = true;
					for(Piece p:b.WhiteP){
						if(p.getPos().equals(cmd[0])){
							piecehere = true;
							if(p.move(cmd[1])){
								result = true;
								break;
							}
							else{
								
							}
						}
					}
				}

				// else if there are three commands, check if the third command are instructions for promoting.
				else if(cmd[2].length()==1&&Character.isLetter(cmd[2].charAt(0))){
					for(Piece p:b.WhiteP){
						if(p.getPos().equals(cmd[0])&&p instanceof Pawn){
							piecehere = true;
							Pawn a = (Pawn) p ;
							if(a.promote(cmd[2],"w",cmd[1])){
								result = true;
								break;
							}
							else{
								
							}
						}
					}
				}



			}
		}
		else{
			// Same as the top block except for black
			if(s.equalsIgnoreCase("resign")){
				
			}
			String cmd[] = s.split(" ");
			if(cmd.length==2){
				for(Piece p:b.BlackP){
					if(p.getPos().equals(cmd[0])){
						piecehere = true;
						if(p.move(cmd[1])){
							draw = false;
							result = true;
							break;
						}
						else{
							
						}
					}
				}
			}
			if(cmd.length==3){
				if(cmd[2].equalsIgnoreCase("draw?")){
					draw = true;
					for(Piece p:b.BlackP){
						if(p.getPos().equals(cmd[0])){
							piecehere = true;
							if(p.move(cmd[1])){
								result = true;
								break;
							}
							else{
								
							}
						}
					}
				}

				else if(cmd[2].length()==1&&Character.isLetter(cmd[2].charAt(0))){
					for(Piece p:b.BlackP){
						if(p.getPos().equals(cmd[0])&&p instanceof Pawn){
							piecehere = true;
							Pawn a = (Pawn) p ;
							if(a.promote(cmd[2],"b",cmd[1])){
								result = true;
								break;
							}
							else{
								
							}
						}
					}
				}



			}
		}
		
		//some logic stuff, not really important.
		if(s.equalsIgnoreCase("draw"));{
			piecehere = true;
		}

		if(!piecehere){
			result = false;
			
		}

		piecehere = false;
		//everything after this just checks for stuff liek checks, checkmates, and stalemates.
		
		if(b.isBlackCheck()){
			if(b.isBlackCheckMate()){
				
			}
		}
		else if(b.isWhiteCheck()){
			if(b.isWhiteCheckMate()){
				
			}
		}
		else{
			if(b.getMoveCtr()%2==0){
				if(b.isWhiteStaleMate()){
					
				}
			}
			else{
				if(b.isBlackStaleMate()){
					
				}
			}



		}



		//this.backend_board.move(startButtonPos.getPosition(), endButtonPos.getPosition());

		/*
		 * PSEUDOCODE
		 * 
		 * Checks with the non-GUI control to make sure it's valid.
		 * Have both references to the start button/view or end button/view.
		 * Then if the move is valid, then you'd just take the piece from the start view and load it into the end view.
		 * Redraw the board aka result is true.
		 */
		return result;
	}
}
