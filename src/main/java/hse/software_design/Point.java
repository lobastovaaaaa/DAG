package hse.software_design;

/**
 * Class of instances determining physical points by describing their position in a 2D space based on mathematical
 * points relative to a local coordinate system
 */
public class Point implements Cloneable {
    /** mathematical point describing a Point's position in a local coordinate system */
    private Coord2D position;

    /**
     * Initialises a physical point with its position based on a mathematical point
     * @param inputCoord2D: Coord2D instance indicating a physical point position in a local coordinate system
     */
    Point(Coord2D inputCoord2D) { position = inputCoord2D; }

    /**
     * Presents physical point's current position coordinates as a mathematical point
     * @return Coord2D instance indicating this Point location in a local coordinate system
     */
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Presents physical point's current position coordinates as a double array
     * @return double static array of size 2; array[0] - double abscissa coordinate,
     * array[1] - double ordinate coordinate of a Point in a local coordinate system
     */
    public double[] getPositionArray() {
        return new double[] {position.getCoordinates()[0], position.getCoordinates()[1]};
    }

    /**
     * Changes physical point's current position by replacing its </position> Coord2D mathematical point
     * @param newValue: Coord2D instance indicating Point new location
     */
    public void setPosition(Coord2D newValue) {
        position = newValue;
    }

    /**
     * Defines a degenerate BoundBox instance of a single physical point
     * @see BoundBox
     * @return BoundBox instance with min and max coordinates equal to a current Point position in a local
     * coordinate system
     */
    public BoundBox getBounds() {
        return new BoundBox(this);
    }

    /**
     * Makes a deep copy of a current (this) Point instance
     * @return instance of a Point class with equal position in a local coordinate system
     */
    public Point clone() {
        return new Point(this.getPosition().clone());
    }

    /**
     * Compares 2 Point instances based on their coordinates
     * @param newPoint: instance of Point class to compare with current (this) Point
     * @return boolean value (true if coordinates of their mathematical points are equal; else false)
     */
    public boolean equalsByValues(Point newPoint) {
        return this.getPosition().equalsByValues(newPoint.getPosition());
    }
}
