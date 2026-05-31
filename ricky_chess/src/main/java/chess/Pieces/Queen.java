package chess.Pieces;

import chess.util.Alliance;
import chess.util.Constants;
import chess.util.Position;

public class Queen extends Piece {
    public Queen(Alliance color, Position pos) {
        super(color, color.equals(Alliance.WHITE) ? Constants.whiteQueen : Constants.blackQueen, pos);
    }

    @Override
    public boolean isLegal(chess.util.ChessBoard chessBoard, Position newPos) {
        return false;
    }
}
