package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Bishop extends Piece {
    /**
     * Constructor for creating the Bishop.
     * 
     * @param color The color of the Bishop.
     * @param pos The initial position of the piece.
     */
    public Bishop(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteBishop : Constants.blackBishop, pos);
    }

    /**
     * Method for verifying the Bishop's moveset using the map board and the end position. Checks
     * if the end position is legal.
     * 
     * @param board A 2D array map overlooking the position of the piece.
     * @param end The end position.
     * @return if the move is legal.
     */
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
            
            // return true if the destination square is occu
            return true;
        }

        return false;
    }
}
