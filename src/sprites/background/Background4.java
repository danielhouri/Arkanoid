// 314712563
package sprites.background;

import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * Background 4.
 */
public class Background4 implements Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Design the background
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}