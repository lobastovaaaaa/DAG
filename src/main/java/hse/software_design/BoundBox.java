package hse.software_design;

/**
 * Immutable class (value type) of instances that define a certain coordinate system by evaluating the minimum volume
 * of space accommodating all Origins and Points in terms of head Origin measurement system. An instance of class itself
 * is determined by left lower and right upper corners' coordinates of a BoundBox rectangle (described earlier)
 *    - BoundBox of a single Point (physical point) is a degenerate BoundBox, with min and max coordinates equal to a current
 *      Point position in a local coordinate system
 *    - BoundBox of a single Origin or an Origin without any Point neither in its children set nor in any children set of
 *      its children of any levels of inheritance
 */
public class BoundBox {
    /** left lower and right upper corners' coordinates of a BoundBox rectangle in current Origin / Point local
     * coordinate system as mathematical points (CoordD instances)*/
    private final Coord2D min;
    private final Coord2D max;
    /** left lower and right upper corners' coordinates of a BoundBox rectangle in current Origin / Point local
     * coordinate system as a double static array of arrays:
     * arrayOfBounds[0][0] - left lower corner abscissa coordinate
     * arrayOfBounds[0][1] - left lower corner ordinate coordinate
     * arrayOfBounds[1][0] - right upper corner abscissa coordinate
     * arrayOfBounds[1][1] - right upper corner ordinate coordinate
     */
    private final double[][] arrayOfBounds;

    /**
     * Initialises a BoundBox of an Origin instance given
     * @param root: Origin instance, which bounds are to be determined
     */
    BoundBox(Origin root){
        if (!root.ifContainsPoints()) {
            arrayOfBounds = null;
            min = null;
            max = null;
        } else {
            arrayOfBounds = localBounds(root, new double[] {root.getPosition().getCoordinates()[0],
                    root.getPosition().getCoordinates()[1]});
            min = new Coord2D(arrayOfBounds[0][0], arrayOfBounds[0][1]);
            max = new Coord2D(arrayOfBounds[1][0], arrayOfBounds[1][1]);
        }
    }

    /**
     * Initialises a BoundBox of a Point instance given
     * @param root: Point instance, which bounds are to be determined
     */
    public BoundBox(Point root){
        arrayOfBounds = new double[][] {{root.getPositionArray()[0], root.getPositionArray()[1]},
                {root.getPositionArray()[0], root.getPositionArray()[1]}};
        min = root.getPosition();
        max = root.getPosition();
    }

    /** left lower corner coordinates of a BoundBox rectangle in current Origin / Point local coordinate system as
     * mathematical point (CoordD instances)
     * @return min: Coord2D instance expressing the position of left lower corner coordinates of a BoundBox
     */
    public Coord2D getMin() { return min; }

    /** right upper corner coordinates of a BoundBox rectangle in current Origin / Point local coordinate system as
     * mathematical point (CoordD instances)
     * @return max: Coord2D instance expressing the position of right upper corner coordinates of a BoundBox
     */
    public Coord2D getMax() { return max; }

    /**
     * Defines left lower and right upper corners' coordinates of a BoundBox rectangle in current Origin / Point local
     * coordinate system as a double array
     * @return double static array of arrays (dimension 1 size = 2, dimension 2 size = 2):
     * arrayOfBounds[0][0] - left lower corner abscissa coordinate
     * arrayOfBounds[0][1] - left lower corner ordinate coordinate
     * arrayOfBounds[1][0] - right upper corner abscissa coordinate
     * arrayOfBounds[1][1] - right upper corner ordinate coordinate
     */
    public double[][] getBoundsArray() { return arrayOfBounds; }

    /**
     * Compares mathematical points of 2 BoundBox instances by their values
     * @param newBoundBox: instance of BoundBox class to compare with current (this) BoundBox
     * @return boolean value (true if both min and max Coord2D instances coordinates are equal to the corresponding
     * min and max points of a compared BoundBox; else false)
     */
    public boolean equalsByValues(BoundBox newBoundBox) {
        if (this.getBoundsArray() == null || newBoundBox.getBoundsArray() == null) return false;
        return this.min.equalsByValues(newBoundBox.min) && this.max.equalsByValues(newBoundBox.max);
    }

    /**
     * Service method used for indicating local BoundBox of a Point / Origin
     * @param localOrigin indicates a local Origin, which children set BoundBox will be defined
     * @param originBias indicates location bias of a current Origin relative to the head Origin of a coordinate system
     * @return BoundBox double static array of arrays (dimension 1 size = 2, dimension 2 size = 2):
     * arrayOfBounds[0][0] - left lower corner abscissa coordinate
     * arrayOfBounds[0][1] - left lower corner ordinate coordinate
     * arrayOfBounds[1][0] - right upper corner abscissa coordinate
     * arrayOfBounds[1][1] - right upper corner ordinate coordinate
     */
    private double[][] localBounds(Origin localOrigin, double[] originBias) {
        double[][] maxParams = new double[2][2];
        double[][] localParams = new double[2][2];
        maxParams[0][0] = Double.MAX_VALUE;
        maxParams[0][1] = Double.MAX_VALUE;
        maxParams[1][0] = - Double.MAX_VALUE;
        maxParams[1][1] = - Double.MAX_VALUE;
        boolean ifOrigin;
        for (Point elem : localOrigin.getChildrenSet()) {
            if (elem instanceof Origin) {
                double []localOriginBias = new double[] {elem.getPositionArray()[0] + originBias[0],
                        elem.getPositionArray()[1] + originBias[1]};
                localParams = localBounds((Origin)elem, localOriginBias);
                ifOrigin = true;
            } else {
                localParams[0][0] = elem.getPosition().getCoordinates()[0] + originBias[0];
                localParams[0][1] = elem.getPosition().getCoordinates()[1] + originBias[1];
                ifOrigin = false;
            }
            if (maxParams[0][0] > localParams[0][0]) maxParams[0][0] = localParams[0][0];
            if (maxParams[0][1] > localParams[0][1]) maxParams[0][1] = localParams[0][1];
            if (ifOrigin) {
                if (maxParams[1][0] < localParams[1][0]) maxParams[1][0] = localParams[1][0];
                if (maxParams[1][1] < localParams[1][1]) maxParams[1][1] = localParams[1][1];
            } else {
                if (maxParams[1][0] < localParams[0][0])
                    maxParams[1][0] = localParams[0][0];
                if (maxParams[1][1] < localParams[0][1])
                    maxParams[1][1] = localParams[0][1];
            }
        }
        return maxParams;
    }
}