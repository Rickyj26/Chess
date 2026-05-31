package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Rook extends Piece {
    public Rook(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteRook : Constants.blackRook, pos);
    }

    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // line logic:
        if(board.getPiece(this).getPosition().getRow() == end.getRow()) {
            int startingPos = Math.min(board.getPiece(this).getPosition().getCol(), end.getCol());
            int endingPos = Math.max(board.getPiece(this).getPosition().getCol(), end.getCol());

            for(int colInRow = startingPos; colInRow < endingPos; colInRow++) {
                if(board.getChessGrid()[board.getPiece(this).getPosition().getRow()][colInRow] != null) {
                    return false;
                }
            }
        } else if(board.getPiece(this).getPosition().getCol() == end.getCol()) { 
            int startingPos = Math.min(board.getPiece(this).getPosition().getCol(), end.getCol());
            int endingPos = Math.max(board.getPiece(this).getPosition().getCol(), end.getCol());
            
            for(int rowInCol = startingPos; rowInCol < endingPos; rowInCol++) {
               if(board.getChessGrid()[rowInCol][board.getPiece(this).getPosition().getCol()] != null) {
                   return false;
               }
            }
        }
        
        if(board.getChessGrid()[end.getRow()][end.getCol()] != null && board.getChessGrid()[end.getRow()][end.getCol()].getColor() != this.getColor()) {
            return true;
        } else if(board.getChessGrid()[end.getRow()][end.getCol()] != null && board.getChessGrid()[end.getRow()][end.getCol()].getColor() == this.getColor()) {
            return false;
        }

        return true;
    }
}