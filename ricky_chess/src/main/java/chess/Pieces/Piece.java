package chess.Pieces;

import java.io.Serializable;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece implements Serializable {
    // Because these objects are not Serializable.
    private transient final Image piece;
    private transient final ImageView pieceView;

    protected final int xPixels = 60;
    protected final int yPixels = 60;

    // Color of the pieces.
    private final Alliance color;

    private Position currPosition;

    /**
     * Constructor for the Piece class.
     *
     * @param color The color of the piece (white or black).
     * @param path The path to the image file for the piece.
     * @param position The initial position of the piece.
     */
    public Piece(Alliance color, String path, Position position) {
        this.color = color;

        this.piece = new Image(getClass().getResourceAsStream(path), xPixels, yPixels, false, true);
        this.pieceView = new ImageView(this.piece);

        this.currPosition = position;
    }

    public void capture(ChessBoard chessGrid, Position end) {
        if(chessGrid.getChessGrid()[this.currPosition.getRow()][this.currPosition.getCol()].equals(chessGrid.getChessGrid()[end.getRow()][end.getCol()])) {
            move(chessGrid, end);
        }
    }

    public void move(ChessBoard chessGrid, Position end) {
        // move the piece from curr to end, and update the chessGrid accordingly.
        if(chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].isLegal(chessGrid, end)) {
            // sets new position for the piece:
            chessGrid.getChessGrid()[end.getRow()][end.getCol()] = chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()];
            this.setPosition(end);

            System.out.println(chessGrid.getChessGrid()[end.getRow()][end.getCol()]);
            
            // makes the previous position obsolete:
            chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()] = null;
        } else {
            System.out.println("Move not allowed!");
        }
    }
    
    public boolean isLegal(ChessBoard board, Position end) {
        return false;
    }

    public void setPosition(Position pos) {
        this.currPosition = pos;

        this.pieceView.relocate(xPixels * pos.getCol(), yPixels * pos.getRow());
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

    @Override
    public String toString() {
        return this.color + " " + this.getClass().getSimpleName();
    }
}