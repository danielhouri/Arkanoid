// 314712563
package geometry;

/**
 * @author Daniel Houri
 * This class represent point has an x and a y value, and can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {

    //Variables
    private final double x;
    private final double y;

    /**
     * The constructor of the class Point.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The constructor get 2 integer and use the main constructor.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(int x, int y) {
        this ((double) x, y);
    }

    /**
     * The method return the distance of this point to the other point.
     * @param other the point for comparison.
     * @return the distance between this point to the other.
     */
    public double distance(Point other) {
        double disX = Math.pow((this.x - other.getX()), 2);
        double disY = Math.pow((this.y - other.getY()), 2);

        return Math.sqrt(disX + disY);
    }

    /**
     * The method return true is the points are equal, false otherwise.
     * @param other the point for comparison.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return ((other != null) && (Utils.round(this.x, other.getX()) && (Utils.round(this.y, other.getY()))));
    }

    /**
     * The method return the x coordinate.
     * @return x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * The method return the y coordinate.
     * @return y coordinate.
     */
    public double getY() {
        return this.y;
    }
}