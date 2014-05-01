package Pieces;

import java.util.ArrayList;

import Board.board;

/**
 * @author Bharath Kannan
 * 
 */
public class Pawn implements Piece {

	board b;
	String color;
	String pos;
	private int movecount;

	public int temp;

	public Pawn(board b, String color, String pos) {
		this.b = b;
		this.color = color;
		this.pos = pos;
	}

	public Pawn(board b, String color, String pos, int m) {
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
			//				if(color.equals("w")&&pos.endsWith("8")){
			//					return promote("Q",color,pos);
			//				}
			//				else if(color.equals("b")&&pos.endsWith("1")){
			//					return promote("Q",color,pos);
			//				}
			//				else{
			String og = this.pos;
			movecount++;
			temp = b.getTotBMoves() + b.getTotWMoves();
			this.pos = pos;
			//					return b.move(toString(),og, pos);
			return b.move(og, pos);
			//}
		} else if (isEnPassant(pos)) {
			movecount++;
			temp = b.getTotBMoves() + b.getTotWMoves();
			this.pos = pos;
			return true;
		}
		return false;
	}

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

		if (movecount == 0) {
			if (color.equals("w")) {
				if ((r1 == r2 - 1) && c2 == c1) {
					return !b.isPiece(r2, c2);
				} else if (r1 == r2 - 2 && c2 == c1
						&& Integer.parseInt(current.substring(1)) == 2) {
					return !b.isPiece(r2, c2) && !b.isPiece(r1 + 1, c2);
				} else if (r1 == r2 - 1 && Math.abs(c1 - c2) == 1) {
					return b.isPiece(r2, c2) && !t.contains(this.color);
				} else {
					return false;
				}

			}
			if (color.equals("b")) {
				if ((r1 == r2 + 1) && c2 == c1) {
					return !b.isPiece(r2, c2);
				} else if (r1 == r2 + 2 && c2 == c1
						&& Integer.parseInt(current.substring(1)) == 7) {
					return !b.isPiece(r2, c2) && !b.isPiece(r1 - 1, c2);
				} else if (r1 == r2 + 1 && Math.abs(c1 - c2) == 1) {
					return b.isPiece(r2, c2) && !t.contains(this.color);
				} else {
					return false;
				}

			}
		} else {
			if (color.equals("w")) {
				if ((r1 == r2 - 1) && c2 == c1) {
					return !b.isPiece(r2, c2);
				}
				if (r1 == r2 - 1 && Math.abs(c1 - c2) == 1) {
					return b.isPiece(r2, c2) && !t.contains(this.color);
				}

			}
			if (color.equals("b")) {
				if ((r1 == r2 + 1) && c2 == c1) {
					return !b.isPiece(r2, c2);
				}
				if (r1 == r2 + 1 && Math.abs(c1 - c2) == 1) {
					return b.isPiece(r2, c2) && !t.contains(this.color);
				} else {
					return false;
				}

			}
		}

		return false;

	}

	public String toString() {
		return color + "p";
	}

	@Override
	public String getColor() {

		return color;
	}

	@Override
	public String getPos() {

		return pos;
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

	public ArrayList<String> attackingPositions() {
		String current = pos;
		int c1 = board.convert(current.substring(0, 1));
		int r1 = Integer.parseInt(current.substring(1, 2)) - 1;
		ArrayList<String> list = new ArrayList<String>();
		if (color.equals("w")) {
			if (c1 > 0 && c1 < 7) {
				String atck1 = board.let[c1 - 1] + (r1 + 2);
				String atck2 = board.let[c1 + 1] + (r1 + 2);
				if (!b.getPiece(r1 + 1, c1 - 1).contains("w"))
					list.add(atck1);
				if (!b.getPiece(r1 + 1, c1 + 1).contains("w"))
					list.add(atck2);
			} else if (c1 == 0) {
				String atck2 = board.let[c1 + 1] + (r1 + 2);
				if (!b.getPiece(r1 + 1, c1 + 1).contains("w"))
					list.add(atck2);
			} else if (c1 == 7) {
				String atck1 = board.let[c1 - 1] + (r1 + 2);
				if (!b.getPiece(r1 + 1, c1 - 1).contains("w"))
					list.add(atck1);
			}
		} else {
			if (c1 > 0 && c1 < 7) {
				String atck1 = board.let[c1 - 1] + (r1);
				String atck2 = board.let[c1 + 1] + (r1);
				if (!b.getPiece(r1 - 1, c1 - 1).contains("b"))
					list.add(atck1);
				if (!b.getPiece(r1 - 1, c1 + 1).contains("b"))
					list.add(atck2);
			} else if (c1 == 0) {
				String atck2 = board.let[c1 + 1] + (r1);
				if (!b.getPiece(r1 - 1, c1 + 1).contains("b"))
					list.add(atck2);
			} else if (c1 == 7) {
				String atck1 = board.let[c1 - 1] + (r1 - 2);
				if (!b.getPiece(r1 - 1, c1 - 1).contains("b"))
					list.add(atck1);
			}
		}
		return list;

	}

	public boolean promote(String piece, String color, String pos) {
		Piece p = null;
		switch (piece) {
		case ("N"):
			p = new Knight(this.b, color, pos);
			break;
		case ("B"):
			p = new Bishop(this.b, color, pos);
			break;
		case ("R"):
			p = new Rook(this.b, color, pos);
			break;

		case ("Q"):
			p = new Queen(this.b, color, pos);
			break;
		}
		if (p != null) {
			String og = this.pos;
			movecount++;
			temp = b.getTotBMoves() + b.getTotWMoves();
			this.pos = pos;
			if (!b.move(toString(), og, pos)) {
				return false;
			}
		}
		return b.promote(p);
	}

	public boolean isEnPassant(String pos) {
		String current = this.pos;
		String moveTo = pos;
		int c1 = board.convert(current.substring(0, 1));
		int r1 = Integer.parseInt(current.substring(1, 2)) - 1;
		int c2 = board.convert(moveTo.substring(0, 1));
		int r2 = Integer.parseInt(moveTo.substring(1, 2)) - 1;
		if (color.equalsIgnoreCase("w")) {
			if (r1 == 4) {
				if (Math.abs(c2 - c1) == 1 && Math.abs(r2 - r1) == 1) {
					Pawn bp = null;
					for (Piece p : b.BlackP) {
						if ((p instanceof Pawn) && p.getMoveCount() == 1) {
							Pawn pawn = (Pawn) p;
							int pc = b.convert(p.getPos().substring(0, 1));
							int pr = Integer.parseInt(p.getPos().substring(1)) - 1;
							if (Math.abs(pc - c1) == 1
									&& pr == r1
									&& pawn.temp == b.getTotBMoves()
											+ b.getTotWMoves()) {
								return b.enPassant(this, pawn, r2, c2);
							}
						}
					}
				}
			}
		} else if (color.equalsIgnoreCase("b")) {
			if (r1 == 3) {
				if (Math.abs(c2 - c1) == 1 && Math.abs(r2 - r1) == 1) {
					Pawn bp = null;
					for (Piece p : b.WhiteP) {
						if ((p instanceof Pawn) && p.getMoveCount() == 1) {
							Pawn pawn = (Pawn) p;
							int pc = b.convert(p.getPos().substring(0, 1));
							int pr = Integer.parseInt(p.getPos().substring(1)) - 1;
							if (Math.abs(pc - c1) == 1
									&& pr == r1
									&& pawn.temp == b.getTotBMoves()
											+ b.getTotWMoves()) {
								return b.enPassant(this, pawn, r2, c2);
							}
						}
					}
				}
			}
		}
		return false;
	}

}
