package com.example.android12;

import Board.board;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.android12.Control.ChessControl;
import com.example.android12.GUIBoard.GUIChessBoard;
import com.example.android12.Model.ChessModel;

public class ChessActivity extends ActionBarActivity{
	private board backend_board;
	private GUIChessBoard chess_board;
	private ChessControl chess_control;
	private ChessModel chess_model;
	
    /* 
     * APPLICATION ENTRY-POINT
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chess);
        
        /*
         * MODEL, VIEW, CONTROL link-ups happen here.
         */
        this.backend_board = new board();
        this.chess_board = new GUIChessBoard(this, backend_board);
        this.chess_model = new ChessModel(this);
        this.chess_control = new ChessControl(backend_board, chess_board, chess_model);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chess, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_chess, container, false);
            return rootView;
        }
    }

}
