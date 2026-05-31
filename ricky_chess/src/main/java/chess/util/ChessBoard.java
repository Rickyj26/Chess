package chess.util;

import java.io.Serializable;

import chess.Pieces.Bishop;
import chess.Pieces.King;
import chess.Pieces.Knight;
import chess.Pieces.Pawn;
import chess.Pieces.Piece;
import chess.Pieces.Queen;
import chess.Pieces.Rook;


public class ChessBoard implements Serializable {
    private Piece[][] chessGrid = new Piece[8][8];

    /**
     * Constructor for the 8x8 ChessBoard. Instantiates where each piece should be
     * and provides a Position for tracking pieces.
     */
    public ChessBoard() {
        for(int col = 0; col < 8; col++) {
            chessGrid[1][col] = new Pawn(Alliance.BLACK, new Position(1, col));
            chessGrid[6][col] = new Pawn(Alliance.WHITE, new Position(6, col));
        }

        // 1st row, white pieces.
        chessGrid[7][0] = new Rook(Alliance.WHITE, new Position(7, 0));
        chessGrid[7][1] = new Knight(Alliance.WHITE, new Position(7, 1));
        chessGrid[7][2] = new Bishop(Alliance.WHITE, new Position(7, 2));
        chessGrid[7][3] = new Queen(Alliance.WHITE, new Position(7, 3));
        chessGrid[7][4] = new King(Alliance.WHITE, new Position(7, 4));
        chessGrid[7][5] = new Bishop(Alliance.WHITE, new Position(7, 5));
        chessGrid[7][6] = new Knight(Alliance.WHITE, new Position(7, 6));
        chessGrid[7][7] = new Rook(Alliance.WHITE, new Position(7, 7));

        // 8th row, black pieces.
        chessGrid[0][0] = new Rook(Alliance.BLACK, new Position(0, 0));
        chessGrid[0][1] = new Knight(Alliance.BLACK, new Position(0, 1));
        chessGrid[0][2] = new Bishop(Alliance.BLACK, new Position(0, 2));
        chessGrid[0][3] = new Queen(Alliance.BLACK, new Position(0, 3));
        chessGrid[0][4] = new King(Alliance.BLACK, new Position(0, 4));
        chessGrid[0][5] = new Bishop(Alliance.BLACK, new Position(0, 5));
        chessGrid[0][6] = new Knight(Alliance.BLACK, new Position(0, 6));
        chessGrid[0][7] = new Rook(Alliance.BLACK, new Position(0, 7));
        
    }

    public Piece[][] getChessGrid() {
        return chessGrid;
    }

    public Piece getPiece(Piece p) {
        return chessGrid[p.getPosition().getRow()][p.getPosition().getCol()];
    }
}
