package hse.software_design;

import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class OriginTest extends PointTest {

    private Origin testOrigin;
    private Coord2D testCoord2D;

    @BeforeAll
    static void prepare() {
        System.out.println("Testing class Origin started");
    }

    @AfterAll
    static void end() {
        System.out.println("Testing class Origin ended");
    }

    @BeforeEach
    void setUp() {
        System.out.println("FuncTest started");
    }

    @AfterEach
    void tearDown() {
        testOrigin = null;
        System.out.println("FuncTest ended");
    }

    @Test
    void constructWithParams() {
        testCoord2D = new Coord2D(9, 10);
        testOrigin = new Origin(testCoord2D);
        assertTrue(testOrigin.getPositionArray()[0] == 9 && testOrigin.getPositionArray()[1] == 10);
    }

    @Test
    void getPosition() {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        assertEquals(testOrigin.getPosition(), testCoord2D);
    }

    @Test
    void getPositionByValue() {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        assertTrue(testOrigin.getPosition().getCoordinates()[0] == 2 &&
                testOrigin.getPosition().getCoordinates()[1] == 5);
    }

    @Test
    void getPositionArray() {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        assertTrue(testOrigin.getPositionArray()[0] == 2 && testOrigin.getPositionArray()[1] == 5);
    }

    @Test
    void setPosition() {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin( new Coord2D(0, 0));
        testOrigin.setPosition(testCoord2D);
        assertEquals(testOrigin.getPosition(), testCoord2D);
    }

    @Test
    void getChildrenValues() throws DAGConstraintException {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        Coord2D coord1 = new Coord2D(40, -2);
        Point point1 = new Point(coord1);
        testOrigin.addToChildren(point1);
        HashSet<Point> children = testOrigin.getChildrenSet();
        Point child = children.iterator().next();
        assertTrue(child.getPositionArray()[0] == 40 && child.getPositionArray()[1] == -2);
    }

    @Test
    void getChildrenCheckIfCopies() throws DAGConstraintException {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        Coord2D coord1 = new Coord2D(4, 9);
        Origin orig1 = new Origin(coord1);
        testOrigin.addToChildren(orig1);
        HashSet<Point> children = testOrigin.getChildrenSet();
        assertFalse(children.contains(orig1));
    }

    @Test
    void cloneValues() {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Point test2 = testOrigin.clone();
        assertTrue(testOrigin.equalsByValues(test2));
    }

    @Test
    void cloneDeepCopy() {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Point test2 = testOrigin.clone();
        assertNotSame(testOrigin, test2);
    }

    @Test
    void addToChildren() throws DAGConstraintException {
        Origin o1 = new Origin(new Coord2D(0, 0));
        Origin o2 = new Origin(new Coord2D(3,3));
        Origin o3 = new Origin(new Coord2D(-3, 1));
        Origin o4 = new Origin(new Coord2D(-5, 1));
        Origin o5 = new Origin(new Coord2D(6, -7));
        Origin o6 = new Origin(new Coord2D(-1, 1));
        Origin o7 = new Origin(new Coord2D(0, 2));
        Point p1 = new Point(new Coord2D(0, 2));
        o1.addToChildren(o3);
        o1.addToChildren(o2);
        o1.addToChildren(o5);
        o2.addToChildren(p1);
        o3.addToChildren(o2);
        o4.addToChildren(o2);
        o5.addToChildren(o4);
        o4.addToChildren(o6);
        o6.addToChildren(o7);
        boolean caughtDAGConstraintException = false;
        try {
            o3.addToChildren(o4);
        }
        catch (DAGConstraintException e) {
            caughtDAGConstraintException = true;
        }
        assertEquals(caughtDAGConstraintException, false);
    }

    @Test
    void addToChildrenSameOriginException() throws DAGConstraintException {
        Origin o1 = new Origin(new Coord2D(0, 0));
        Origin o2 = new Origin(new Coord2D(3,3));
        Origin o3 = new Origin(new Coord2D(-3, 1));
        Origin o4 = new Origin(new Coord2D(-5, 1));
        Origin o5 = new Origin(new Coord2D(6, -7));
        Origin o6 = new Origin(new Coord2D(-1, 1));
        Origin o7 = new Origin(new Coord2D(0, 2));
        Point p1 = new Point(new Coord2D(0, 2));
        o1.addToChildren(o3);
        o1.addToChildren(o2);
        o1.addToChildren(o5);
        o2.addToChildren(p1);
        o3.addToChildren(o2);
        o4.addToChildren(o2);
        o5.addToChildren(o4);
        o4.addToChildren(o6);
        o6.addToChildren(o7);
        boolean caughtDAGConstraintException = false;
        try {
            o3.addToChildren(o3);
        }
        catch (DAGConstraintException e) {
            caughtDAGConstraintException = true;
        }
        assertEquals(caughtDAGConstraintException, true);
    }

    @Test
    void addToChildrenException() throws DAGConstraintException {
        Origin o1 = new Origin(new Coord2D(0, 0));
        Origin o2 = new Origin(new Coord2D(3,3));
        Origin o3 = new Origin(new Coord2D(-3, 1));
        Origin o4 = new Origin(new Coord2D(-5, 1));
        Origin o5 = new Origin(new Coord2D(6, -7));
        Origin o6 = new Origin(new Coord2D(-1, 1));
        Origin o7 = new Origin(new Coord2D(0, 2));
        Point p1 = new Point(new Coord2D(0, 2));
        o1.addToChildren(o3);
        o1.addToChildren(o2);
        o1.addToChildren(o5);
        o2.addToChildren(p1);
        o3.addToChildren(o2);
        o3.addToChildren(o4);
        o4.addToChildren(o2);
        o5.addToChildren(o4);
        o4.addToChildren(o6);
        o6.addToChildren(o7);
        boolean caughtDAGConstraintException = false;
        try {
            o7.addToChildren(o1);
        }
        catch (DAGConstraintException e) {
            caughtDAGConstraintException = true;
        }
        assertEquals(caughtDAGConstraintException, true);
    }

    @Test
    void setChildrenValues() throws DAGConstraintException {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Coord2D test = new Coord2D(2, -22);
        Point test2 = new Point(test);
        HashSet<Point> newChildrenSet = new HashSet<>();
        newChildrenSet.add(test2);
        testOrigin.setChildren(newChildrenSet);
        Point child = newChildrenSet.iterator().next();
        assertTrue(child.getPositionArray()[0] == 2 && child.getPositionArray()[1] == -22);
    }

    @Test
    void setChildrenCheckIfCopies() throws DAGConstraintException {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Coord2D test = new Coord2D(2, -22);
        Point test2 = new Point(test);
        HashSet<Point> newChildrenSet = new HashSet<>();
        newChildrenSet.add(test2);
        testOrigin.setChildren(newChildrenSet);
        assertFalse(testOrigin.getChildrenSet().contains(test2));
    }

    @Test
    void setChildrenException() throws DAGConstraintException {
        Origin o1 = new Origin(new Coord2D(0, 0));
        Origin o2 = new Origin(new Coord2D(3,3));
        Origin o3 = new Origin(new Coord2D(-3, 1));
        Origin o4 = new Origin(new Coord2D(-5, 1));
        Origin o5 = new Origin(new Coord2D(6, -7));
        Origin o6 = new Origin(new Coord2D(-1, 1));
        Origin o7 = new Origin(new Coord2D(0, 2));
        Point p1 = new Point(new Coord2D(0, 2));
        o1.addToChildren(o3);
        o1.addToChildren(o2);
        o1.addToChildren(o5);
        o2.addToChildren(p1);
        o3.addToChildren(o2);
        o3.addToChildren(o4);
        o4.addToChildren(o2);
        o5.addToChildren(o4);
        o4.addToChildren(o6);
        o6.addToChildren(o7);
        boolean caughtDAGConstraintException = false;
        try {
            HashSet<Point> newChildren = new HashSet<>();
            newChildren.add(o1);
            newChildren.add(o2);
            newChildren.add(o5);
            o7.setChildren(newChildren);
        }
        catch (DAGConstraintException e) {
            caughtDAGConstraintException = true;
        }
    }

    @Test
    void equalsByValuesTrue() {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Coord2D test = new Coord2D(13, -2);
        Origin test2 = new Origin(test);
        assertTrue(testOrigin.equalsByValues(test2) && testOrigin != test2);
    }

    @Test
    void equalsByValuesFalse() {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Coord2D test = new Coord2D(3, -2);
        Origin test2 = new Origin(test);
        assertFalse(testOrigin.equalsByValues(test2));
    }

    @Test
    void getBoundsNull() throws DAGConstraintException {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Origin o1 = new Origin(new Coord2D(2, 10));
        Origin o2 = new Origin(new Coord2D(12, 1));
        testOrigin.addToChildren(o1);
        testOrigin.addToChildren(o2);
        BoundBox bounds = testOrigin.getBounds();
        assertNull(bounds.getBoundsArray());
    }

    @Test
    void getBoundsSingle() throws DAGConstraintException {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Coord2D coord2 = new Coord2D(40, -2);
        Point point1 = new Point(coord2);
        testOrigin.addToChildren(point1);
        BoundBox bounds = testOrigin.getBounds();
        assertTrue(bounds.getBoundsArray()[0][0] == 53 && bounds.getBoundsArray()[0][1] == -4 &&
                bounds.getBoundsArray()[1][0] == 53 && bounds.getBoundsArray()[1][1] == -4);
    }

    @Test
    void getBoundsPlural() throws DAGConstraintException {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        Coord2D coord1 = new Coord2D(4, 9);
        Coord2D coord2 = new Coord2D(40, -2);
        Coord2D coord3 = new Coord2D(24, 0);
        Coord2D coord4 = new Coord2D(32, 109);
        Origin orig1 = new Origin(coord1);
        Origin orig2 = new Origin(coord4);
        Point point1 = new Point(coord2);
        Point point2 = new Point(coord3);
        Point point3 = new Point(coord4);
        orig1.addToChildren(orig2);
        orig1.addToChildren(point1);
        orig2.addToChildren(point2);
        testOrigin.addToChildren(orig1);
        testOrigin.addToChildren(point3);
        BoundBox bounds = testOrigin.getBounds();
        assertTrue(bounds.getBoundsArray()[0][0] == 34 && bounds.getBoundsArray()[0][1] == 12 &&
                bounds.getBoundsArray()[1][0] == 62 && bounds.getBoundsArray()[1][1] == 123);
    }
}