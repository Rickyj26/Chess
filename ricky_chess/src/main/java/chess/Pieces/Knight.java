package chess.Pieces;

import chess.util.Alliance;
import chess.util.Constants;
import chess.util.Position;

public class Knight extends Piece {
    public Knight(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteKnight : Constants.blackKnight, pos);
    }

    
}
