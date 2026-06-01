package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class King extends Piece {
    /**
     * Constructor for creating the King.
     * 
     * @param color The color of the King.
     * @param pos The initial position of the piece.
     */
    public King(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteKing : Constants.blackKing, pos);
    }

    /**
     * Method for verifying the King's moveset using the map board and the end position. Checks
     * if the end position is legal.
     * 
     * @param board A 2D array map overlooking the position of the piece.
     * @param end The end position.
     * @return if the move is legal.
     */
    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // Check if the move is within one square in any direction
        int rowDiff = Math.abs(board.getPiece(this).getPosition().getRow() - end.getRow());
        int colDiff = Math.abs(board.getPiece(this).getPosition().getCol() - end.getCol());

        boolean isValidMove = (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 0) || (rowDiff == 1 && colDiff == 1);
        
        if(isValidMove && board.getChessGrid()[end.getRow()][end.getCol()] == null) {
            return true;
        } else if(isValidMove && board.getChessGrid()[end.getRow()][end.getCol()] != null) {
            // Check if the piece at the end position is of the opposite color
            return board.getChessGrid()[end.getRow()][end.getCol()].getColor() != this.getColor();
        }

        return false;
    }
}
