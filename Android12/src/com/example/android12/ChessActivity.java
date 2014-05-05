package com.example.android12;

import Board.board;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android12.Control.ChessControl;
import com.example.android12.GUIBoard.GUIChessBoard;
import com.example.android12.GUIBoard.IChessBoard;
import com.example.android12.Model.ChessModel;

public class ChessActivity extends ActionBarActivity{
	private board backend_board;
	private IChessBoard chess_board;
	private ChessControl chess_control;
	private ChessModel chess_model;
	
    /* 
     * APPLICATION ENTRY-POINT
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.fragment_chess);
        
        /*
         * MODEL, VIEW, CONTROL link-ups happen here.
         */
        this.backend_board = new board();
        this.backend_board.load();
        this.chess_board = new GUIChessBoard(this, backend_board);
        this.chess_model = new ChessModel(this);
        try {
			this.chess_model.loadGames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			Toast.makeText(this, "Error loading saved games", Toast.LENGTH_LONG).show();
		}
        this.chess_control = new ChessControl(backend_board, chess_board, chess_model);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chess, menu);
        for(int i = 0; i<menu.size();i++){
        	if(((String)menu.getItem(i).getTitle()).equalsIgnoreCase("List Previous Games")){
        		menu.getItem(i).setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						chess_board.showGameList();
						return false;
					}
				});
        	}
        	
        	else if(((String)menu.getItem(i).getTitle()).equalsIgnoreCase("New Game")){
        		menu.getItem(i).setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						
//						backend_board = new board();
//				        backend_board.load();
//						chess_board = new GUIChessBoard(ChessActivity.this, backend_board);
//					     chess_control = new ChessControl(backend_board, chess_board, chess_model);
//					     chess_board.test();
						
						chess_board.setDefaultState();
						chess_control.reset();

					     return false;
					}
				});
        	}
        	
        	else if(((String)menu.getItem(i).getTitle()).equalsIgnoreCase("Save Game")){
        		menu.getItem(i).setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						if(!chess_control.isOldGame()){
							chess_board.showSaveGame();
						}
						return false;
					}
				});
        	}
        	
        }
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
        
        if(id==R.id.list_item){
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
