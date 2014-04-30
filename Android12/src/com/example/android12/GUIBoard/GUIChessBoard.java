package com.example.android12.GUIBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Board.board;
import Pieces.Piece;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android12.R;
import com.example.android12.Control.ChessControl;

public class GUIChessBoard extends View implements IChessBoard {
	private HashMap<String, Button> controls;
	private Square[][] squares;
	private Context parent;
	private GridLayout chessBoardView;
	private board b;
	private boolean inErrorState = false;
	
	public GUIChessBoard(Context context) {
		super(context);
		parent = context;
		squares = new Square[8][8];
		ActionBarActivity activity = (ActionBarActivity)context;
		this.controls = new HashMap<String, Button>();
		chessBoardView = (GridLayout)activity.findViewById(R.id.chessboard);
		chessBoardView.setColumnCount(8);
        chessBoardView.setRowCount(8);

		//test code for now
		b = new board();
		Draw(b);
		createControlButtons();
		ChessControl cc = new ChessControl(b, this);
        
	}

	@Override
	public void registerPositionAL(OnClickListener al) {
		// TODO Auto-generated method stub
		for(Square[] ar : squares){
			for(Square a: ar){
				a.setOnClickListener(al);
			}
		}
	}

	@Override
	public void registerMajorBoardActionsAL(String name_of_action,
			OnClickListener al) {
		/*
		 * TODO Need following buttons:
		 * + Forward (Replay)
		 * + Backward
		 * + AI - Random Selection (meaning first selection) of a Legal Move (Shit)
		 * + Draw
		 * + Resign
		 */
	}
	
	private void createControlButtons(){
		controls.put("Undo", new Button(parent));
		controls.get("Undo").setText("Undo");
		controls.put("Forward", new Button(parent));
		controls.get("Forward").setText("Next");
		controls.put("Draw", new Button(parent));
		controls.get("Draw").setText("Offer Draw");
		controls.put("Resign", new Button(parent));
		controls.get("Resign").setText("Resign");
		controls.put("AI", new Button(parent));
		controls.get("AI").setText("AI");
	      Display display =((ActionBarActivity)parent).getWindowManager().getDefaultDisplay();
	        Point size = new Point();
	        display.getSize(size);
	        int width = size.x/controls.size();

	        int height = size.y/9;
	        
		Iterator<Entry<String, Button>> it = controls.entrySet().iterator();
		ActionBarActivity activity = (ActionBarActivity)parent;
		LinearLayout controlRow = (LinearLayout) activity.findViewById(R.id.button_controls);
		while(it.hasNext()){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
			Map.Entry<String, Button> pairs = it.next();
			pairs.getValue().setLayoutParams(params);
			pairs.getValue().setTextSize(10);
			controlRow.addView(pairs.getValue());
			it.remove();
		}
	}
	
	private void setUpMoveList(){
		ListView movelist = (ListView)((ActionBarActivity)parent).findViewById(R.id.move_list);
		
	}

	private void Draw(board b) {
		// TODO Auto-generated method stub
		GridLayout chessBoardView = (GridLayout)((ActionBarActivity)parent).findViewById(R.id.chessboard);
		chessBoardView.removeAllViews();
		String[][] txtBoard = b.board;
        Display display =((ActionBarActivity)parent).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if(width>height){
        	width = height;
        }
		for(int i = txtBoard.length-1;i>-1;i--){
			for(int j = 0; j<txtBoard[0].length;j++){
				String pos = board.let[j]+(i+1);
				Piece p = null;
				for(Piece pce:b.WhiteP){
					if(pce.getPos().equals(pos)){
						p = pce;
					}
				}
				if(p==null){
					for(Piece pce:b.BlackP){
						if(pce.getPos().equals(pos)){
							p = pce;
						}
					}
		
				}
				String s = ""+i+1;
				Square sq = new Square(parent, p, pos);
				sq.setChessImage();
//				sq.setText(sq.toString());
//				sq.setTextSize(7);

				GridLayout.LayoutParams param =new GridLayout.LayoutParams();

        		param.width = width/9;
        		param.height = param.width;
        		sq.setLayoutParams(param);
        		chessBoardView.addView(sq);
        		squares[i][j] = sq;
		}
		chessBoardView.setPadding(width/18, 5, 0, 0);
		
	}
		
		
	}

	@Override
	public void reDraw(board b) {
		String[][] s = b.board;
		
		for(int i = 0; i<s.length;i++){
			for(int j = 0; j<s[1].length;j++){
				if(!s[i][j].equalsIgnoreCase(squares[i][j].toString())){
					String pos = board.let[j]+(i+1);
					Piece u = null;
					for(Piece p:b.WhiteP){
						if(p.getPos().equals(pos)){
							u = p;
						}
					}
					
					for(Piece p:b.BlackP){
						if(p.getPos().equals(pos)){
							u =p;
						}
					}
					
					squares[i][j].setPiece(u);
					
					
				}
				squares[i][j].setChessImage();
			}
		}
		
		
		
		
		// TODO redraw the board when a piece moves
		/*
		 * 
		 * PSEUDOCODE
		 * Check each square with the txt representation of the board. 
		 * If there is a difference, redraw the image.
		 * 
		 * 
		 * 
		 */
	}

	@Override
	public Square[][] getSquares() {
		// TODO Auto-generated method stub
		return squares;
	}

	@Override
	public void displayErrorMsg(String msg) {
		// TODO Auto-generated method stub
		inErrorState = true;
		Toast.makeText(parent, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void hideErrorMsg() {
		// TODO Auto-generated method stub
		inErrorState = false;
	}

	@Override
	public boolean inErrorState() {
		// TODO Auto-generated method stub
		return inErrorState;
	}

	@Override
	public void disableForward() {
		// TODO Auto-generated method stub
		controls.get("Forward").setEnabled(false);
	}

	@Override
	public void enableForward() {
		// TODO Auto-generated method stub
		controls.get("Forward").setEnabled(true);

	}

	@Override
	public void disableBackward() {
		// TODO Auto-generated method stub
		controls.get("Undo").setEnabled(false);
	}

	@Override
	public void enableBackward() {
		// TODO Auto-generated method stub
		controls.get("Undo").setEnabled(true);

	}

	@Override
	public void disableAI() {
		// TODO Auto-generated method stub
		controls.get("AI").setEnabled(false);
	}

	@Override
	public void enableAI() {
		// TODO Auto-generated method stub
		controls.get("AI").setEnabled(true);
	}

	@Override
	public void disableDraw() {
		// TODO Auto-generated method stub
		controls.get("Draw").setEnabled(false);
	}

	@Override
	public void enableDraw() {
		// TODO Auto-generated method stub
		controls.get("Draw").setEnabled(true);
	}

	@Override
	public void disableResign() {
		// TODO Auto-generated method stub
		controls.get("Resign").setEnabled(false);
	}

	@Override
	public void enableResign() {
		// TODO Auto-generated method stub
		controls.get("Resign").setEnabled(true);
	}

	@Override
	public void setDefaultState() {
		// TODO Auto-generated method stub
		setDefaultAuxControlState();
		b.reset();
		Draw(b);
	}

	@Override
	public void setDefaultAuxControlState() {
		// TODO Auto-generated method stub
		enableAI();
		enableBackward();
		enableDraw();
		enableResign();
		disableForward();
	}
}
