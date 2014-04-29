package com.example.android12.GUIBoard;

import Board.board;
import android.view.View.OnClickListener;


/**
 * @author Mark Labrador
 *
 */
public interface IChessBoard {
	/**
	 * The control registers an action listener to each square on the board.
	 * @param al eh, Action Listener.
	 */
	public void registerPositionAL(OnClickListener al);
	
	/**
	 * Registers all button actions that don't deal with the board directly, namely, the squares.
	 * 
	 * List of Actions
	 * + Forward
	 * + Backward
	 * + AI
	 * + Draw
	 * + Resign
	 * 
	 * @param name_of_action Name of the action stored in a key-value e. g. HashMap
	 * @param al eh, Action L.
	 */
	public void registerMajorBoardActionsAL(String name_of_action, OnClickListener al);
	
	/**
	 * Redraws the chess board with the new board b.
	 * @param b the new board to draw.
	 */
	public void reDraw(board b);
	
	/**
	 * @return Returns the board of squares.
	 */
	public Square[][] getSquares();
	
	/**
	 * Receives an error message to display.
	 * 
	 * @param msg Error message.
	 */
	public void displayErrorMsg(String msg);
	
	/**
	 * Returns GUI to a no error state, and erases the previous error message. 
	 */
	public void hideErrorMsg();
	
	/**
	 * Checks whether the GUI is displaying an error message.
	 * 
	 * @return True, it is displaying an error message.  False, it is not displaying an error message.
	 */
	public boolean inErrorState();
	
	/**
	 * Disables the forward control.
	 */
	public void disableForward();
	
	/**
	 * Enables the forward control. 
	 */
	public void enableForward();
	
	/**
	 * Disables the backward control.
	 */
	public void disableBackward();
	
	/**
	 * Enables the backward control. 
	 */
	public void enableBackward();
	
	/**
	 * Disables the AI control.
	 */
	public void disableAI();
	
	/**
	 * Enables the AI control.
	 */
	public void enableAI();
	
	/**
	 * Disables the draw control.
	 */
	public void disableDraw();
	
	/**
	 * Enables the draw control.
	 */
	public void enableDraw();
	
	/**
	 * Disables the resign control.
	 */
	public void disableResign();
	
	/**
	 * Enables the resign control. 
	 */
	public void enableResign();
	
	/**
	 * Returns the GUI to its original state.  This is
	 * normally a combination of the other commands in this
	 * interface. 
	 */
	public void setDefaultState();
}
