package com.example.android12.GUIBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Board.board;
import Pieces.Piece;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android12.R;
import com.example.android12.Model.Game;
import com.example.android12.Model.Move;

public class GUIChessBoard extends View implements IChessBoard {
	private List<Game> gamesList;
	private List<Move> currMovesList;
	
	private HashMap<String, Button> controls;
	private Square[][] squares;
	private Context parent;
	private GridLayout chessBoardView;
	private board b;
	private boolean inErrorState = false;
	private AlertDialog.Builder promPieces;
	public static final CharSequence[] values = { "Queen", "Rook", "Knight",
			"Bishop" };

	//TODO There should be no reference to a board.  The view should be unknowledgeable about the control.
	public GUIChessBoard(Context context, board b) {
		super(context);
		this.currMovesList = new ArrayList<Move>();
		this.gamesList = new ArrayList<Game>();
		this.parent = context;
		this.squares = new Square[8][8];
		this.b = b;
		ActionBarActivity activity = (ActionBarActivity) context;
		this.controls = new HashMap<String, Button>();
		this.chessBoardView = (GridLayout) activity
				.findViewById(R.id.chessboard);
		this.chessBoardView.setColumnCount(8);
		this.chessBoardView.setRowCount(8);
		Draw(this.b.board);
		//setUpMoveList();
		createControlButtons();
		this.setUpPopUp();
	}

	@Override
	public void registerPositionAL(OnClickListener regular_al) {
		for (Square[] ar : squares) {
			for (Square a : ar) {
				a.setOnClickListener(regular_al);
			}
		}
	}

	@Override
	public void registerMajorBoardActionsAL(String name_of_action,
			OnClickListener al) {
		if (controls.containsKey(name_of_action)) {
			this.controls.get(name_of_action).setOnClickListener(al);
		}
	}

	@Override
	public void registerPromotionAction(DialogInterface.OnClickListener promote) {
		this.promPieces.setItems(values, promote);
	}

	private void createControlButtons() {
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
		Display display = ((ActionBarActivity) parent).getWindowManager()
				.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x / (controls.size()+1);
		int height = size.y / 10;
		Iterator<Entry<String, Button>> it = controls.entrySet().iterator();
		ActionBarActivity activity = (ActionBarActivity) parent;
		LinearLayout controlRow = (LinearLayout) activity
				.findViewById(R.id.button_controls);
		int gap = (size.x-width*controls.size())/controls.size();
		while (it.hasNext()) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					width, height);
			Map.Entry<String, Button> pairs = it.next();
			params.rightMargin = gap;
			pairs.getValue().setLayoutParams(params);
			pairs.getValue().setTextSize(9);
			pairs.getValue().setBackgroundColor(Color.LTGRAY);
			controlRow.addView(pairs.getValue());
			it.remove();
		}
	}

	@Override
	public void setUpMoveList() {
		ListView movelist = (ListView) ((ActionBarActivity) parent)
				.findViewById(R.id.move_list);
		Display display = ((ActionBarActivity) parent).getWindowManager()
				.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		GridLayout.LayoutParams params = new GridLayout.LayoutParams();
		if (width < height) {
			params.height = height - width-height/5;
		}
		movelist.setLayoutParams(params);
		movelist.requestLayout();
		ArrayAdapter<Move> ma = new ArrayAdapter<>(parent,R.layout.mytextview, currMovesList);
		movelist.setAdapter(ma);
		movelist.setPadding(width/18, 0, 0, 5);
		
	}

	private void Draw(String[][] txtBoard) {
		GridLayout chessBoardView = (GridLayout) ((ActionBarActivity) parent)
				.findViewById(R.id.chessboard);
		chessBoardView.removeAllViews();
		//String[][] txtBoard = b.board;
		Display display = ((ActionBarActivity) parent).getWindowManager()
				.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		if (width > height) {
			width = height;
		}
		for (int i = txtBoard.length - 1; i > -1; i--) {
			for (int j = 0; j < txtBoard[0].length; j++) {
				String pos = board.let[j] + (i + 1);
				Piece p = null;
				for (Piece pce : b.WhiteP) {
					if (pce.getPos().equals(pos)) {
						p = pce;
					}
				}
				if (p == null) {
					for (Piece pce : b.BlackP) {
						if (pce.getPos().equals(pos)) {
							p = pce;
						}
					}

				}

				String s = "" + i + 1;
				Square sq = new Square(parent, p, pos);
				sq.setChessImage();

				GridLayout.LayoutParams param = new GridLayout.LayoutParams();

				param.width = width / 9;
				param.height = param.width;
				sq.setLayoutParams(param);
				chessBoardView.addView(sq);
				squares[i][j] = sq;
			}
			chessBoardView.setPadding(width / 18, 5, 0, 5);
		}

	}

	/*
	 * PSEUDOCODE Check each square with the txt representation of the board. If
	 * there is a difference, redraw the image.
	 */
	@Override
	public void reDraw(String[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[1].length; j++) {
				if (!s[i][j].equalsIgnoreCase(squares[i][j].toString())) {
					String pos = board.let[j] + (i + 1);
					Piece u = null;
					for (Piece p : b.WhiteP) {
						if (p.getPos().equals(pos)) {
							u = p;
						}
					}

					for (Piece p : b.BlackP) {
						if (p.getPos().equals(pos)) {
							u = p;
						}
					}

					squares[i][j].setPiece(u);

				}
				squares[i][j].setChessImage();
			}
		}
	}

	private void setUpPopUp() {
		final ArrayList<String> list = new ArrayList<String>();
		promPieces = new AlertDialog.Builder(parent);
		promPieces.setTitle("Pick a piece.");

	}

	public void showPromotionType() {
		promPieces.show();
	}

	@Override
	public ASquare[][] getSquares() {
		return squares;
	}

	@Override
	public void displayErrorMsg(String msg) {
		inErrorState = true;
		Toast.makeText(parent, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void hideErrorMsg() {
		inErrorState = false;
	}

	@Override
	public boolean inErrorState() {
		return inErrorState;
	}

	@Override
	public void disableForward() {
		controls.get("Forward").setEnabled(false);
	}

	@Override
	public void enableForward() {
		controls.get("Forward").setEnabled(true);

	}

	@Override
	public void disableBackward() {
		controls.get("Undo").setEnabled(false);
	}

	@Override
	public void enableBackward() {
		controls.get("Undo").setEnabled(true);

	}

	@Override
	public void disableAI() {
		controls.get("AI").setEnabled(false);
	}

	@Override
	public void enableAI() {
		controls.get("AI").setEnabled(true);
	}

	@Override
	public void disableDraw() {
		controls.get("Draw").setEnabled(false);
	}

	@Override
	public void enableDraw() {
		controls.get("Draw").setEnabled(true);
	}

	@Override
	public void disableResign() {
		controls.get("Resign").setEnabled(false);
	}

	@Override
	public void enableResign() {
		controls.get("Resign").setEnabled(true);
	}

	@Override
	public void setDefaultState() {
		setDefaultAuxControlState();
		b.reset();
		Draw(b.board);
	}

	@Override
	public void setDefaultAuxControlState() {
		enableAI();
		enableBackward();
		enableDraw();
		enableResign();
		disableForward();
	}

	@Override
	public void loadMovesList(List<Move> moveslist) {
		this.currMovesList = moveslist;
	}

	@Override
	public void loadGamesList(List<Game> gameslist) {
		this.gamesList = gameslist;
	}
}
