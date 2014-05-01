package com.example.android12.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Game implements Serializable, Comparable<Game>{
	private static final long serialVersionUID = 1L;
	private Date datePlayed;
	private List<Move> movesList;
	
	public Game() {
		this.datePlayed = Calendar.getInstance().getTime();
		this.movesList = new ArrayList<Move>();
	}
	
	public Date getDatePlayed() {
		return this.datePlayed;
	}
	
	public List<Move> getMovesList() {
		return this.movesList;
	}

	@Override
	public int compareTo(Game another) {
		// TODO Auto-generated method stub
		return 0;
	}
}
