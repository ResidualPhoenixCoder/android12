package com.example.android12.GUIBoard;

import android.view.View.OnClickListener;


public interface IChessBoard {
	/**
	 * The control registers an action listener to each square on the board.
	 * @param al eh, Action Listener.
	 */
	public void registerPositionAL(OnClickListener al);
	
	/**
	 * Registers all button actions that don't deal with the board directly, namely, the squares.
	 * @param name_of_action Name of the action stored in a key-value e. g. HashMap
	 * @param al eh, Action L.
	 */
	public void registerMajorBoardActionsAL(String name_of_action, OnClickListener al);
}
