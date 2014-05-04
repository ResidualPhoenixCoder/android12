package com.example.android12.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.R;

public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7224004656112256162L;
	private String title;
	private Calendar datePlayed;
	private List<Move> movesList;
	private CharSequence gameStringList;
	
	public Game() {
		this("");
	}
	
	public Game(String title) {
		this.title = title;
		this.datePlayed = Calendar.getInstance();
		this.movesList = new ArrayList<Move>();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Calendar getDatePlayed() {
		return this.datePlayed;
	}
	
	public List<Move> getMovesList() {
		return this.movesList;
	}
	

	
	@Override
	public String toString(){
		return title;
	}
}
