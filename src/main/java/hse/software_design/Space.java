package hse.software_design;

/**
 * Class instances defines a global coordinate system. The system itself is determined by an Origin, which indicates an
 * origin point of the system (considering the bias of this Origin from (0; 0) point).
 */
public class Space implements Cloneable {
    private Origin root;

    /**
     * Initialises a global coordinate system with a default root Origin in (0; 0)
     */
    public Space() { root = new Origin(new Coord2D(0, 0)); }

    /**
     * Initialises a global coordinate system with an Origin instance provided
     * @param newRoot: Origin instance defining new root of current Space instance
     */
    public Space(Origin newRoot) { root = newRoot; }

    /**
     * Return the root Origin of the global coordinate system
     * @return Oorigin instance - root of current Space instance
     */
    public Origin getRoot() { return root; }

    /**
     * Sets a certain Origin instance as the root of global coordinate system
     * @param newRoot: Origin instance defining new root of current Space instance
     */
    public void setAsRoot(Origin newRoot) { root = newRoot; }

    /**
     * Defines a BoundBox instance with min and max coordinates equal to left lower and right upper corners' coordinates
     * of a rectangle,that limits minimum volume of space accommodating all Points in global coordinate system
     * @see BoundBox
     * @return BoundBox instance of root Origin
     */
    public BoundBox getBounds() { return root.getBounds(); }

    /**
     * Makes a deep copy of current (this) Space instance (with copies of Origin root and its children of all levels of
     * inheritance in the same hierarchical order)
     * @return instance of Space class structurally identical to current Space instance
     */
    public Space clone() {
        return new Space(root.clone());
    }
}
