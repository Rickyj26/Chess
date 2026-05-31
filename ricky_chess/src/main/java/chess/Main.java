package chess;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main extends Application {
    private final int SCENE_WIDTH = 480;
    private final int SCENE_HEIGHT = 480;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Game chessGame = new Game();

        Label label = new Label("Welcome to chess simulator!");
        
        Button newGame = new Button("New Game");
        Button resume = new Button("Load Game");
        Button exit = new Button("Save and exit");

        newGame.setOnAction(e -> {
            System.out.println("Starting new game...");

            // Starts the game for the first time, so we can set up the pieces and the board, and then we can start the game loop.
            chessGame.init(primaryStage);
        });

        resume.setOnAction(e -> {
            System.out.println("Loading saved games...");
            
            // make a new scene where we can select from a max list of 5 saved games,,
            // ordered from most recent to oldest, and then continue the same piece locations.
            // temporary
            chessGame.resume(primaryStage);
            
        });

        exit.setOnAction(e -> {
            System.out.println("Saving and exiting game...");

            // logic for saving game.
            chessGame.save();   

            System.out.println("Game saved successfully!");
            primaryStage.close();
        });

        primaryStage.setOnCloseRequest(e-> {
            System.out.println("Saving and exiting game...");

            // logic for saving game.
            //chessGame.save();   

            System.out.println("Game saved successfully!");
            primaryStage.close();
        });
        
        /*HOME MENU*/
        // Home UI elements. Rudimentary for now, but it will be improved in the future.
        //Image wKing = new Image(Constants.whiteKing, 60, 60, true, false);
        //Image wKing = new Image(getClass().getResourceAsStream(Constants.whiteKing), 60, 60, true, false);
        //Image bKing = new Image(getClass().getResourceAsStream(Constants.blackKing), 60, 60, true, false);
        
        //ImageView king = new ImageView(wKing);
        //king.setX(1000);
        //king.setY(0);

        //HBox logo  = new HBox(king);
        //logo.setAlignment(Pos.TOP_LEFT);
        
        // 5 pixels of spacing
        VBox buttons = new VBox(5, newGame, resume, exit);
        buttons.setAlignment(Pos.CENTER);
        
        VBox ui = new VBox(20, label, buttons);
        ui.setAlignment(Pos.CENTER);
        
        Scene initalScene = new Scene(ui, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("Awesome Chess Game!");
        primaryStage.setScene(initalScene);
        primaryStage.show();
        
    }
}