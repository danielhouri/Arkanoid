package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.levels.LevelInformation;
import game.screens.EndScreen;
import java.util.List;

/**
 * This class 'GameFlow' support for moving from one level to the next.
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {

    // Private Fields
    private final GUI gui;
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter currentScore;
    private boolean win;

    /**
     * The constructor of the class 'GameFlow'.
     * @param gui gui
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.gui = gui;
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.currentScore = new Counter();
        this.win = true;
    }

    /**
     * This methode support for moving from one level to the next.
     * @param levels the different levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // Init and start the game
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.currentScore);

            level.initialize();
            level.run();

            // If there is no more balls
            if (level.getRemainingBalls() == 0) {
                this.win = false;
                break;
            }
        }

        // Announce the result of the game
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.currentScore.getValue(), this.win)));

        // Close the gui
        this.gui.close();
    }
}