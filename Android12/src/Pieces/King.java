package Pieces;

import java.util.ArrayList;

import Board.board;

/**
 * @author Bharath Kannan
 * 
 */
public class King implements Piece {

	board b;
	String color;
	String pos;
	private int movecount;

	public King(board b, String color, String pos) {
		this.b = b;
		this.color = color;
		this.pos = pos;
	}

	public King(board b, String color, String pos, int m) {
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

		} else if (isKingSideCastle()) {
			Rook r = getKingSideCastleRook();
			if (r != null) {
				if (color.equals("w") && pos.equalsIgnoreCase("g1")) {
					r.setPos("f1");
					this.pos = pos;
					movecount++;
					return b.KingSideCastle(toString());
				} else if (color.equals("b") && pos.equalsIgnoreCase("g8")) {
					r.setPos("f8");
					this.pos = pos;
					movecount++;
					return b.KingSideCastle(toString());
				}

			}

		} else if (isQueenSideCastle()) {
			Rook r = getQueenSideCastleRook();
			if (r != null) {
				if (color.equals("w") && pos.equalsIgnoreCase("c1")) {
					r.setPos("d1");
					this.pos = pos;
					movecount++;
					return b.QueenSideCastle(toString());
				} else if (color.equals("b") && pos.equalsIgnoreCase("c8")) {
					r.setPos("d8");
					this.pos = pos;
					movecount++;
					return b.QueenSideCastle(toString());
				}
			}

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

		if (t.contains(this.color)) {
			return false;
		}

		if ((Math.abs(r2 - r1) <= 1) && (Math.abs(c2 - c1) <= 1)) {
			return true;
		}

		return false;
	}

	/*
	 * @param currKingPos Position the king is currently at.
	 * 
	 * @param rookPos Position of the rook we want to check a castle possibility
	 * with.
	 * 
	 * @param kingPos Destination position to enable the castle.
	 * 
	 * @param pieces List of pieces for the respective side.
	 * 
	 * @return True, if castling is possible with the above params. False,
	 * otherwise.
	 */
	private boolean castleCheck(String currKingPos, String rookPos,
			String kingPos, ArrayList<Piece> pieces) {
		boolean result = false;
		Rook r = null;
		for (Piece p : pieces) {
			if (p.getPos().equals(rookPos) && (p instanceof Rook)) {
				r = (Rook) p;
			}

			if (r != null) {
				if (r.getPos().equalsIgnoreCase(rookPos)
						&& r.getMoveCount() == 0
						&& currKingPos.equalsIgnoreCase(kingPos)) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * @param pos
	 *            Position to move to and castle.
	 * @return True, castling is possible to pos. False, otherwise.
	 */
	public boolean isCastle(String pos) {
		boolean result = false;
		if (this.movecount == 0) {
			//KINGSIDE AND QUEENSIDE CHECK
			if (color.equalsIgnoreCase("w") && !b.isWhiteCheck()) {
				result = castleCheck(pos, "h1", "g1", b.WhiteP)
						|| castleCheck(pos, "a1", "c1", b.WhiteP);
			} else if (color.equalsIgnoreCase("b") && !b.isBlackCheck()) {
				result = castleCheck(pos, "h8", "g8", b.BlackP)
						|| castleCheck(pos, "a8", "c8", b.BlackP);
			}
		}
		return result;
	}

	public boolean isKingSideCastle() {
		if (movecount != 0) {
			return false;
		}
		Rook r = null;
		if (color.equalsIgnoreCase("w") && !b.isWhiteCheck()) {
			for (Piece p : b.WhiteP) {
				if (p.getPos().equals("h1") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("h1") && kingMove("g1")) {
						return r.getMoveCount() == 0;
					}
				}

			}
		} else if (color.equalsIgnoreCase("b") && !b.isBlackCheck()) {
			for (Piece p : b.BlackP) {
				if (p.getPos().equals("h8") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("h8") && kingMove("g8")) {
						return r.getMoveCount() == 0;
					}
				}

			}
		}
		return false;
	}

	public Rook getKingSideCastleRook() {
		if (!isKingSideCastle()) {
			return null;
		}

		Rook r = null;
		if (color.equalsIgnoreCase("w")) {
			for (Piece p : b.WhiteP) {
				if (p.getPos().equals("h1") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("h1") && kingMove("g1")) {
						return r;
					}
				}

			}
		} else if (color.equalsIgnoreCase("b")) {
			for (Piece p : b.BlackP) {
				if (p.getPos().equals("h8") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("h8") && kingMove("g8")) {
						return r;
					}
				}

			}
		}
		return null;
	}

	public boolean isQueenSideCastle() {
		if (movecount != 0) {
			return false;
		}
		Rook r = null;
		if (color.equalsIgnoreCase("w") && !b.isWhiteCheck()) {
			for (Piece p : b.WhiteP) {
				if (p.getPos().equals("a1") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("a1") && kingMove("c1")) {
						return r.getMoveCount() == 0;
					}
				}

			}
		} else if (color.equalsIgnoreCase("b") && !b.isBlackCheck()) {
			for (Piece p : b.BlackP) {
				if (p.getPos().equals("a8") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("a8") && kingMove("c8")) {
						return r.getMoveCount() == 0;
					}
				}

			}
		}
		return false;
	}

	public Rook getQueenSideCastleRook() {
		if (!isQueenSideCastle()) {
			return null;
		}

		Rook r = null;
		if (color.equalsIgnoreCase("w")) {
			for (Piece p : b.WhiteP) {
				if (p.getPos().equals("a1") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("a1") && kingMove("c1")) {
						return r;
					}
				}

			}
		} else if (color.equalsIgnoreCase("b")) {
			for (Piece p : b.BlackP) {
				if (p.getPos().equals("a8") && (p instanceof Rook)) {
					r = (Rook) p;
				}
				if (r != null) {
					if (rookMove("a8") && kingMove("c8")) {
						return r;
					}
				}

			}
		}
		return null;
	}

	private boolean rookMove(String pos) {
		String current = pos;
		String moveTo = this.pos;
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
		if (this.pos.equals(pos)) {
			return false;
		}
		int x = Math.min(c1, c2);
		int y = Math.max(c2, c1);
		if (r1 == r2) {
			for (int i = x + 1; i < y; i++) {
				if (b.isPiece(r1, i)) {
					return false;
				}
			}

		}
		return true;
	}

	public boolean kingMove(String pos) {
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
		if (this.pos.equals(pos)) {
			return false;
		}
		board board = b.boardClone();
		King k = null;
		if (color.equals("w")) {
			for (Piece p : board.WhiteP) {
				if (p instanceof King) {
					k = (King) p;
					break;
				}
			}
		} else {
			for (Piece p : board.BlackP) {
				if (p instanceof King) {
					k = (King) p;
					break;
				}
			}
		}
		int x = Math.min(c1, c2);
		int y = Math.max(c2, c1);
		if (r1 == r2) {
			for (int i = x + 1; i <= y; i++) {
				String position = board.let[i] + (r1 + 1);
				if (!k.move(position)) {
					return false;
				}
				k.setPos(position);
			}

		}

		return true;
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

	@Override
	public String getColor() {

		return color;
	}

	@Override
	public String getPos() {

		return pos;
	}

	public String toString() {
		return color + "K";
	}

	@Override
	public void setPos(String pos) {

		this.pos = pos;
	}

	@Override
	public void reduceMoveCount() {

		movecount--;
	}

	@Override
	public int getMoveCount() {

		return movecount;
	}
}
