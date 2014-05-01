package com.example.android12.Model;

import java.util.ArrayList;
import java.util.List;

public class ChessModel {
	private ArrayList<Game> games;
	
	public ChessModel() {
		this.games = new ArrayList<Game>();
	}
	
	public void sortByDate() {
	}
	
	public void sortByTitle() {

	}
	
	public List<Game> getGamesList() {
		return this.games;
	}
}
