// 314712563

package game;

import biuoop.DrawSurface;

/**
 * This interface manage the GUI and frame-rate code.
 */
public interface Animation {
    /**
     * This methode is in charge of the logic of one frame.
     * @param d the surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Stop the loop.
     * @return false (stop the game)
     */
    boolean shouldStop();
}