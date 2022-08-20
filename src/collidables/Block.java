//314712563

package collidables;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block is an object that we collide into (like rectangle).
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Private fields
    private final Rectangle rcl;
    private Color color;
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * The constructor of the class 'Block'.
     * @param rcl the new rectangle
     */
    public Block(Rectangle rcl) {
        this.rcl = rcl;
        this.color = Color.gray;
    }

    /**
     * The constructor of the class 'Block'.
     * @param rcl the new rectangle
     * @param clr the color
     */
    public Block(Rectangle rcl, Color clr) {
        this.rcl = rcl;
        this.color = clr;
    }

    /**
     * The constructor of the class 'Block'.
     * @param upperLeft the new upper left of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Block(Point upperLeft, double width, double height) {
        this.rcl = new Rectangle(upperLeft, width, height);
    }

    /**
     * Return the "collision shape" of the object.
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rcl;
    }

    /**
     * The methode return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter the ball
     * @param collisionPoint the collision point
     * @param currentVelocity the force the object
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] lines = rcl.getEdges();

        // This section change the velocity according to the hit side of the rectangle.
        // For example: if the collision point is with the left side, then change dy to -dy.
        this.notifyHit(hitter);

        if ((lines[Rectangle.UPPER].isPointInRange(collisionPoint)
                || lines[Rectangle.LOWER].isPointInRange(collisionPoint))
                && (lines[Rectangle.RIGHT].isPointInRange(collisionPoint)
                || lines[Rectangle.LEFT].isPointInRange(collisionPoint))) {
            // If the collision occurs near 2 edges
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (lines[Rectangle.UPPER].isPointInRange(collisionPoint)
                || lines[Rectangle.LOWER].isPointInRange(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (lines[Rectangle.RIGHT].isPointInRange(collisionPoint)
                || lines[Rectangle.LEFT].isPointInRange(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    /**
     * The methode draw the ball on the given DrawSurface.
     * @param surface the DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) this.rcl.getUpperLeft().getX();
        int y = (int) this.rcl.getUpperLeft().getY();
        surface.fillRectangle(x, y, (int) this.rcl.getWidth(), (int) this.rcl.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, (int) this.rcl.getWidth(), (int) this.rcl.getHeight());
    }

    /**
     * .
     */
    public void timePassed() {
    }

    /**
     * Add the ball to the spirit list.
     * @param g the game reference
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Add the ball to the spirit list.
     * @param game the game reference
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * The methode set the color.
     * @param clr the new color
     */
    public void setColor(Color clr) {
        this.color = clr;
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener to hit events
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the list of listeners
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * which will be called whenever a hit() occurs, and will notify all of the registered HitListener objects
     * by calling their hitEvent method.
     * @param hitter the ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}