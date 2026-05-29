package chess.Pieces;

import chess.util.Alliance;
import chess.util.Constants;
import chess.util.Position;

public class King extends Piece {
    public King(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteKing : Constants.blackKing, pos);
    }
}
