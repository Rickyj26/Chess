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
        if(getColor().equals(Alliance.WHITE)) {
                
        }

        
        return false;
    }
}
