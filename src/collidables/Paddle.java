// 31471263
package collidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Utils;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;

/**
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys, and moves according
 * to the player key presses. It implement the Sprite and the Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {

    // Private fields
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle rcl;
    private Color color;
    private final int width;
    private final int paddleSpeed;

    // Final fields
    static final int SLICE_NUM = 5;

    /**
     * The main constructor of the Paddle object.
     * @param keyboard the keyboard sensor
     * @param paddleWidth paddle width
     * @param paddleSpeed paddle speed
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int paddleWidth, int paddleSpeed) {
        this.width = paddleWidth;
        int height = 20;

        this.rcl = new Rectangle(new Point((Utils.WIDTH - this.width) / 2, Utils.HEIGHT - height * 2),
                paddleWidth, height);
        this.color = Color.ORANGE;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * This methode move left the paddle.
     */
    public void moveLeft() {
        if ((this.rcl.getUpperLeft().getX() - paddleSpeed) > Utils.BORDER) {
            Point p1 = new Point((this.rcl.getUpperLeft().getX() - paddleSpeed), this.rcl.getUpperLeft().getY());
            this.rcl.setUpperLeft(p1);
        }
    }

    /**
     * This methode move right the paddle.
     */
    public void moveRight() {
        // Check if the paddle movement is in the frame
        if ((this.rcl.getUpperLeft().getX() + width + paddleSpeed) < (Utils.WIDTH - Utils.BORDER)) {
            Point p1 = new Point((this.rcl.getUpperLeft().getX() + paddleSpeed), this.rcl.getUpperLeft().getY());
            this.rcl.setUpperLeft(p1);
        }
    }

    /**
     * This methode check if the user press the left/right key.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * The methode draw the ball on the given DrawSurface.
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.rcl.getUpperLeft().getX();
        int y = (int) this.rcl.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.rcl.getWidth(), (int) this.rcl.getHeight());
    }

    /**
     * Return the "collision shape" of the object.
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rcl;
    }

    /**
     * The methode return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter the hitter ball
     * @param collisionPoint the collision point
     * @param currentVelocity the force the object
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] lines = rcl.getEdges();

        // This section change the velocity according to the hit side of the rectangle.
        // For example: if the collision point is with the left side, then change dy to -dy.
        if ((lines[Rectangle.UPPER].isPointInRange(collisionPoint)
                || lines[Rectangle.LOWER].isPointInRange(collisionPoint))
                && (lines[Rectangle.RIGHT].isPointInRange(collisionPoint)
                || lines[Rectangle.LEFT].isPointInRange(collisionPoint))) {
            // If the collision occurs near 2 edges
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (lines[Rectangle.UPPER].isPointInRange(collisionPoint)) {
            // Slice paddle to 5 equally-spaced regions
            Line[] slice = slicePaddle();

            // Get the speed vector from the velocity
            double speed = currentVelocity.getSpeed();

            // Check which part the ball hit
            if (slice[0].isPointInRange(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(300, speed);
            } else if (slice[1].isPointInRange(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(330, speed);
            } else if (slice[3].isPointInRange(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else if (slice[4].isPointInRange(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(60, speed);
            }

            // If the ball hit the middle
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (lines[Rectangle.LOWER].isPointInRange(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (lines[Rectangle.RIGHT].isPointInRange(collisionPoint)
                || lines[Rectangle.LEFT].isPointInRange(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        return currentVelocity;
    }

    /**
     * This methode slice the paddle to 5 parts.
     * @return an array of 5 parts
     */
    private Line[] slicePaddle() {
        Line upper = this.rcl.getEdges()[Rectangle.UPPER];
        Point start = upper.start();
        Point end = upper.end();
        double diff = (end.getX() - start.getX()) / (SLICE_NUM - 1);
        Line[] slice = new Line[SLICE_NUM];

        for (int i = 0; i < SLICE_NUM; i++) {
            Point p1 = new Point((diff * i) + start.getX(), start.getY());
            Point p2 = new Point((diff * (i + 1)) + start.getX(), start.getY());
            slice[i] = new Line(p1, p2);
        }

        return slice;
    }

    /**
     * The methode set the color.
     * @param clr the new color
     */
    void setColor(Color clr) {
        this.color = clr;
    }

    /**
     * Add this paddle to the game.
     * @param g the game var
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @return the paddle width.
     */
    public int getWidth() {
        return width;
    }
}