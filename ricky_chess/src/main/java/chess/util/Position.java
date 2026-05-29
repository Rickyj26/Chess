package chess.util;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public void setPosition(Position pos) {
        this.row = pos.getRow();
        this.col = pos.getCol();
    }

    public Position getPosition() {
        return this;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
