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

    public Position currPosition;

    /**
     * Constructor for the Piece class.
     *
     * @param color The color of the piece (white or black).
     * @param path The path to the image file for the piece.
     * @param position The initial position of the piece.
     */
    public Piece(Alliance color, String path, Position position) {
        this.color = color;

        this.piece = new Image(getClass().getResourceAsStream(path), xPixels, yPixels, false, false);
        this.pieceView = new ImageView(this.piece);

        this.currPosition = position;
    }

    /**
     * Capturing method for capturing pieces.
     * 
     * @param chessGrid The board of the pieces.
     * @param end The end position.
     */
    @Deprecated
    public void capture(ChessBoard chessGrid, Position end) {
        if(chessGrid.getChessGrid()[this.currPosition.getRow()][this.currPosition.getCol()].equals(chessGrid.getChessGrid()[end.getRow()][end.getCol()])) {
            move(chessGrid, end);
        }
    }

    /**
     * Method for allowing the movement of the pieces.
     * 
     * @param chessGrid The 2D array map of the pieces.
     * @param end The end position.
     */
    public void move(ChessBoard chessGrid, Position end) {
        // move the piece from curr to end, and update the chessGrid accordingly.
        if(chessGrid.getPiece(this).isLegal(chessGrid, end)) {
            // sets new position for the piece:
            Position currentPos = chessGrid.getPiece(this).getPosition();

            chessGrid.getChessGrid()[end.getRow()][end.getCol()] = chessGrid.getChessGrid()[currentPos.getRow()][currentPos.getCol()];

            // makes the previous position obsolete:
            chessGrid.getChessGrid()[currentPos.getRow()][currentPos.getCol()] = null;

            this.setPosition(end);

            System.out.println(chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].getPosition());

            
            /*
            if(chessGrid.getChessGrid()[end.getRow()][end.getCol()].getColor() != this.color) {
                chessGrid.getChessGrid()[end.getRow()][end.getCol()] = chessGrid.getPiece(this);
            }
            */
        } else {
            System.out.println("Move not allowed!");
        }
    }
    
    /**
     * Method for determining if the move is legal.
     * @param board The 2D array map of the pieces.
     * @param end The end position.
     * @return if the move is legal.
     */
    public abstract boolean isLegal(ChessBoard board, Position end);

    /**
     * Setter method for overriding the current position.
     * 
     * @param pos The end position.
     */
    public void setPosition(Position pos) {
        this.currPosition = pos;
        
        int direction = this.color == Alliance.WHITE ? 1 : -1;

        this.pieceView.relocate((xPixels * pos.getCol()) * direction, (yPixels * pos.getRow()) * direction);
    }

    /**
     * Getter method for getting the position of the piece.
     * 
     * @return The current position of the piece.
     */
    public Position getPosition() {
        return this.currPosition;
    }

    /**
     * Getter method for getting the color of the piece.
     * 
     * @return The color of the piece.
     */
    public Alliance getColor() {
        return this.color;
    }

    /**
     * Getter method for getting the image of the piece.
     * 
     * @return The image of the piece.
     */
    public ImageView getPieceView() {
        return this.pieceView;
    }

    /**
     * For getting the name of the selected piece.
     * @return The color and name of the piece.
     */
    @Override
    public String toString() {
        return this.color + " " + this.getClass().getSimpleName();
    }
}