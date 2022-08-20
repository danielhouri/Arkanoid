// 314712563
package listeners;

import collidables.Block;
import sprites.Ball;

/**
 * Objects that want to be notified of hit events, should implement the HitListener interface,
 * and register themselves with a HitNotifier object using its addHitListener method.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block
     * @param hitter the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}