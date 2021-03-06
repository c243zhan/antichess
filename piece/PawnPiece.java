package antiChess.piece;

import java.awt.Color;
import java.util.ArrayList;
import antiChess.MoveAnnotation;

public class PawnPiece extends Piece {

	public PawnPiece(int x, int y, Color color) {
		super(x, y, color);
	}

	public PawnPiece(Piece copy) {
		super(copy);
	}
	
	@Override
	// returns a set of moves to empty cell or to capture the opponent's piece
	public ArrayList<MoveAnnotation> getPossibleMoves(Piece[][] board) throws Exception {
		ArrayList<MoveAnnotation> possibleMoves = new ArrayList<MoveAnnotation>();
		int offset;

		if (player.equals(Color.WHITE)) {
			offset = -1;
		} else {
			offset = 1;
		}

		if (isValidPoint(row + offset, col) &&
			board[row + offset][col] == null) {
			possibleMoves.add(toMoveAnnotation(row + offset, col));
			
			// Pawn can proceed 2 cells if it hasn't been moved yet
			if (!moved && board[row + 2*offset][col] == null) {
				possibleMoves.add(toMoveAnnotation(row + 2*offset, col));
			}
		}
		if (isValidPoint(row + offset, col + offset) &&
			board[row + offset][col + offset] != null &&
			!board[row + offset][col + offset].getPlayer().equals(player)) {
			possibleMoves.add(toMoveAnnotation(row + offset, col + offset));
		}
		if (isValidPoint(row + offset, col - offset) &&
			board[row + offset][col - offset] != null &&
			!board[row + offset][col - offset].getPlayer().equals(player)) {
			possibleMoves.add(toMoveAnnotation(row + offset, col - offset));
		}

		return possibleMoves;
	}

	@Override
	// returns a set of moves to capture the opponent's piece only
	public ArrayList<MoveAnnotation> getAttackMoves(Piece[][] board) throws Exception {
		ArrayList<MoveAnnotation> attackMoves = new ArrayList<MoveAnnotation>();
		int offset;

		if (player.equals(Color.WHITE)) {
			offset = -1;
		} else {
			offset = 1;
		}

		if (isValidPoint(row + offset, col + offset) &&
			board[row + offset][col + offset] != null &&
			!board[row + offset][col + offset].getPlayer().equals(player)) {
			attackMoves.add(toMoveAnnotation(row + offset, col + offset));
		}
		if (isValidPoint(row + offset, col - offset) &&
			board[row + offset][col - offset] != null &&
			!board[row + offset][col - offset].getPlayer().equals(player)) {
			attackMoves.add(toMoveAnnotation(row + offset, col - offset));
		}

		return attackMoves;
	}

	@Override
	// gets a string that represents a piece
	public String getPieceString() {
		if (player.equals(Color.WHITE)) {
			return "P";
		} else {
			return "p";
		}
	}
}
