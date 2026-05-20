package chess;

/*
 * Supposedly, this class will be responsible for the game logic, such as turn management, move validation, and game state.
 * This game class will also be responsible for easy save file management, such as saving and loading game states.
 * Currently, this class is temporarily empty, but it will be implemented in the future as the project progresses.
 */
public class Game {
    private String player1, player2;
    
    public State currState = State.IDLE;

    public Game() {
        
    }

    public void setPlayers(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        
    }

    public void resume() {
        
    }

    @Override
    public String toString() {
        return 
        """
        Game Information:
                """;
            
    }
}
