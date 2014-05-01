package Pieces;

import java.util.ArrayList;

import Board.board;

/**
 * @author Bharath Kannan
 * 
 */
public class Rook implements Piece {

	board b;
	String color;
	String pos;
	private int movecount;

	public Rook(board b, String color, String pos) {
		this.b = b;
		this.color = color;
		this.pos = pos;
	}

	public Rook(board b, String color, String pos, int m) {
		this.b = b;
		this.color = color;
		this.pos = pos;
		movecount = m;
	}

	@Override
	public boolean move(String pos) {

		if (pos.length() != 2) {
			return false;
		}
		if (isLegal(pos)) {
			String og = this.pos;

			movecount++;
			this.pos = pos;

			//				return b.move(toString(),og, pos);
			return b.move(og, pos);

		}
		return false;
	}

	@Override
	public boolean isLegal(String pos) {

		String current = this.pos;
		String moveTo = pos;
		int c1 = board.convert(current.substring(0, 1));
		int r1 = Integer.parseInt(current.substring(1, 2)) - 1;
		int c2 = board.convert(moveTo.substring(0, 1));
		int r2 = Integer.parseInt(moveTo.substring(1, 2)) - 1;

		if (c2 < 0) {
			return false;
		}
		if (r2 > 7) {
			return false;
		}
		String t = b.getPiece(r2, c2);
		if (this.pos.equals(pos)) {
			return false;
		}
		int x;
		int y;
		if (r1 == r2) {
			x = Math.min(c1, c2);
			y = Math.max(c1, c2);
			for (int i = x + 1; i < y; i++) {
				if (b.isPiece(r1, i)) {
					return false;
				}
			}

			return !t.contains(this.color);
		} else if (c1 == c2) {
			x = Math.min(r1, r2);
			y = Math.max(r1, r2);
			for (int i = x + 1; i < y; i++) {
				if (b.isPiece(i, c1)) {
					return false;
				}
			}
			return !t.contains(this.color);

		}

		return false;
	}

	@Override
	public String getColor() {

		return color;
	}

	@Override
	public String getPos() {

		return pos;
	}

	public String toString() {
		return color + "R";
	}

	@Override
	public void setPos(String pos) {

		this.pos = pos;
	}

	@Override
	public void reduceMoveCount() {

		movecount--;
	}

	public int getMoveCount() {
		return movecount;
	}

	public ArrayList<String> attackingPositions() {
		String current = pos;
		int c1 = board.convert(current.substring(0, 1));
		int r1 = Integer.parseInt(current.substring(1, 2)) - 1;
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String p = board.let[i] + (j + 1);
				if (isLegal(p)) {
					list.add(p);
				}
			}
		}
		return list;
	}

}
