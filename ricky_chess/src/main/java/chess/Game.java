package chess;

import java.io.Serializable;

import chess.saveFile.FileSaver;
import chess.util.Alliance;
import chess.util.ChessBoard;
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

    //private LocalTime p1Time;
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
        board.setPrefSize(480, 480);
        //HBox board = new HBox();

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

        Scene chess = new Scene(board, 480, 480);
        stage.setScene(chess);
    }

    public void resume(Stage primaryStage) {

    }

    public void save() {
        FileSaver.saveGame(this);
    }

    public void getTimeLeft() {
        
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