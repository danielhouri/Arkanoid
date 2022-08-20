// 314712563
package game.screens;

import biuoop.DrawSurface;
import game.Animation;
import java.awt.Color;

/**
 * This class is a new kind of Animation, used for get a break of the game.
 */
public class EndScreen implements Animation {
    // Private fields
    private final int score;
    private final boolean win;

    /**
     * The constructor of the class 'PauseScreen'.
     * @param score the final score of the game
     * @param win the user win or not?
     */
    public EndScreen(int score, Boolean win) {
        this.score = score;
        this.win = win;
    }

    /**
     * Display a screen with the message paused -- press space to continue until a key is pressed.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        if (!this.win) {
            d.setColor(Color.RED);
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        } else {
            d.setColor(Color.GREEN);
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
        }
        d.setColor(Color.BLACK);
        d.drawText(250, (d.getHeight() / 4) * 3, "Press space to exit", 32);
    }

    /**
     * Stop the loop.
     * @return false (stop the game)
     */
    public boolean shouldStop() {
        return true;
    }
}