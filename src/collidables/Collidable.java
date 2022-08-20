// 314712563

package collidables;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return collision shape
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter the ball
     * @param collisionPoint the collision point
     * @param currentVelocity the force the object
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}