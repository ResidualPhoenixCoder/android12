package com.example.android12.GUIBoard;

import java.util.HashMap;

import Board.board;
import Pieces.Piece;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.android12.R;
import com.example.android12.Control.ChessControl;

public class GUIChessBoard extends View implements IChessBoard {
	private HashMap<String, Button> controls;
	private Square[][] squares;
	private Context parent;
	
	public GUIChessBoard(Context context) {
		super(context);
		parent = context;
		squares = new Square[8][8];
		ActionBarActivity activity = (ActionBarActivity)context;
		this.controls = new HashMap<String, Button>();
		GridLayout chessBoardView = (GridLayout)activity.findViewById(R.id.chessboard);
		chessBoardView.setColumnCount(8);
        chessBoardView.setRowCount(8);

		//test code for now
		board b = new board();
		Draw(b);
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
//				Button sq = new Button(parent);

//				if(p!=null){
//					sq.setText(sq.toString());
//				}
				GridLayout.LayoutParams param =new GridLayout.LayoutParams();
//        		param.rowSpec = GridLayout.spec(i);
//        		param.columnSpec = GridLayout.spec(j);
        		param.width = width/8;
        		param.height = param.width;
        		sq.setLayoutParams(param);
        		chessBoardView.addView(sq);
        		squares[i][j] = sq;
		}
		
		
	}
		
		
	}

	@Override
	public void reDraw(board b) {
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
}
