// 314712563
package listeners;

import collidables.Block;
import game.Counter;
import sprites.Ball;

/**
 * This class update counter on the screen when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    // Fields
    private final Counter currentScore;

    /**
     * The constructor of the class 'ScoreTrackingListener'.
     * @param scoreCounter scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Update the counter on the screen when a ball hit a block.
     * @param beingHit the block
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}