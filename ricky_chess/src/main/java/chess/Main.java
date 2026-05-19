package chess;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        Label label = new Label("Welcome to chess simulator!");
        
        Button newGame = new Button("New Game");
        Button resume = new Button("Load Game");
        Button exit = new Button("Save and exit");

        exit.setOnAction(e -> {
            System.out.println("Saving and exiting game...");

            // logic for saving game.

            System.out.println("Game saved successfully!");
            primaryStage.close();
        });
        
        Image pawn = new Image(Constants.whiteKing);
        ImageView white = new ImageView(pawn);
        
        // 5 pixels of spacing
        VBox buttons = new VBox(5, white, newGame, resume, exit);
        buttons.setAlignment(Pos.CENTER);
        
        VBox ui = new VBox(20, label, buttons);
        ui.setAlignment(Pos.CENTER);
        
        Scene initalScene = new Scene(ui, SCENE_WIDTH, SCENE_HEIGHT);
        
        primaryStage.setTitle("Awesome Chess Game!");
        primaryStage.setScene(initalScene);
        primaryStage.show();
        
    } 
    
    public void generateGrid(int xTiles, int yTiles) {
           
    }
}