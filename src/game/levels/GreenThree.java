// 314712563
package game.levels;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.background.Background3;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the Level 3 of the game.
 */
public class GreenThree implements LevelInformation {
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
    public GreenThree() {
        this.levelName = "Green 3";
        this.numOfBalls = 2;

        this.ballVelocities = new ArrayList<>();
        ballVelocities.add(new Velocity(1, -4));
        ballVelocities.add(new Velocity(-2, -4));

        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.minOfBlocks = 40;

        // Create a background
        this.sprite = new Background3();
        this.blocks = new ArrayList<>();

        // Init the blocks array
        Color[] clr = {Color.white, Color.PINK, Color.BLUE, Color.YELLOW, Color.RED, Color.gray};
        int[] size = {30, 20};
        int k = 0;
        for (int i = 6; i >= 1; i--) {
            for (int j = 1; j <= ((i * 2) + k); j++) {
                this.blocks.add(new Block(new Rectangle(new Point((800 - size[0]) - (j * size[0]),
                        250 + size[1] * (6 - i)),  size[0], size[1]), clr[i - 1]));
            }
            k++;
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