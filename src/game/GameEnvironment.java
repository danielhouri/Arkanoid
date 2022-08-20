// 314712563

package game;

import collidables.Collidable;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class is a collection of such things. The ball will know the game environment, and will use it
 * to check for collisions and direct its movement.
 */
public class GameEnvironment {

    // Private fields
    private final List<Collidable> objects = new ArrayList<>();

    /**
     * Add the given collidable to the environment.
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * Add the given collidable to the environment.
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        this.objects.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory the trajectory of the ball
     * @return the collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> infoList = new ArrayList<>();

        // Get all collision points
        for (Collidable index : objects) {
            Point collPoint = trajectory.closestIntersectionToStartOfLine(index.getCollisionRectangle());
            if (collPoint != null) {
                infoList.add(new CollisionInfo(collPoint, index));
            }
        }

        // Get the closest collision point from the info list
        double min = Utils.FIRST_ITERATION;
        CollisionInfo minInfo = null;
        for (CollisionInfo record : infoList) {
            // Get the distance between the collision point and the current center
            double distance = trajectory.start().distance(record.collisionPoint());

            // -1 is signify the first iteration
            if (min == Utils.FIRST_ITERATION || (min > distance)) {
                min = distance;
                minInfo = record;
            }
        }
        return minInfo;
    }
}