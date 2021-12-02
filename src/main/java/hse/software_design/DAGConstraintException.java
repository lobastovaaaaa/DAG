package hse.software_design;

/** Class of a custom exception used to indicate a detection of a cycle in a Direct Acyclic Graph */
public class DAGConstraintException extends Exception {
    /** Initialises the warning called in case of exception */
    public DAGConstraintException() {
        super("\nDAGConstraintException: detected cycle in Directed Acyclic Graph!");
    }
}