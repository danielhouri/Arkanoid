// 314712563

package sprites;
import geometry.Point;

/**
 * @author Daniel Houri
 * This class represent velocity object.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    //Variables
    private final double dx;
    private final double dy;

    // constructor
    /**
     * The constructor of the class Velocity.
     * @param dx change in position on the `x` axes
     * @param dy change in position on the `y` axes
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The constructor convert angle and speed to change in position on the `x` and the `y` axes.
     * @param angle angle (in Degrees)
     * @param speed speed
     * @return Velocity object in term of dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * The methode return the dx coordinate.
     * @return the dx coordinate
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The methode return the dy coordinate.
     * @return the dy coordinate
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * @param p the previous point
     * @return the new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(dx + p.getX(), dy + p.getY());
    }

    /**
     * The methode check if the velocity is equal.
     * @param other the Velocity to compare
     * @return return true is the Velocity are equal, false otherwise
     */
    public boolean equals(Velocity other) {
        return ((this.getDx() == other.getDx()) && (this.getDy() == other.getDy()));
    }

    /**
     * The methode return the speed using the Pythagorean theorem.
     * @return the speed according to dx and dy
     */
    public double getSpeed() {
        double powA = Math.pow(this.dx, 2);
        double powB = Math.pow(this.dy, 2);
        return (Math.sqrt(powA + powB));
    }
}