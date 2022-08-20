// 314712563

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.screens.CountdownAnimation;

/**
 * This class contain the game loop logic.
 */
public class AnimationRunner {
    // Private fields
    private final GUI gui;
    private final Sleeper sleeper = new Sleeper();

    private final int framesPerSecond;
    /**
     * The constructor of the class 'AnimationRunner'.
     * @param gui the gui
     * @param framesPerSecond the framesPerSecond changes
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * The AnimationRunner takes an Animation object and runs it.
     * @param animation the game
     */
    public void run(Animation animation) {
        int millisecondsPerFrame;
        // Set the millisecondsPerFrame
        if (animation.getClass() == CountdownAnimation.class) {
            millisecondsPerFrame = 1000 / (int) ((CountdownAnimation) animation).getNumOfSeconds();
        } else {
            millisecondsPerFrame = 1000 / this.framesPerSecond;
        }

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}