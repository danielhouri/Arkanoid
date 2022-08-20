// 314712563
package collidables;

import geometry.Point;

/**
 * The CollisionInfo class contain info on the collision.
 */
public class CollisionInfo {
    //Private fields
    private final Point collision;
    private final Collidable object;

    /**
     * The constructor of the collision class.
     * @param collision the point of collision
     * @param object the object collision with
     */
    public CollisionInfo(Point collision, Collidable object) {
        this.collision = collision;
        this.object = object;
    }

    /**
     * The point at which the collision occurs.
     * @return collision point
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * The collidable object involved in the collision.
     * @return the object
     */
    public Collidable collisionObject() {
        return this.object;
    }
}