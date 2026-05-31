package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Knight extends Piece {
    public Knight(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteKnight : Constants.blackKnight, pos);
    }

    @Override
    public boolean isLegal(ChessBoard chessBoard, Position end) {
        // L shape - 2 in one direction, 1 in the other direction.
        int rowDiff = Math.abs(end.getRow() - this.getPosition().getRow());
        int colDiff = Math.abs(end.getCol() - this.getPosition().getCol());

        boolean isLegalMove = rowDiff == 2 && colDiff == 1 || rowDiff == 1 && colDiff == 2;

        if(isLegalMove && chessBoard.getChessGrid()[end.getRow()][end.getCol()] == null) {
            return true;
        } else if(isLegalMove && chessBoard.getChessGrid()[end.getRow()][end.getCol()] != null && chessBoard.getChessGrid()[end.getRow()][end.getCol()].getColor() != this.getColor()) {
            //capturing logic
            return true;    
        }

        return false;
    }
}
