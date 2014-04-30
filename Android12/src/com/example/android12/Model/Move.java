package com.example.android12.Model;

import java.io.Serializable;

import Pieces.Piece;

public class Move implements Serializable {
	private static final long serialVersionUID = 1L;

	private Piece startPiece;
	private Piece endPiece;

	private String startPos;
	private String endPos;

	private boolean promotion;
	private boolean drawOffered;

	public Move(String startPos, Piece startPiece, String endPos, Piece endPiece) {
		this.startPos = startPos;
		this.startPiece = startPiece;

		this.endPos = endPos;
		this.endPiece = endPiece;
	}

	public Move(String startPos, Piece startPiece, String endPos,
			Piece endPiece, boolean promotion, boolean drawOffered) {
		this(startPos, startPiece, endPos, endPiece);
		this.promotion = promotion;
		this.drawOffered = drawOffered;
	}

	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}
	
	public boolean isDrawOffered() {
		return drawOffered;
	}

	public void setDrawOffered(boolean drawOffered) {
		this.drawOffered = drawOffered;
	}


	public Piece getStartPiece() {
		return startPiece;
	}

	public void setStartPiece(Piece startPiece) {
		this.startPiece = startPiece;
	}

	public Piece getEndPiece() {
		return endPiece;
	}

	public void setEndPiece(Piece endPiece) {
		this.endPiece = endPiece;
	}

	public String getStartPos() {
		return startPos;
	}

	public void setStartPos(String startPos) {
		this.startPos = startPos;
	}

	public String getEndPos() {
		return endPos;
	}

	public void setEndPos(String endPos) {
		this.endPos = endPos;
	}
}