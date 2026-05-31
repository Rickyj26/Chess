package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Bishop extends Piece {
    public Bishop(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteBishop : Constants.blackBishop, pos);
    }

    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // accounts for all 4 directions.
        boolean isDiagonalMove = Math.abs(board.getPiece(this).getPosition().getRow() - end.getRow()) == Math.abs(board.getPiece(this).getPosition().getCol() - end.getCol());

        if(isDiagonalMove) {
            int rowDirection = end.getRow() - board.getPiece(this).getPosition().getRow() > 0 ? 1 : -1;
            int colDirection = end.getCol() - board.getPiece(this).getPosition().getCol() > 0 ? 1 : -1;

            int nextRow = board.getPiece(this).getPosition().getRow() + rowDirection;
            int nextCol = board.getPiece(this).getPosition().getCol() + colDirection;

            while(nextRow != end.getRow() && nextCol != end.getCol()) {
                if(board.getChessGrid()[nextRow][nextCol] != null) {
                    return false;
                }
                
                nextRow += rowDirection;
                nextCol += colDirection;
            }

            // if the destination square is occupied by a piece of the same color, return false.
            if(board.getChessGrid()[end.getRow()][end.getCol()] != null && board.getChessGrid()[end.getRow()][end.getCol()].getColor() == this.getColor()) {
                return false;
            }

            return true;
        }
        return false;
    }
}
