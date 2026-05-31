package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Pawn extends Piece {
    public Pawn(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whitePawn : Constants.blackPawn, pos);
    }

    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // row: + if white, - if black. col: + if right, - if left.
        int rowDiff = Math.abs(board.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].getPosition().getRow() - end.getRow());
        int colDiff = Math.abs(board.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].getPosition().getCol() - end.getCol());

        // if the pawn moves forward by 1 and the end positon is empty, then it's legal.
        // also handles promotion logic.
        if(rowDiff == 1 && colDiff == 0 && board.getChessGrid()[end.getRow()][end.getCol()] == null) {
            return true;
        }

        // capturing logic:
        if(rowDiff == 1 && colDiff == 1 && board.getChessGrid()[end.getRow()][end.getCol()] != null) {
            return true;
        }

        boolean isFirstMove = getColor().equals(Alliance.WHITE) && this.getPosition().getRow() == 6 || getColor().equals(Alliance.BLACK) && this.getPosition().getRow() == 1;

        if(rowDiff == 2 && colDiff == 0 && isFirstMove) {
            return true;
        } else if(getColor().equals(Alliance.BLACK) && rowDiff == 2 && colDiff == 0 && board.getChessGrid()[end.getRow()][end.getCol()].getPosition() == null) {
            return true;
        }

        return false;
    }
}   
