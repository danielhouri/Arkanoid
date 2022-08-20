// 314712563
package sprites.background;

import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * Background 2.
 */
public class Background2 implements Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Design the background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);

        // Design the geometry forms
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 200, 50);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}