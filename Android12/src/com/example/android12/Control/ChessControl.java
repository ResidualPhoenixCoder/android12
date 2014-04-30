package com.example.android12.Control;

import Board.board;
import Pieces.Pawn;
import Pieces.Piece;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.android12.GUIBoard.ASquare;
import com.example.android12.GUIBoard.IChessBoard;
import com.example.android12.Model.Game;
import com.example.android12.Model.Move;

public class ChessControl {
	private board backend_board;
	private IChessBoard view_board;

	private ASquare startP;
	private ASquare endP;

	private int startOgColor;
	private int endOgColor;

	private boolean draw;
	private boolean resign;

	private Game currGame;

	public ChessControl(board backend_board, IChessBoard view_board) {
		this.backend_board = backend_board;
		this.view_board = view_board;
		this.currGame = new Game();
		setup();
	}

	private void setup() {
		/*
		 * List of Actions +Forward +Backward +AI +Draw +Resign +Promotion
		 */
		view_board.registerPositionAL(new OnClickListener() {
			/* GENERAL MOVE */
			@Override
			public void onClick(View v) {
				/*
				 * v.getId() this is your position. Parse position.
				 * 
				 * DECIDE WHETHER TO HIGHLIGHT Check whether the current is
				 * occupied. + If it is, check whether the piece is the same
				 * color as whoever's turn. ++Then you highlight the square.
				 * Otherwise, break.
				 * 
				 * 
				 * if startPos is empty and the clicked on view contains a
				 * piece, load this into the startPos. else if startPos is the
				 * same as the square selected again, unselect the current
				 * square and break. Otherwise, fill the end.
				 * 
				 * if both startPos and endPos are filled, then perform the
				 * move. if move returns false, then don't redraw and keep
				 * startPos highlighted. if move returns true, then redraw and
				 * clear all highlights.
				 * 
				 * Basically, call move.
				 */

				/*
				 * TODO FEATURES +After clicking the first button, disable
				 * non-board buttons to prevent confusion. +After a successful
				 * move is made or button is unselected, re-enable all other
				 * non-board functions.
				 */

				/*
				 * Side that is moving is determined by the backend board's move
				 * counter.
				 */
				if (v instanceof ASquare) {
					ASquare currSquare = (ASquare) v;

					if (startP == null) {
						if (currSquare.getPiece() != null) {
							startP = currSquare;
							startOgColor = startP.getBackgroundColor();
							startP.setBackgroundColor(ASquare.selectedSquareColor);
						}
					} else if (startP.getPosition().equals(
							currSquare.getPosition())) {
						startP.setBackgroundColor(startOgColor);
						startP = null;
					} else if (endP == null) {
						endP = currSquare;
						endOgColor = endP.getBackgroundColor();
						endP.setBackgroundColor(ASquare.selectedSquareColor);
					}

					/*
					 * Start processing the move, if startP and endP are
					 * non-empty. The invariant here is that they are only
					 * non-empty, if they are valid locations.
					 */
					if (startP != null && endP != null) {
						if (move()) {
							view_board.reDraw(backend_board.board);
						}

						startP.setBackgroundColor(startOgColor);
						startP = null;

						endP.setBackgroundColor(endOgColor);
						endP = null;
					}
				}
			}
		},
		/* PROMOTION */
		new OnClickListener() {
			@Override
			public void onClick(View v) {
		//				if (cmd[2].length() == 1
		//				&& Character.isLetter(cmd[2].charAt(0))) { // else if there are three commands, check if the third command are instructions for promoting.
		//			for (Piece p : backend_board.WhiteP) {
		//				if (p.getPos().equals(cmd[0]) && p instanceof Pawn) {
		//					piecehere = true;
		//					Pawn a = (Pawn) p;
		//					if (a.promote(cmd[2], "w", cmd[1])) {
		//						result = true;
		//						break;
		//					}
		//				}
		//			}
		//		}
			}
		});

		/* FORWARD */
		view_board.registerMajorBoardActionsAL("Forward",
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						//TODO Trigger forward movement based on the list or bring to current position.
					}
				});

		/* BACKWARD */
		view_board.registerMajorBoardActionsAL("Backward",
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Trigger backward movement based on the list or until original board positions are found.
					}
				});

		/* AI */
		view_board.registerMajorBoardActionsAL("AI", new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Trigger the artificial intelligence logic; random selection of first valid move.
			}
		});

		/* DRAW */
		view_board.registerMajorBoardActionsAL("Draw", new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * TODO Check whether this is an offer or accept of a draw.
				 * Keywords: + Offer Draw + Accept Draw
				 */
				if (v instanceof Button) {
					Button drawBtn = (Button) v;
					if (drawBtn.getText().toString()
							.equalsIgnoreCase("Offer Draw")) {
						draw = true;
					} else if (drawBtn.getText().toString()
							.equalsIgnoreCase("Accept Draw")) {
						new_game();
					}
				}
			}
		});

		/* RESIGN */
		view_board.registerMajorBoardActionsAL("Resign", new OnClickListener() {
			@Override
			public void onClick(View v) {
				new_game();
			}
		});
	}

	/*
	 * Resets the entire application to prepare for a new game.
	 */
	//TODO Need to save current game, if this is ending a game.
	private void new_game() {
		backend_board = new board();
		view_board.setDefaultState();
		view_board.reDraw(backend_board.board);
		currGame = new Game();
	}

	/*
	 * PSEUDOCODE
	 * 
	 * Checks with the non-GUI control to make sure it's valid. Have both
	 * references to the start button/view or end button/view. Then if the move
	 * is valid, then you'd just take the piece from the start view and load it
	 * into the end view. Redraw the board aka result is true.
	 */
	private boolean move() {
		boolean result = false;
		boolean piecehere = false;

		//basically we set up this string with the commands separated by spaces. First type
		//is just commands, which is all ive implemented for now. But we will also need to append things to this 
		//string if there is a draw offered or a promotion being made. We probably could do away with this string, but
		//i didnt want to mess with working code until we have a working version of this.
		if (startP != null && endP != null) {
			//String s = startP.getPosition() + " " + endP.getPosition();
			Move currMove = new Move(startP.getPosition(), startP.getPiece(),
					endP.getPosition(), endP.getPiece());

			//goes into appropriate block based on whose turn it is.
			if (backend_board.getMoveCtr() % 2 == 0) {
				//String cmd[] = s.split(" ");

				// if the move is a normal move, it will look for the piece and call its move
				//if (cmd.length == 2) {
					for (Piece p : backend_board.WhiteP) {
						if (p.getPos().equalsIgnoreCase(startP.getPosition())) {
							//piecehere = true;
							if (p.move(endP.getPosition())) {
								//draw = false;
								result = true;
								break;
							}
						}
					}
				//}

				/*
				 * The DRAW command does not happen here.  It happens in the draw event when either user clicks
				 * on the draw command.
				 */
				// If there are three commands (2 positions +1 more), check if the person is offering a draw.
//				if (cmd.length == 3) {
//					if (cmd[2].equalsIgnoreCase("draw?")) {
//						draw = true;
//						for (Piece p : backend_board.WhiteP) {
//							if (p.getPos().equals(cmd[0])) {
//								piecehere = true;
//								if (p.move(cmd[1])) {
//									result = true;
//									break;
//								}
//							}
//						}
//					} else 
				}
			} else {
				//Same as the top block except for black
				if (s.equalsIgnoreCase("resign")) {

				}
				
				String cmd[] = s.split(" ");
				if (cmd.length == 2) {
					for (Piece p : backend_board.BlackP) {
						if (p.getPos().equals(cmd[0])) {
							piecehere = true;
							if (p.move(cmd[1])) {
								draw = false;
								result = true;
								break;
							} else {

							}
						}
					}
				}
				if (cmd.length == 3) {
					if (cmd[2].equalsIgnoreCase("draw?")) {
						draw = true;
						for (Piece p : backend_board.BlackP) {
							if (p.getPos().equals(cmd[0])) {
								piecehere = true;
								if (p.move(cmd[1])) {
									result = true;
									break;
								} else {

								}
							}
						}
					}

					else if (cmd[2].length() == 1
							&& Character.isLetter(cmd[2].charAt(0))) {
						for (Piece p : backend_board.BlackP) {
							if (p.getPos().equals(cmd[0]) && p instanceof Pawn) {
								piecehere = true;
								Pawn a = (Pawn) p;
								if (a.promote(cmd[2], "b", cmd[1])) {
									result = true;
									break;
								} else {

								}
							}
						}
					}

				}
			}

			//some logic stuff, not really important.
			if (s.equalsIgnoreCase("draw"))
				;
			{
				piecehere = true;
			}

			if (!piecehere) {
				result = false;
			}

			piecehere = false;
			//everything after this just checks for stuff liek checks, checkmates, and stalemates.

			if (backend_board.isBlackCheck()) {
				if (backend_board.isBlackCheckMate()) {

				}
			} else if (backend_board.isWhiteCheck()) {
				if (backend_board.isWhiteCheckMate()) {

				}
			} else {
				if (backend_board.getMoveCtr() % 2 == 0) {
					if (backend_board.isWhiteStaleMate()) {

					}
				} else {
					if (backend_board.isBlackStaleMate()) {

					}
				}
			}
		}
		return result;
	}
}
