package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Queen extends Piece {
    /**
     * Constructor for creating the Queen.
     * 
     * @param color The color of the Queen.
     * @param pos The initial position of the piece.
     */
    public Queen(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteQueen : Constants.blackQueen, pos);
    }

    /**
     * Method for verifying the Queen's moveset using the map board and the end position. Checks
     * if the end position is legal.
     * 
     * @param board A 2D array map overlooking the position of the piece.
     * @param end The end position.
     * @return if the move is legal.
     */
    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // for combining it with the rook logic as the rook 
        Bishop tempBishop = new Bishop(this.getColor(), this.getPosition());
        Rook tempRook = new Rook(this.getColor(), this.getPosition());

        return tempBishop.isLegal(board, end) || tempRook.isLegal(board, end);
    }
}
