// 314712563

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;
import game.levels.LevelInformation;
import game.screens.CountdownAnimation;
import game.screens.PauseScreen;
import geometry.Point;
import geometry.Rectangle;
import geometry.Utils;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * @author Daniel Houri
 * This class hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {

    // Private fields
    private final Counter remainingBlocks = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter currentScore;

    // Final fields
    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    private final LevelInformation levelInformation;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;

    /**
     * The constructor of the class 'Game'.
     * @param levelInfo array of level information
     * @param keyboardSensor the keyboard sensor
     * @param animationRunner the animation runner
     * @param currentScore the current score of the user
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter currentScore) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        this.levelInformation = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.currentScore = currentScore;
    }

    /**
     * Add collidable to the list in the game environment.
     * @param c the new collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite to the list in the spirit.
     * @param s the new spirit
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove collidable to the list in the game environment.
     * @param c the new collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite to the list in the spirit.
     * @param s the new spirit
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * This methode generate the block that represent the frame.
     */
    public void createBorder() {
        Block[] b = new Block[3];
        b[0] = new Block(new Rectangle(new Point(0, 15),  Utils.WIDTH, Utils.BORDER));
        b[1] = new Block(new Rectangle(new Point(0, 0),  Utils.BORDER, Utils.HEIGHT));
        b[2] = new Block(new Rectangle(new Point(Utils.WIDTH - Utils.BORDER, 0),  Utils.BORDER, Utils.HEIGHT));

        // Create 3 sides to the game
        for (int i = 0; i < 3; i++) {
            b[i].addToGame(this);
        }
    }

    /**
     * This methode generate blocks that represent the death-region area.
     * @param listener listener
     */
    public void createDeathRegion(HitListener listener) {
        Block b = new Block(new Rectangle(new Point(0, Utils.HEIGHT - Utils.BORDER), Utils.WIDTH, Utils.BORDER));
        b.addToGame(this);
        b.setColor(Color.green);
        b.addHitListener(listener);
    }

    /**
     * This methode generate few collidable object.
     * @param listener listener
     * @param listener1 listener1
     */
    public void generateBlockStructure(HitListener listener, HitListener listener1) {
        for (Block bl : this.levelInformation.blocks()) {
            bl.addToGame(this);
            this.remainingBlocks.increase(1);
            bl.addHitListener(listener);
            bl.addHitListener(listener1);
        }
    }

    /**
     * This methode generate the balls according to the level info.
     */
    public void createBallsOnTopOfPaddle() {
        Ball ball;
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            ball = new Ball(new Point(Utils.WIDTH / 2, 500), 5, Color.BLUE);
            ball.addToGame(this);
            ball.setEnvironment(this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            this.remainingBalls.increase(1);
        }
    }

    /**
     * This methode generate all game object's.
     */
    public void createGameObjects() {
        // Create the listeners: BallRemover, BlockRemover and ScoreTracking
        HitListener listener = new BallRemover(this, this.remainingBalls);
        HitListener listener1 = new BlockRemover(this, this.remainingBlocks);
        HitListener listener2 = new ScoreTrackingListener(this.currentScore);

        // Create the death region
        this.createDeathRegion(listener);

        // Create background font to the gui
        this.addSprite(this.levelInformation.getBackground());

        // Create the border of the game and the block arranged in a pattern
        this.createBorder();
        this.generateBlockStructure(listener1, listener2);

        // Create the score bar
        this.sprites.addSprite(new ScoreIndicator(this.currentScore, this.levelInformation.levelName()));

        // Create 2 balls in random place
        this.createBallsOnTopOfPaddle();
    }

    /**
     * This function initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        this.createGameObjects();

        // Create a new paddle and add it to the game
        Paddle user = new Paddle(keyboard, this.levelInformation.paddleWidth(), this.levelInformation.paddleSpeed());
        user.addToGame(this);
    }

    /**
     * This methode is in charge of the logic of one frame.
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        // End the game if there is no more balls or blocks or numberOfBlocksToRemove <= blocks.size()
        if ((this.remainingBlocks.getValue() <= 0) || (this.remainingBalls.getValue() <= 0)
                || ((levelInformation.blocks().size() - this.remainingBlocks.getValue())
                >= levelInformation.numberOfBlocksToRemove())) {
            // Add 100 points to the score when all the blocks are removed
            if (this.remainingBlocks.getValue() <= 0) {
                this.currentScore.increase(100);
            }

            this.running = false;
        }
    }

    /**
     * @return true - the game is running, else return false
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This function run the game - start the animation loop.
     */
    public void run() {
        this.running = true;
        // CountDown animation
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        // Start the game
        this.runner.run(this);
    }

    /**
     * @return the number of remaining balls
     */
    public int getRemainingBalls() {
        return remainingBalls.getValue();
    }

    /**
     * @return the number of remaining blocks
     */
    public int getRemainingBlocks() {
        return remainingBlocks.getValue();
    }
}