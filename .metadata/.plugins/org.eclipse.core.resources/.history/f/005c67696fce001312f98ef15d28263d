package com.example.android12.Control;

import Board.board;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.android12.GUIBoard.IChessBoard;

public class ChessControl {
	private board backend_board;
	private IChessBoard view_board;
	private String startPos; //TODO Suggestion, save a reference to the entire start view.
	private String endPos;
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
		boolean result = this.backend_board.move(startPos, endPos);
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
