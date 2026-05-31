package chess.Pieces;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Constants;
import chess.util.Position;

public class King extends Piece {
    public King(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteKing : Constants.blackKing, pos);
    }

    @Override
    public boolean isLegal(ChessBoard chessBoard, Position newPos) {
        return false;
    }
}
