package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class King extends Piece {
    public King(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteKing : Constants.blackKing, pos);
    }

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
