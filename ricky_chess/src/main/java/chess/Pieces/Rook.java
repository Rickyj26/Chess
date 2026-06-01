package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class Rook extends Piece {
    /**
     * Constructor for creating the Rook.
     * 
     * @param color The color of the Rook.
     * @param pos The initial position of the piece.
     */
    public Rook(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteRook : Constants.blackRook, pos);
    }

    /**
     * Checks if the Rook Move is legal by checking if the move in the same
     * row (horizontal move) or same column (vertical move). 
     * Then, validates if there are any pieces in the way.
     * Finally, checks if the destination square is occupied by a piece of the same color.
     * 
     * @param board A 2D array map overlooking the position of the piece.
     * @param end The end position.
     * @return if the move is legal.
     */
    @Override
    public boolean isLegal(ChessBoard board, Position end) {
        // before doing any calcs, check if we're trying to capture one of our pieces. If true, return false. Dont wan't to do
        // this AFTER we've realized the move is bogus.
        if(board.getChessGrid()[end.getRow()][end.getCol()] != null && board.getChessGrid()[end.getRow()][end.getCol()].getColor() == this.getColor()) {
            return false;
        }

        // line logic:
        if(board.getPiece(this).getPosition().getRow() == end.getRow()) {
            int startingPos = Math.min(board.getPiece(this).getPosition().getCol(), end.getCol());
            int endingPos = Math.max(board.getPiece(this).getPosition().getCol(), end.getCol());

            for(int colInRow = startingPos; colInRow < endingPos; colInRow++) {
                if(board.getChessGrid()[board.getPiece(this).getPosition().getRow()][colInRow] != null) {
                    return false;
                } 
            }

            return true;

        } else if(board.getPiece(this).getPosition().getCol() == end.getCol()) { 
            int startingPos = Math.min(board.getPiece(this).getPosition().getRow(), end.getRow());
            int endingPos = Math.max(board.getPiece(this).getPosition().getRow(), end.getRow());
            
            for(int rowInCol = startingPos; rowInCol < endingPos; rowInCol++) {
               if(board.getChessGrid()[rowInCol][board.getPiece(this).getPosition().getCol()] != null) {
                   return false;
               }
            }

            return true;
        }
        
        // return false for any bogus move.
        return false;
    }
}