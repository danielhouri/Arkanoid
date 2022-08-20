// 314712563

package sprites;
import biuoop.DrawSurface;
import collidables.Collidable;
import collidables.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
/**
 * @author Daniel Houri
 * This class represent ball object. Ball have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {

    // Variables
    private final int r;
    private final Color color;

    private Point center;
    private Velocity vel;
    private GameEnvironment gameEnvironment;

    /**
     * The main constructor of the ball object.
     * @param center the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * The main constructor of the ball object.
     * @param center the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param gme the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gme) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gme;
    }

    /**
     * A constructor of the ball object.
     * @param x coordinate of the center of the ball
     * @param y coordinate of the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * A constructor of the ball object.
     * @param center the center of the ball
     * @param r the radius of the ball
     */
    public Ball(Point center, int r) {
        this(center, r, Color.BLACK);
    }

    // Accessors
    /**
     * @return the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) (this.center.getX());
    }

    /**
     * @return the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) (this.center.getY());
    }

    /**
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the color of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * The methode set the velocity.
     * @param dx change in position on the `x` axes
     * @param dy change in position on the `y` axes
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * The methode set the velocity.
     * @param newVel the new velocity
     */
    public void setVelocity(Velocity newVel) {
        this.vel = newVel;
    }

    /**
     * The methode set the game environment.
     * @param newEnvironment the new environment
     */
    public void setEnvironment(GameEnvironment newEnvironment) {
        this.gameEnvironment = newEnvironment;
    }

    /**
     * The methode return the velocity.
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * The methode draw the ball on the given DrawSurface.
     * @param surface the DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * The methode keep the balls inside the screen.
     * When it hits the border to the left or to the right, it should change its horizontal direction,
     * and when it hits the border on the top or the bottom it should change its vertical direction.
     */
    public void moveOneStep() {
        // Compute the trajectory of the ball
        Point start = this.center;
        double x = start.getX() + this.getVelocity().getDx();
        double y = start.getY() + this.getVelocity().getDy();
        Point end = new Point(x, y);
        Line trajectory = new Line(start, end);

        CollisionInfo info = gameEnvironment.getClosestCollision(trajectory);
        if (info == null) {
            // No collision - then the ball is moving according to the velocity of the ball
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // Collision - moving the ball approximately to end and compute a new velocity
            Collidable c1 = info.collisionObject();
            this.center = this.adjustNewCenter(info.collisionPoint(), c1.getCollisionRectangle());
            Velocity newVelocity = c1.hit(this, info.collisionPoint(), this.getVelocity());
            this.setVelocity(newVelocity);
        }
    }

    /**
     * The methode get the collision point and return a point outside from the rectangle.
     * This new point will be the new center of the ball.
     * @param collisionPoint collision point
     * @param rcl the rectangle with the collision point
     * @return the new point
     */
    public Point adjustNewCenter(Point collisionPoint, Rectangle rcl) {
        // Get the edges of the rectangle
        Line[] lines = rcl.getEdges();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // If the ball hit the right/left then change the x coordinate
        if (lines[Rectangle.RIGHT].isPointInRange(collisionPoint)) {
            x--;
        } else if (lines[Rectangle.LEFT].isPointInRange(collisionPoint)) {
            x++;
        }

        // If the ball hit the upper/lower then change the y coordinate
        if ((lines[Rectangle.UPPER].isPointInRange(collisionPoint))) {
            y--;
        } else if ((lines[Rectangle.LOWER].isPointInRange(collisionPoint))) {
            y++;
        }

        return new Point(x, y);
    }

    /**
     * The function move one step the ball.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add the ball to the spirit list.
     * @param g the game reference
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball to the spirit list.
     * @param g the game reference
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}