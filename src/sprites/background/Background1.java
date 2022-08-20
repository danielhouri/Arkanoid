// 314712563
package sprites.background;

import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * Background 1.
 */
public class Background1 implements Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Design the background
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);

        // Design the geometry forms
        d.setColor(Color.blue);
        d.drawCircle(400, 200, 30);
        d.drawCircle(400, 200, 50);
        d.drawCircle(400, 200, 70);

        d.drawLine(270, 200, 370, 200);
        d.drawLine(430, 200, 530, 200);
        d.drawLine(400, 100, 400, 170);
        d.drawLine(400, 230, 400, 300);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
