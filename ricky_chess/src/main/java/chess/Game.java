package chess;

import java.io.Serializable;
import java.time.LocalTime;

import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.FileSaver;
import chess.util.Position;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

// absolute positioning system, for every piece, calculate if the pieces are fiendly or not,
// then create a list of movesets. If the user does not select a valid square, do not move there.
public class Game implements Serializable {
    private String player1, player2;

    // default time period
    //Duration p1Time = Duration.minutes(5);
    //Duration p2Time = Duration.minutes(5);

    private LocalTime p1Time;
    //private LocalTime p2Time;

    private Alliance currColor = Alliance.WHITE;

    
    private ChessBoard chessBoard = new ChessBoard();

    //private Label p1TimeLabel = new Label(p1Time.toMinutes());
    //private Label p2TimeLabel = new Label(p2Time.toMinutes());

    public Game() {}

    public void setPlayers(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void init(Stage stage) {
        //initialize the game, such as setting up the board and pieces, and then we can start the game loop.
        Label p1 = new Label("Player 1 Name (white): ");
        Label p2 = new Label("Player 2 Name (black): ");

        //Label error = new Label("");

        TextField p1Name = new TextField();
        TextField p2Name = new TextField();

        HBox p1Info = new HBox(5, p1, p1Name);
        HBox p2Info = new HBox(5, p2, p2Name);

        Button confirm = new Button("Confirm");

        confirm.setOnAction(e -> {
            if(p1Name.getText().isEmpty() || p2Name.getText().isEmpty()) {
                //error.setText("Please enter both player names.");
                setPlayers("p1", "p2");
            } else {
                setPlayers(p1Name.getText(), p2Name.getText());
            }

            System.out.println("Player 1: " + this.player1 + ", Player 2: " + this.player2);

            start(stage);
        });

        VBox names = new VBox(5, p1Info, p2Info, confirm);
        names.setAlignment(Pos.CENTER);

        Scene config = new Scene(names, 480, 480);
        stage.setScene(config);
    }

    public void start(Stage stage) {
        Pane board = new Pane();

        Label p1Name = new Label("Player 1: " + this.player1);
        Label p1Time = new Label("Time left: 5:00");

        Label p2Name = new Label("Player 2: " + this.player2);
        Label p2Time = new Label("Time left: 5:00");

        Label turn = new Label("Current turn: " + this.currColor);  

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(60, 60, (col + row) % 2 != 0 ? Color.WHITE : Color.GREEN);
                square.relocate(col * 60, row * 60);

                if(chessBoard.getChessGrid()[row][col] != null) {    
                    ImageView piece = chessBoard.getChessGrid()[row][col].getPieceView();
                    piece.relocate(col * 60, row * 60);

                    board.getChildren().addAll(square, piece);
                } else {
                    board.getChildren().add(square);
                }
            }
        }

        board.getChildren().forEach(Rectangle -> {
            // logic for selecting and moving pieces.
            Rectangle.setOnMouseClicked(e -> {
                int col = (int) Rectangle.getLayoutX() / 60;
                int lastCol = col;

                int row = (int) Rectangle.getLayoutY() / 60;
                int lastRow = row;
                
                chessBoard.getChessGrid()[row][col].move(chessBoard, new Position(lastRow, lastCol));
                System.out.println("Clicked on square: (" + row + ", " + col + ")");
            });

        });

        board.getChildren().addAll(p1Name, p1Time, p2Name, p2Time, turn);
        p1Name.relocate(500, 10);
        p1Time.relocate(500, 40);

        turn.relocate(500, 230);

        p2Name.relocate(500, 430);
        p2Time.relocate(500, 460);
        

        Scene chess = new Scene(board, 650, 480);
        stage.setScene(chess);
    }

    public void resume(Stage primaryStage) {

    }

    public void save() {
        FileSaver.saveGame(this);
    }

    public String getTimeLeft(Alliance color) {
        long currTime = System.nanoTime();

        // in seconds
        long timeDiff = (System.nanoTime() - currTime) / 1_000_000_000;

        //p1Time.

        int secondsLeft = 0;
        int minutesLeft = 0;
        int hrsLeft = 0;

        return "%f : %f : %f".formatted(hrsLeft, minutesLeft, secondsLeft);
    }

    private int toMinutes() {
        return 0;
    }

    private int toSeconds() {
        return 0;
    }

    private int toHours() {
        return 0;
    }

    @Override
    public String toString() {
        return 
        """
        Game Information:
        player1: %s
        player2: %s
        current state: %s
        """.formatted(
            player1, player2, currColor);
    }
}