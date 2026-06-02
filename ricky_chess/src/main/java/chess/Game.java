package chess;

import java.io.Serializable;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import chess.Pieces.Piece;
import chess.util.Alliance;
import chess.util.ChessBoard;
import chess.util.CountDownTimer;
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

/**
 * Main class for managing game states and end conditions.
 */
public class Game implements Serializable {
    private String player1, player2;

    // for checking whose turn it is.
    private Alliance currColor = Alliance.WHITE;

    private ChessBoard chessBoard = new ChessBoard();
    private Supplier<Piece> supplier;
    private BooleanSupplier isWhiteTurn;;

    /**
     * Setter for setting the 2 players.
     * 
     * @param player1 The first player
     * @param player2 The second player
     */
    public void setPlayers(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Initialize method for defining the 2 players in the game.
     * 
     * @param stage The stage so we can show different windows.
     */
    public void init(Stage stage) {
        //initialize the game, such as setting up the board and pieces, and then we can start the game loop.
        Label p1 = new Label("Player 1 Name (white): ");
        Label p2 = new Label("Player 2 Name (black): ");

        TextField p1Name = new TextField();
        TextField p2Name = new TextField();

        HBox p1Info = new HBox(5, p1, p1Name);
        p1Info.setAlignment(Pos.CENTER);

        HBox p2Info = new HBox(5, p2, p2Name);
        p2Info.setAlignment(Pos.CENTER);

        Button confirm = new Button("Confirm");

        confirm.setOnAction(e -> {
            if(p1Name.getText().isEmpty() || p2Name.getText().isEmpty()) {
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

    /**
     * Method for starting the chess game.
     * 
     * @param stage The stage so we can show the chess board window.
     */
    public void start(Stage stage) {
        Pane board = new Pane();
        
        CountDownTimer p1Timer = new CountDownTimer(300);
        CountDownTimer p2Timer = new CountDownTimer(300);  

        Label p1Name = new Label(this.player1);
        Label p1Time = new Label(p1Timer.toString());

        Label p2Name = new Label(this.player2);
        Label p2Time = new Label(p2Timer.toString());
        
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

        p1Timer.start(p1Time);
        p2Timer.start(p2Time);

        p1Timer.pause();
        p2Timer.pause();

        board.getChildren().forEach(Rectangle -> {
            // logic for selecting and moving pieces.
            Rectangle.setOnMouseClicked(e -> {
                int col = (int) Rectangle.getLayoutX() / 60;
                int row = (int) Rectangle.getLayoutY() / 60;
                
                if(supplier == null) {
                    Piece p = chessBoard.getChessGrid()[row][col];
                    
                    System.out.println("------------------------------");
                    System.out.println("Clicked on piece: " + p);

                    if(p != null && p.getColor() == currColor) {
                        // piece is saved and selected.
                        supplier = () -> p;
                        
                    }
                } else {
                    supplier.get().move(chessBoard, new Position(row, col));
                    supplier = null;

                    if(currColor == Alliance.WHITE) {
                        p1Timer.pause();
                        p2Timer.resume();

                        currColor = Alliance.BLACK;
                    } else {
                        p1Timer.resume();
                        p2Timer.pause();

                        currColor = Alliance.WHITE;
                    }

                    turn.setText("Current turn: " + currColor);

                    refreshPiecePositions(board);
                }

                System.out.println("Clicked on square: (" + row + ", " + col + ")");
                System.out.println("------------------------------");
            });
        });

        board.getChildren().addAll(p1Name, p1Time, p2Name, p2Time, turn);
        p2Name.relocate(500, 10);
        p2Time.relocate(500, 30);

        turn.relocate(500, 230);

        p1Name.relocate(500, 440);
        p1Time.relocate(500, 460);
        
        Scene chess = new Scene(board, 650, 480);
        stage.setScene(chess);
    }
    
    /**
     * Method for refreshing the images of the pieces.
     * @param board The Pane board so we can add it to the stage.
     */
    private void refreshPiecePositions(Pane board) {
        // Remove all ImageViews from board
        board.getChildren().removeIf(node -> node instanceof ImageView);

        // Re-add all pieces at their current positions
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = chessBoard.getChessGrid()[row][col];
                if (piece != null) {
                    ImageView pieceView = piece.getPieceView();
                    pieceView.relocate(col * 60, row * 60);
                    board.getChildren().add(pieceView);
                }
            }
        }
    }

    /**
     * Method for continuing a past game. However, if there are no saved 
     * games, we make a new window telling the user that there are no
     * saved games.
     * 
     * @param stage The stage so we can show the window if there are no saved games.
     */
    public void resume(Stage stage) {
        Game game = FileSaver.getGame();

        if(game == null) {
            Label errorMsg = new Label("No saved games!");
            Scene previousScene = stage.getScene();

            Button back = new Button("back");

            back.setOnAction(e -> {
                stage.setScene(previousScene);
            });

            VBox error = new VBox(5, errorMsg, back);
            error.setAlignment(Pos.CENTER);
            stage.setScene(new Scene(error, 180, 180));
        } else {
            game.start(stage);
        }
    }

    /**
     * Method for saving this current game.
     */
    public void save() {
        FileSaver.saveGame(this);
    }

    /**
     * Method for overriding the message to game condition when 
     * printing out the Game object.
     */
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