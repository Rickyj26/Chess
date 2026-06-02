package chess.util;

import java.io.Serializable;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.control.Label;
/**
 * Utility class for managing the countdown timer for each player.
 */
public class CountDownTimer implements Serializable {
    private boolean isPaused = false;
    private boolean isRunning = true;
    private Duration remaining;
    private ScheduledExecutorService scheduler;

    /**
     * Constructor for initializing the countdown timer.
     * @param seconds The initial time in seconds.
     */
    public CountDownTimer(long seconds) {
        this.remaining = Duration.ofSeconds(seconds);
    }

    /**
     * Starts the countdown timer and updates the label every second.
     * @param label The label of the timer.
     */
    public void start(Label label) {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            if (!isPaused && isRunning) {
                if (remaining.getSeconds() > 0) {
                    remaining = remaining.minusSeconds(1);
                    Platform.runLater(() -> label.setText(this.toString()));
                } else {
                    Platform.runLater(() -> label.setText("Time's done!"));
                    stop();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * Pauses the countdown timer.
     */
    public void pause() {
        isPaused = true;
    }

    /**
     * Resumes the countdown timer.
     */
    public void resume() {
        isPaused = false;
    }

    /**
     * Stops the countdown timer.
     */
    public void stop() {
        isRunning = false;
        scheduler.shutdown();
    }

    /**
     * Returns a string representation of the remaining time. For ease of use.
     * 
     * @return The formatted time string.
     */
    @Override
    public String toString() {
        long seconds = remaining.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        
        return String.format("Time left: %02d:%02d:%02d", hours, minutes % 60, seconds % 60);
    }
}
