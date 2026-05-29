package chess.Pieces;

import chess.util.Alliance;
import chess.util.Constants;
import chess.util.Position;

public class Rook extends Piece {
    public Rook(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteRook : Constants.blackRook, pos);
    }
}
