//314712563
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the rectangle form.
 */
public class Rectangle {

    private Point upperLeft;
    private final double width;
    private final double height;

    // The array contain 4 lines that describe the rectangle in a Cartesian coordinate system
    private final Line[] edges = new Line[4];
    public static final int UPPER = 0;
    public static final int LOWER = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    /**
     * The constructor of the rectangle class.
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        this.updateEdges();
    }

    /**
     * This methode update the line equation of the rectangle according the upper left point of the rectangle.
     */
    public void updateEdges() {
        // Create 4 lines that describe the rectangle
        double xDifference = this.upperLeft.getX() + this.width;
        double yDifference = this.upperLeft.getY() + this.height;

        this.edges[0] = new Line(this.upperLeft, new Point(xDifference, this.upperLeft.getY()));
        this.edges[1] = new Line(this.upperLeft.getX(), yDifference, xDifference, yDifference);
        this.edges[2] = new Line(this.edges[1].start(), this.edges[0].start());
        this.edges[3] = new Line(this.edges[1].end(), this.edges[0].end());
    }

    /**
     * The methode return the intersection points of the line with the edges of the rectangle.
     * @param line the comparison line
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Create a new list that contain the intersection point
        List<Point> lsPoint = new ArrayList<>();

        // Add to the list the points that intersect with the edges of the rectangle
        for (Line ln : edges) {
            Point intersection = ln.intersectionWith(line);
            if (intersection != null) {
                lsPoint.add(intersection);
            }
        }
        return lsPoint;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the edges of the rectangle
     */
    public Line[] getEdges() {
        return this.edges;
    }

    /**
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set the upper left point of the rectangle.
     * @param p1 the new point of the rectangle
     */
    public void setUpperLeft(Point p1) {
        this.upperLeft = p1;
        this.updateEdges();
    }
}