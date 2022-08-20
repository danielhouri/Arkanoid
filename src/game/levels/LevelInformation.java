// 314712563
package game.levels;

import collidables.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {

    /**
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     * @return a list with the balls speeds
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}
