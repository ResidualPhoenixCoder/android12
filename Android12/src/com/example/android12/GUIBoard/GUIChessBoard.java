package com.example.android12.GUIBoard;

import java.util.HashMap;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.android12.R;

public class GUIChessBoard extends View implements IChessBoard {
	private HashMap<String, Button> controls;
	
	public GUIChessBoard(Context context) {
		super(context);
		this.controls = new HashMap<String, Button>();
		GridLayout layout = (GridLayout)this.findViewById(R.id.chessboard);
		//layout.addView(shit);
		Button test = new Button(null);
	}

	@Override
	public void registerPositionAL(OnClickListener al) {
		// TODO Auto-generated method stub
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
}
