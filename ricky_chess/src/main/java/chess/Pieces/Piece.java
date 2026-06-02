package chess.Pieces;

import java.io.Serializable;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Piece implements Serializable {
    // Because these objects are not Serializable.
    private transient final Image piece;
    private transient final ImageView pieceView;

    protected final int xPixels = 60;
    protected final int yPixels = 60;

    private String path;
    
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
        this.path = path;
        this.color = color;
        this.currPosition = position;

        this.piece = new Image(getClass().getResourceAsStream(path), xPixels, yPixels, false, false);
        this.pieceView = new ImageView(this.piece);
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
            // prevents the King from being captured, which is an end condition for the game.
            if(chessGrid.getChessGrid()[end.getRow()][end.getCol()] != null && chessGrid.getChessGrid()[end.getRow()][end.getCol()].equals(chessGrid.findKing(this.color == Alliance.WHITE ? Alliance.BLACK : Alliance.WHITE))) {
                if(chessGrid.isInCheck()) {
                    
                }

                return;
            }

            // sets new position for the piece:
            Position currentPos = chessGrid.getPiece(this).getPosition();

            chessGrid.getChessGrid()[end.getRow()][end.getCol()] = chessGrid.getChessGrid()[currentPos.getRow()][currentPos.getCol()];

            // makes the previous position obsolete:
            chessGrid.getChessGrid()[currentPos.getRow()][currentPos.getCol()] = null;

            this.setPosition(end);

            //System.out.println(chessGrid.getChessGrid()[this.getPosition().getRow()][this.getPosition().getCol()].getPosition());
        } else {
            System.out.println("Move not allowed!");
        }
    }

    @Deprecated
    public void highlightLegalMoves(ChessBoard chessGrid, Pane pane) {
        for(int row = 0; row < chessGrid.getChessGrid().length; row++) {
            for(int col = 0; col < chessGrid.getChessGrid()[row].length; col++) {
                if(this.isLegal(chessGrid, new Position(row, col))) {
                    Rectangle highlight = new Rectangle(60, 60, Color.YELLOW);

                    pane.getChildren().add(highlight);
                    highlight.relocate(col * 60, row * 60);
                }
            }
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
        if(this.pieceView == null) {
            return new ImageView(new Image(getClass().getResourceAsStream(this.path), xPixels, yPixels, false, false));
        }

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