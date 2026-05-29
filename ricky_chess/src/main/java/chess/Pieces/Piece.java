package chess.Pieces;

import java.io.Serializable;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece implements Serializable {
    protected Image piece;
    protected ImageView pieceView;

    protected final int xPixels = 60;
    protected final int yPixels = 60;

    // Color of the pieces.
    private final Alliance color;

    private Position currPosition;

    /*
     * Constructor for the Piece class.
     * @param color The color of the piece (white or black).
     * @param path The path to the image file for the piece.
     * @param position The initial position of the piece.
     */
    public Piece(Alliance color, String path, Position position) {
        this.color = color;

        this.piece = new Image(getClass().getResourceAsStream(path), xPixels, yPixels, true, false);
        this.pieceView = new ImageView(this.piece);

        this.currPosition = position;
    }

    public void capture(ChessBoard chessGrid, Position end) {
        if(chessGrid.getChessGrid()[this.currPosition.getRow()][this.currPosition.getCol()].equals(chessGrid.getChessGrid()[end.getRow()][end.getCol()])) {
            
        }
    }

    public void move(ChessBoard chessGrid, Position end) {
        // move the piece from curr to end, and update the chessGrid accordingly.
        if(chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].isLegal(chessGrid, end)) {
            // sets new position for the piece:
            chessGrid.getChessGrid()[end.getRow()][end.getCol()] = chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()];
            this.setPosition(end);
            
            // makes the previous position obsolete:
            chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()] = null;
        }
    }
    
    public boolean isLegal(ChessBoard board, Position end) {
        return false;
    }

    public void setPosition(Position pos) {
        //getChessGrid()[row][col] = this;
    }

    public Position getPosition() {
        return this.currPosition;
    }

    public Alliance getColor() {
        return this.color;
    }

    public ImageView getPieceView() {
        return this.pieceView;
    }
}