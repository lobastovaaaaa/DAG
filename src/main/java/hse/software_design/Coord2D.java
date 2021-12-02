package hse.software_design;

/**
 * Immutable class (value type), whose instances determine mathematical points of 2D space by specifying their
 * constant coordinates and may be used to determine the movement of physical points by describing their positions
 */
public final class Coord2D implements Cloneable {
    /** coordinates of a mathematical point along the abscissa and ordinate axes */
    private final double x;
    private final double y;

    /**
     * Initialises the values of the abscissa and ordinate coordinate properties
     * @param inputX: double abscissa coordinate of a mathematical point in relative coordinate system
     * @param inputY: double ordinate coordinate of a mathematical point in relative coordinate system
     */
    public Coord2D(double inputX, double inputY) {
        this.x = inputX;
        this.y = inputY;
    }

    /**
     * Presents information about coordinates of a mathematical point in relative coordinate system as a double array
     * @return double static array of size 2;
     * array[0] - double abscissa coordinate, array[1] - double ordinate coordinate
     */
    public double[] getCoordinates() {
        return new double[] {this.x, this.y};
    }

    /**
     * Makes a deep copy of a current (this) Coord2D instance
     * @return instance of a Coord2D class with equal coordinates
     */
    public Coord2D clone() {
        return new Coord2D(x, y);
    }

    /**
     * Compares 2 Coord2D instances based on their coordinates
     * @param newCoord2D: instance of Coord2D class to compare with current (this) Coord2D
     * @return boolean value (true if both coordinates are equal; else false)
     */
    public boolean equalsByValues(Coord2D newCoord2D) {
        return this.getCoordinates()[0] == newCoord2D.getCoordinates()[0] &&
                this.getCoordinates()[1] == newCoord2D.getCoordinates()[1];
    }
}