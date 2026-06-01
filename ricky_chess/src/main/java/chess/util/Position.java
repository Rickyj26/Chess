package chess.util;
import java.io.Serializable;

/**
 * Utility class for wrapping the piece's position.
 */
public class Position implements Serializable {
    private int row;
    private int col;

    /**
     * Constructor for remembering the piece's position.
     * 
     * @param row The row the piece is at in the 2D array map.
     * @param col The col the piece is at in the 2D array map.
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;

    }

    /**
     * Setter method for overriding initial position.
     * 
     * @param pos The new position.
     */
    public void setPosition(Position pos) {
        this.row = pos.getRow();
        this.col = pos.getCol();
    }

    /**
     * Getter method for getting the current position.
     * 
     * @return The current position.
     */
    public Position getPosition() {
        return this;
    }

    /**
     * Getter method for accessing the specific row of
     * the piece.
     * 
     * @return The row of the piece.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter method for accessing the specific col of
     * the piece.
     * 
     * @return The col of the piece.
     */
    public int getCol() {
        return col;
    }
}
