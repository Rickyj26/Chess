package chess.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import chess.Game;

public class FileSaver implements Serializable {
    private FileSaver() {}

    /**
     * Method for saving/serializing any game.
     * 
     * @param game The game to save.
     */
    public static void saveGame(Game game) {
        // Implementation for saving the game
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FileSaver.class.getResource(Constants.fileLocation).getFile()))) {
            outputStream.writeObject(game);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Method for returning the game so it can be resumed.
     * 
     * @return Game game object
     */
    public static Game getGame() {
        // returns the game file so Main.java can handle it.
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FileSaver.class.getResource(Constants.fileLocation).getFile()))) {
            return (Game)inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved Files!");
            return null;
        }
    }
}
