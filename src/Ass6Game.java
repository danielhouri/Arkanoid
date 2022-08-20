// 314712563

import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import game.levels.DirectHit;
import game.levels.FinalFour;
import game.levels.GreenThree;
import game.levels.LevelInformation;
import game.levels.WideEasy;
import geometry.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Houri
 * The main class that launch the game.
 */
public class Ass6Game {

    /**
     * The main methode that initialize and launch the game.
     * @param args args from the user
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Utils.WIDTH, Utils.HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);

        // Get the arg from the user and fill the level list
        List<LevelInformation> levels = new ArrayList<>();
        List<Integer> list = Utils.checkArgs(args);
        for (int i:list) {
            if (i == 1) {
                levels.add(new DirectHit());
            } else if (i == 2) {
                levels.add(new WideEasy());
            } else if (i == 3) {
                levels.add(new GreenThree());
            } else if (i == 4) {
                levels.add(new FinalFour());
            }
        }
        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new GreenThree());
            levels.add(new FinalFour());
        }

        GameFlow game = new GameFlow(gui, animationRunner, gui.getKeyboardSensor());
        game.runLevels(levels);
    }
}