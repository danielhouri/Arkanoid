// 314712563

package geometry;
import java.util.List;

/**
 * @author Daniel Houri
 * This class represent line object. Line have 2 points, a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {

    //Variables
    private final Point start;
    private final Point end;
    private final double mGradient;
    private final double bInterception;
    private boolean xParallel = false; // line parallel to the y-axis
    private boolean isPoint = false;

    // Constructors
    /**
     * The main constructor of the Line object.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.mGradient = calcGradient();
        this.bInterception = calcB();
        if (this.start.equals(this.end)) {
            isPoint = true;
        }
    }

    /**
     * The second constructor receive 4 point and send them to the main constructor.
     * @param x1 Param x of the start point
     * @param y1 Param y of the start point
     * @param x2 Param x of the end point
     * @param y2 Param y of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * This methode return the length of the line.
     * @return the length
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The method calculates and return the middle of the line.
     * The equation of the distance is xm = (x1 + x2)/2, ym = (y1 + y2)/2.
     * @return a point that represent the middle of the line
     */
    public Point middle() {
        double midX = (this.end.getX() + this.start.getX()) / 2; //xm = (x1 + x2)/2
        double midY = (this.end.getY() + this.start.getY()) / 2; //ym = (y1 + y2)/2

        return new Point(midX, midY);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * The methode calculates the gradient of the line by using the equation:
     * m = (y1 - y2) / (x1 - x2).
     * @return the gradient of the line
     */
    private double calcGradient() {
        if ((this.end.getX() - this.start.getX()) != 0) {
            return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        } else {
            this.xParallel = true;
            return 0;
        }
    }

    /**
     * The methode calculates the value of y when x=0 by using the equation:
     * b = y - mx.
     * @return b var
     */
    private double calcB() {
        if (!this.getParallel()) {
            return (this.end().getY() - (this.calcGradient() * this.end().getX()));
        }
        return 0;
    }

    /**
     * @return true if its parallel, else false.
     */
    public boolean getParallel() {
        return this.xParallel;
    }

    /**
     * @return true if its parallel, else false.
     */
    public boolean getIsPoint() {
        return this.isPoint;
    }

    /**
     * @return the gradient of the line
     */
    public double getGradient() {
        return this.mGradient;
    }

    /**
     * @return the b value of the line.
     */
    public double getB() {
        return this.bInterception;
    }

    /**
     * The methode check if the intersection point is in the range of the line.
     * @param p1 the suspicious intersection point
     * @return true - is in the range, false - isn't in the range
     */
    public boolean isPointInRange(Point p1) {
        // If xP1 is in the range of the line
        if ((Math.min(this.start.getX(), this.end.getX()) <= p1.getX())
                && (p1.getX() <= Math.max(this.start.getX(), this.end.getX()))) {
            //If yP1 is in the range of the line
            return (Math.min(this.start.getY(), this.end.getY()) <= p1.getY())
                    && (p1.getY() <= Math.max(this.start.getY(), this.end.getY()));
        }
        return false;
    }

    /**
     * The method check if the lines has more than one point in common.
     * @param other the second line
     * @return true - there is more than one point in common, else false
     */
    public boolean isLineContainX(Line other) {
        // Get the min and max of the coordinate y of the start and point
        double max1 = Math.max(this.start.getX(), this.end.getX());
        double min1 = Math.min(this.start.getX(), this.end.getX());
        double max2 = Math.max(other.start.getX(), other.end.getX());
        double min2 = Math.min(other.start.getX(), other.end.getX());


        // If one of the lines is a point then check if it is in the range.
        // Else, it check if there is more than one points in common.
        if (this.getIsPoint() || other.getIsPoint()) {
            return  (this.isPointInRange(other.start()) || other.isPointInRange(this.start()));
        } else if (((min2 < min1 + this.length()) && (min1 + this.length() < max2))
                || ((min1 < min2 + other.length()) && (min2 + this.length() < max1))) {
            return false;
        }

        // If the endpoint is equal to a starting point of the second line
        return ((max1 == min2) || (min1 == max2));
    }

    /**
     * The method check if the lines has more than one point in common.
     * @param other the second line
     * @return true - there is more than one point in common, else false
     */
    public boolean isLineContainY(Line other) {
        // Get the min and max of the coordinate y of the start and point
        double max1 = Math.max(this.start.getY(), this.end.getY());
        double min1 = Math.min(this.start.getY(), this.end.getY());
        double max2 = Math.max(other.start.getY(), other.end.getY());
        double min2 = Math.min(other.start.getY(), other.end.getY());


        // If one of the lines is a point then check if it is in the range.
        // Else, it check if there is more than one points in common.
        if (this.getIsPoint() || other.getIsPoint()) {
            return  (this.isPointInRange(other.start()) || other.isPointInRange(this.start()));
        } else if (((min2 < min1 + this.length()) && (min1 + this.length() < max2))
                || ((min1 < min2 + other.length()) && (min2 + this.length() < max1))) {
            return false;
        }

        // If the endpoint is equal to a starting point of the second line
        return ((max1 == min2) || (min1 == max2));
    }

    /**
     * The methode check if the lines intersect.
     * @param other the line to compare
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return false;
        }
        // If the lines is not parallel to the y axis
        if (!(this.getParallel() || other.getParallel())) {
            // If the lines are parallel
            if (this.getGradient() == other.getGradient()) {
                return this.isLineContainX(other);
            }

            // Calculates the intersection point
            double xIntersection = (other.getB() - this.getB()) / (this.getGradient() - other.getGradient());
            double yOther = other.getGradient() * xIntersection + other.getB();
            double yThis = this.getGradient() * xIntersection + this.getB();

            // If the variables are approximately equal
            if (Utils.round(yThis, yOther)) {
                // If the variables is in the range
                return (this.isPointInRange(new Point(xIntersection, yThis))
                        && other.isPointInRange(new Point(xIntersection, yOther)));
            }
        }

        // If just one of the lines is parallel to the y axis
        if (!other.getParallel()) {
            double yIntersection = (other.getGradient() * this.start().getX()) + other.getB();

            // This section check if line is a point and check if this point is intersect with the second line
            if (this.getIsPoint()) {
                return (other.isPointInRange(new Point(this.start().getX(), yIntersection))
                        && Utils.round(yIntersection, this.start().getY()));
            }

            // This section check if the line is not a point, then check if the yIntersection is in the range
            return ((this.isPointInRange(new Point(this.start().getX(), yIntersection)))
                    && other.isPointInRange(new Point(this.start().getX(), yIntersection)));
        } else if (!this.getParallel()) {
            double yIntersection = (this.getGradient() * other.start().getX()) + this.getB();

            // This section check if line is a point and check if this point is intersect with the second line
            if (other.getIsPoint()) {
                return ((this.isPointInRange(new Point(other.start().getX(), yIntersection)))
                        && Utils.round(yIntersection, other.start().getY()));
            }

            if (other.isPointInRange(new Point(other.start().getX(), yIntersection))) {
                return true;
            }
            // This section check if the line is not a point, then check if the yIntersection is in the range
            return (Utils.round(yIntersection, other.start().getY())
                    && this.isPointInRange(new Point(other.start().getX(), yIntersection)));
        }

        // If the lines both is parallel to the y axis
        if (this.start().getX() == other.start().getX()) {
            return (this.isLineContainY(other));
        }

        return false;
    }

    /**
     * The methode check if the lines intersect and return the intersection point.
     * The equation for calculating parameter y is: y = mx + b.
     * @param other the line to compare
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // If the lines is equals
        if (this.equals(other)) {
            return null;
        } else if (isIntersecting(other)) {
            // If the the both lines is parallel
            if (this.getParallel() && other.getParallel()) {
                double num1 = Math.max(this.start().getY(), other.start().getY());
                double num2 = Math.max(this.end().getY(), other.end().getY());
                double range = Math.min(num1, num2);
                return new Point(this.start().getX(), range);
            } else if (this.getParallel()) {
                // If this line is parallel to the y axis.
                double yThis = (other.getGradient() * this.start().getX()) + other.getB();
                return new Point(this.start().getX(), yThis);
            } else if (other.getParallel()) {
                // If the other line is parallel to the y axis.
                double yThis = (this.getGradient() * other.start().getX()) + this.getB();
                return new Point(other.start().getX(), yThis);
            } else {
                // If both lines is not parallel to y axis
                // Both lines is parallel to x axis
                if ((this.getGradient() == 0) && (other.getGradient() == 0)) {
                    double maxThis = Math.max(this.start().getX(), this.end().getX());
                    double minOther = Math.max(other.start().getX(), other.end().getX());
                    if (minOther <= maxThis) {
                        return new Point(minOther, this.start.getY());
                    } else {
                        return new Point(maxThis, this.start.getY());
                    }
                }

                // Both lines is not parallel to x and y axis
                double xIntersection = (other.getB() - this.getB()) / (this.getGradient() - other.getGradient());
                double yIntersection = this.getGradient() * xIntersection + this.getB();

                return new Point(xIntersection, yIntersection);
            }
        }

        return null;
    }

    /**
     * The methode check if the lines are equal.
     * @param other the line to compare
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.start().equals(other.end()) && this.end().equals(other.start())));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle
     * @return null - not intersect with the rectangle, else the closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);

        // minDis contain the min distance with the start point
        double minDis = -1;
        Point minPoint = null;

        // Compare the distance of the points with the start of the line
        for (Point i : points) {
            double distance = this.start.distance(i);
            if ((minDis > distance) || (minDis == -1)) {
                minDis = distance;
                minPoint = i;
            }
        }

        return minPoint;
    }
}