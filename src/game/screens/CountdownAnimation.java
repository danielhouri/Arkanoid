// 314712563
package game.screens;

import biuoop.DrawSurface;
import game.Animation;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen
 * for (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;

    /**
     * The constructor of the class 'CountdownAnimation'.
     * @param numOfSeconds numOfSeconds
     * @param countFrom countFrom
     * @param gameScreen game sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * @return num of second in frame
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    /**
     * This methode is in charge of the logic of one frame.
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw the gameScreen
        this.gameScreen.drawAllOn(d);

        // Draw the count down
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 5, String.valueOf(countFrom), 32);

        // Check if the countdown is over
        countFrom--;
        if (countFrom == 0) {
            this.stop = true;
        }
    }

    /**
     * Stop the loop.
     * @return false (stop the game)
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}