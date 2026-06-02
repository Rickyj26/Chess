package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Pawn extends Piece {
    /**
     * Constructor for creating the Pawn.
     * 
     * @param color The color of the Pawn.
     * @param pos The initial position of the piece.
     */
    public Pawn(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whitePawn : Constants.blackPawn, pos);
    }

    /**
     * Method for verifying the Pawn's moveset using the map board and the end position. Checks
     * if the end position is legal.
     * 
     * @param board A 2D array map overlooking the position of the piece.
     * @param end The end position.
     * @return if the move is legal.
     */
    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // row: + if white, - if black. col: + if right, - if left.
        int direction = this.getColor() == Alliance.WHITE ? 1 : -1;

        int rowDiff = (board.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].getPosition().getRow() - end.getRow()) * direction;
        int colDiff = Math.abs(board.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].getPosition().getCol() - end.getCol());

        // if the pawn moves forward by 1 and the end positon is empty, then it's legal.
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

    public boolean isPromotion() {
        return false;
    }
}   
