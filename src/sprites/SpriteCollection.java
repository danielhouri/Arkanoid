// 314712563
package sprites;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * This class hold a collection of sprites and supports the addition of new sprites, send notifications to the sprites.
 */
public class SpriteCollection {
    // Private Fields
    private final List<Sprite> objects = new ArrayList<>();

    /**
     * The methode add a new spirit to the list array.
     * @param s the new spirit
     */
    public void addSprite(Sprite s) {
        objects.add(s);
    }

    /**
     * The methode add a new spirit to the list array.
     * @param s the new spirit
     */
    public void removeSprite(Sprite s) {
        objects.remove(s);
    }

    /**
     * The methode call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the Sprites before iterating over them.
        List<Sprite> copyObjects = new ArrayList<>(objects);

        // Notify all objects
        for (Sprite obj : copyObjects) {
            obj.timePassed();
        }
    }

    /**
     * The methode call drawOn(d) on all sprites.
     * @param d the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite obj : objects) {
            obj.drawOn(d);
        }
    }
}