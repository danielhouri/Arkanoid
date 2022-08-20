// 314712563
package game.levels;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.background.Background1;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the Level 1 of the game.
 */
public class DirectHit implements LevelInformation {

    // Fields
    private final String levelName;
    private final int numOfBalls;
    private final List<Velocity> ballVelocities;
    private final int paddleSpeed;
    private final int paddleWidth;
    private final Sprite sprite;
    private final List<Block> blocks;
    private final int minOfBlocks;

    /**
     * The constructor of the class 'DirectHit'.
     */
    public DirectHit() {
        this.levelName = "Direct Hit";
        this.numOfBalls = 1;

        this.ballVelocities = new ArrayList<>();
        ballVelocities.add(new Velocity(0, -5));

        this.paddleSpeed = 5;
        this.paddleWidth = 100;
        this.minOfBlocks = 1;

        // Create a background
        this.sprite = new Background1();
        this.blocks = new ArrayList<>();
        this.blocks.add(new Block(new Rectangle(new Point(385, 185),  30, 30), Color.RED));
    }

    /**
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     * @return a list with the balls speeds
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the level name will be displayed at the top of the screen
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return this.sprite;
    }

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.minOfBlocks;
    }
}