// 314712563
package game.levels;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.background.Background4;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the Level 4 of the game.
 */
public class FinalFour implements LevelInformation {
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
    public FinalFour() {
        this.levelName = "Final Four";
        this.numOfBalls = 3;

        this.ballVelocities = new ArrayList<>();
        ballVelocities.add(new Velocity(1, -4));
        ballVelocities.add(new Velocity(-2, -4));
        ballVelocities.add(new Velocity(3, -4));

        this.paddleSpeed = 7;
        this.paddleWidth = 100;
        this.minOfBlocks = 105;

        // Create a background
        this.sprite = new Background4();
        this.blocks = new ArrayList<>();

        // Init the blocks array
        Color[] clr = {Color.gray, Color.RED, Color.YELLOW, Color.GREEN, Color.lightGray, Color.pink, Color.MAGENTA};
        int k = 74;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 10; i++) {
                this.blocks.add(new Block(new Rectangle(new Point(30 + (k * i), 100 + (20 * j)),
                        74, 20), clr[j]));
            }
        }
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