//314712563
package sprites;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * This class is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    private final Counter currentScore;
    private final String level;

    /**
     * The constructor of the class 'ScoreIndicator'.
     * @param currentScore currentScore
     * @param level level name
     */
    public ScoreIndicator(Counter currentScore, String level) {
        this.currentScore = currentScore;
        this.level = level;
    }

    /**
     * Draw the sprite to the screen.
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + this.currentScore.getValue(), 15);
        d.drawText(650, 15, this.level, 15);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}