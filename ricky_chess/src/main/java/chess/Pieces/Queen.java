package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Queen extends Piece {
    public Queen(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteQueen : Constants.blackQueen, pos);
    }

    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // for combining it with the rook logic as the rook 
        /*
        int rowDiff = Math.abs(board.getPiece(this).getPosition().getRow() - end.getRow());
        int colDiff = Math.abs(board.getPiece(this).getPosition().getCol() - end.getCol());

        // if the differences are the same, we know it's a diagonal move and use bishop logic.
        boolean isDiagonalMove = rowDiff == colDiff;

        if(isDiagonalMove) {
            
            return tempBishop.isLegal(board, end);
        } else {
            Rook tempRook = new Rook(this.getColor(), this.getPosition());
            return tempRook.isLegal(board, end);
        }
        */

        Bishop tempBishop = new Bishop(this.getColor(), this.getPosition());
        Rook tempRook = new Rook(this.getColor(), this.getPosition());

        return tempBishop.isLegal(board, end) || tempRook.isLegal(board, end);
    }
}
