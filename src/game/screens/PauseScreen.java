// 314712563
package game.screens;

import biuoop.DrawSurface;
import game.Animation;

/**
 * This class is a new kind of Animation, used for get a break of the game.
 */
public class PauseScreen implements Animation {

    /**
     * Display a screen with the message paused -- press space to continue until a key is pressed.
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Stop the loop.
     * @return false (stop the game)
     */
    public boolean shouldStop() {
        return true;
    }
}