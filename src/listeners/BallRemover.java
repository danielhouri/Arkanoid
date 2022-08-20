// 314712563

package listeners;
import collidables.Block;
import game.Counter;
import game.GameLevel;
import sprites.Ball;

/**
 * This class BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    // Fields
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * The constructor of the class 'BlockRemover'.
     * @param game the game
     * @param removedBlocks the update counter
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit the block
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the block from th lists of collidable and sprites
        hitter.removeFromGame(this.game);

        // Update the counter of blocks
        this.remainingBlocks.decrease(1);
    }
}