package chess.Pieces;

import chess.util.Alliance;
import chess.util.Constants;
import chess.util.Position;

public class Bishop extends Piece {
    public Bishop(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteBishop : Constants.blackBishop, pos);
    }
}
