package chess;

import java.io.Serializable;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Supposedly, this class will be responsible for the game logic, such as turn management, move validation, and game state.
 * This game class will also be responsible for easy save file management, such as saving and loading game states.
 * Currently, this class is temporarily empty, but it will be implemented in the future as the project progresses.
 */
public class Game implements Serializable {
    private String player1, player2;
    
    public State currState = State.IDLE;

    public void setPlayers(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start(Stage stage) {
       Label player1 = new Label("Player 1 Name: ");
       Label player2 = new Label("Player 2 Name: ");

       TextField player1Name = new TextField();
       TextField player2Name = new TextField();

       HBox player1Info = new HBox(5, player1, player1Name);
       HBox player2Info = new HBox(5, player2, player2Name);

       Button confirm = new Button("Confirm");

       confirm.setOnAction(e -> {
           setPlayers(player1Name.getText(), player2Name.getText());
           System.out.println("Player 1: " + this.player1 + ", Player 2: " + this.player2);
       });

       VBox names = new VBox(5, player1Info, player2Info, confirm);
       names.setAlignment(Pos.CENTER);

       Scene config = new Scene(names, 480, 480);
       stage.setScene(config);
    }

    public void resume() {
        
    }

    public void save() {
        FileSaver.saveGame(this);
    }

    public void generateGrid() {
        //for()
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
            player1, player2, currState
        );
    }
}
