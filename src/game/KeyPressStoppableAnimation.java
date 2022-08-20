package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This decorator-class 'KeyPressStoppableAnimation' wrap an existing animation and add a "waiting-for-key" behavior
 * to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    // Private Fields
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The constructor of the class 'KeyPressStoppableAnimation'.
     * @param sensor the keyboard sensor
     * @param key the string to print
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * This methode is in charge of the logic of one frame.
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed && this.sensor.isPressed(this.key)) {
            this.stop = true;
        }
    }

    /**
     * Stop the loop.
     * @return false (stop the game)
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}