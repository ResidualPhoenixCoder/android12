package com.example.android12.GUIBoard;

import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

import com.example.android12.Model.Game;
import com.example.android12.Model.Move;

/**
 * @author Mark Labrador
 * 
 */
public interface IChessBoard {
	/**
	 * The control registers an action listener to each square on the board.
	 * 
	 * @param al
	 *            eh, Action Listener.
	 */
	public void registerPositionAL(OnClickListener regular_al);

	/**
	 * Registers all button actions that don't deal with the board directly,
	 * namely, the squares.
	 * 
	 * List of Actions + Forward + Backward + AI + Draw + Resign
	 * 
	 * @param name_of_action
	 *            Name of the action stored in a key-value e. g. HashMap
	 * @param al
	 *            eh, Action L.
	 */
	public void registerMajorBoardActionsAL(String name_of_action,
			OnClickListener al);

	/**
	 * Redraws the chess board with the new board b.
	 * 
	 * @param s
	 *            the new string board to draw.
	 */
	public void reDraw(String[][] s);

	/**
	 * @return Returns the board of squares.
	 */
	public ASquare[][] getSquares();

	/**
	 * Receives an error message to display.
	 * 
	 * @param msg
	 *            Error message.
	 */
	public void displayErrorMsg(String msg);

	/**
	 * Returns GUI to a no error state, and erases the previous error message.
	 */
	public void hideErrorMsg();

	/**
	 * Checks whether the GUI is displaying an error message.
	 * 
	 * @return True, it is displaying an error message. False, it is not
	 *         displaying an error message.
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
	 * Returns all GUI elements except for the board back to its original state.
	 * This is normally used for between state moves.
	 * 
	 * This is normally a combination of the other enabler/disabler commands in
	 * this interface.
	 */
	public void setDefaultAuxControlState();

	/**
	 * Returns all GUI elements including the board back to its original state.
	 * This is normally used for rendering a new game.
	 * 
	 * This is normally a combination of the other enabler/disabler commands in
	 * this interface.
	 */
	public void setDefaultState();

	/**
	 * 
	 * Shows the promotion list.
	 */
	public void showPromotionType();

	public void registerPromotionAction(
			android.content.DialogInterface.OnClickListener promote);
	
	public void loadMovesList(List<Move> moveslist);
	public void loadGamesList(List<Game> gameslist);

	void setUpMoveList();
	
	/**
	 * Refreshes the move list data.
	 */
	public void refreshMoveData();
	
	/**
	 * Shows the save game dialog.
	 */
	public void showSaveGame();
	
	/**
	 * Sets up the game save dialog.
	 * @param onSave What to do when Save is clicked.
	 */
	public void setUpSavePopUp(DialogInterface.OnClickListener onSave);
	
	/**
	 * @return Gets the Title of the game that needs to be saved.
	 */
	public String getGameSaveTitle();
	
	public void showGameList();
		
	public void setUpGameListPopUp(DialogInterface.OnClickListener gameSelect);
	
	public void refreshGameListData();
		
	public void disableBoard();
	public void test();

}
