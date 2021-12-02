package hse.software_design;

import java.util.HashSet;

/**
 * Class of instances defining origin point of a local coordinate system based on a mathematical point, indicating the
 * offset of this origin relative to some other coordinate system. In addition to its own position, the coordinate
 * system has a set of coordinate systems and / or physical points, positions of which are measured relative to the
 * current origin coordinate system
 */
public class Origin extends Point implements Cloneable{
    /**
     * set of Point (and Origin as Point heir) instances, positions of which are measured relative to the
     * current Origin coordinate system
     */
    private HashSet<Point> children;

    /**
     * Initialises an Origin instance with its position based on a mathematical point and an empty set of children
     * @param inputCoord2D: Coord2D instance indicating an Origin position in a local coordinate system
     */
    Origin(Coord2D inputCoord2D) {
        super(inputCoord2D);
        children = new HashSet<>();
    }

    /**
     * Presents this Origin current position coordinates as a mathematical point
     * @return Coord2D instance indicating this Origin location in a local coordinate system
     */
    public Coord2D getPosition() { return super.getPosition(); }

    /**
     * Presents this Origin current position coordinates as a double array
     * @return double static array of size 2; array[0] - double abscissa coordinate,
     * array[1] - double ordinate coordinate of an Origin in a local coordinate system
     */
    public double[] getPositionArray() { return super.getPositionArray(); }

    /**
     * Changes current Origin position by replacing its </position> Coord2D mathematical point
     * @param newValue: Coord2D instance indicating Origin's new location
     */
    public void setPosition(Coord2D newValue) { super.setPosition(newValue); }

    /**
     * Returns a set of copies of Point (and Origin as Point heir) instances, positions of which are measured
     * relative to the current Origin coordinate system
     * @return a set Point (and Origin as Point heir) copied instances (with hierarchies of previous levels retained)
     */
    public HashSet<Point> getChildrenSet () {
        HashSet<Point> copy = new HashSet<>();
        for (Point elem : children) {
            copy.add(elem.clone());
        }
        return copy;
    }

    /**
     * Defines a new set of copies of Point (and Origin as Point heir) instances, positions of which are measured
     * relative to the current Origin coordinate system; this set replaces the previous one
     * @param newChildrenSet: set of Point (and Origin as Point heir) instances, positions of which are to be measured
     * relative to the current Origin coordinate system
     * @exception DAGConstraintException: in case if setting new set as a children set will lead to appearance of
     * cycle(s) in global coordinate system
     */
    public void setChildren(HashSet<Point> newChildrenSet) throws DAGConstraintException {
        if (newChildrenSet.size() != 0) {
            for (Point p : newChildrenSet) {
                if (p instanceof Origin) {
                    if (((Origin)p).ifContains(this)) {
                        throw new DAGConstraintException();
                    }
                }
            }
        }
        children = new HashSet<>();
        children.addAll(newChildrenSet);
    }

    /**
     * Adds a Point (or Origin as Point heir) instance, so that its positions will be measured relative to the current
     * Origin coordinate system; this set replaces the previous one
     * @param newChild: a Point (or Origin as Point heir) instance, positions of which is to be measured
     * relative to the current Origin coordinate system
     * @exception DAGConstraintException: in case if adding new Point to a children set will lead to appearance of
     * cycle(s) in global coordinate system
     */
    public void addToChildren(Point newChild) throws DAGConstraintException{
        if (newChild instanceof Origin) {
            if (((Origin) newChild).ifContains(this))
                throw new DAGConstraintException();
        }
        children.add(newChild);
    }

    /**
     * Defines a BoundBox instance with min and max coordinates equal to left lower and right upper corners' coordinates
     * of a rectangle,that limits minimum volume of space accommodating all subsidiary Points of all levels of inheritance
     * (including subsidiary Points of Origins - subsidiaries of current Origin, their sub-Origins' Points and so on)
     * @see BoundBox
     * @return BoundBox instance of current Origin (coordinates given in the measurement system of current Origin)
     */
    public BoundBox getBounds() {
        return new BoundBox(this);
    }

    /**
     * Makes a deep copy of a current (this) Origin instance (with children set of this Origin children retaining
     * hierarchies of previous levels
     * @return instance of an Origin class with equal position in a local coordinate system
     */
    public Origin clone() {
        Origin copy = new Origin(this.getPosition().clone());
        try {
            copy.setChildren(this.getChildrenSet());
        } catch (DAGConstraintException e) {  // loading set of copies as new origin's children set  -> cycle impossible
        }
        return copy;
    }

    /**
     * Compares 2 Origins instances based on their coordinates
     * @param newOrigin: instance of Origin class to compare with current (this) origin
     * @return boolean value (true if coordinates of their mathematical points are equal; else false)
     */
    public boolean equalsByValues(Origin newOrigin) {
        return super.equalsByValues(newOrigin);
    }

    /**
     * Service method for checking if current Origin or its children of any level of inheritance contain certain Origin instance
     * @param toFind: Origin instance to look for
     * @return boolean value: true if instance found, else false
     */
    private boolean ifContains(Origin toFind) {
        if (this == toFind) return true;
        if (this.children.size() != 0) {
            for (Point elem : this.children) {
                if (elem instanceof Origin) {
                    if ((elem) == toFind) return true;
                    if (((Origin) elem).ifContains(toFind)) return true;
                }
            }
        }
        return false;
    }

    /**
     * Service method for checking if current Origin or its children of any level of inheritance contain certain any
     * Point instances
     * @return boolean value: true if any Point instance found, else false
     */
    protected boolean ifContainsPoints() {
        if (this.children.size() != 0) {
            for (Point elem : this.children) {
                if (elem instanceof Origin) {
                    if (((Origin) elem).ifContainsPoints()) return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
