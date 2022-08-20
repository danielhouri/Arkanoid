// 314712563

package sprites;
import biuoop.DrawSurface;

/**
 * The Sprite interface represent a game object that can be drawn to the screen (and which is not just a
 * background image).
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d the surface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}