package hse.software_design;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BoundBoxTest {
    private Origin testOrigin;
    private Coord2D testCoord2D;
    private Point testPoint;
    private BoundBox testBounds;

    @BeforeAll
    static void prepare() {
        System.out.println("Testing class BoundBox started");
    }

    @AfterAll
    static void end() {
        System.out.println("Testing class BoundBox ended");
    }

    @BeforeEach
    void setUp() { System.out.println("FuncTest started"); }

    @AfterEach
    void tearDown() {
        testBounds = null;
        testPoint = null;
        testOrigin = null;
        testCoord2D = null;
        System.out.println("FuncTest ended");
    }

    @Test
    void boundBoxPoint() {
        testCoord2D = new Coord2D(19, -3);
        testPoint = new Point(testCoord2D);
        testBounds = new BoundBox(testPoint);
        assertTrue(testBounds.getBoundsArray()[0][0] == 19 && testBounds.getBoundsArray()[0][1] == -3 &&
                testBounds.getBoundsArray()[1][0] == 19 && testBounds.getBoundsArray()[1][1] == -3);
    }

    @Test
    void boundBoxOriginNull() {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        testBounds = new BoundBox(testOrigin);
        assertNull(testBounds.getBoundsArray());
    }

    @Test
    void boundBoxOrigin() {
        testCoord2D = new Coord2D(19, -3);
        testOrigin = new Origin(testCoord2D);
        testBounds = new BoundBox(testOrigin);
        assertNull(testBounds.getBoundsArray());
    }

    @Test
    void getMinNull() {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        Coord2D min = testOrigin.getBounds().getMin();
        assertNull(min);
    }

    @Test
    void getMin() throws DAGConstraintException {
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
        testBounds = testOrigin.getBounds();
        Coord2D localMin = new Coord2D(34, 12);
        assertTrue(testBounds.getMin().equalsByValues(localMin));
    }

    @Test
    void getMaxNull() {
        testCoord2D = new Coord2D(2, 5);
        testOrigin = new Origin(testCoord2D);
        Coord2D max = testOrigin.getBounds().getMax();
        assertNull(max);
    }

    @Test
    void getMax() throws DAGConstraintException {
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
        testBounds = testOrigin.getBounds();
        Coord2D localMax = new Coord2D(62, 123);
        assertTrue(testBounds.getMax().equalsByValues(localMax));
    }

    @Test
    void equalsByValuesTrue() throws DAGConstraintException {
        testOrigin = new Origin(new Coord2D(-2, 2));
        Origin o1 = new Origin(new Coord2D(1, -1));
        Origin o2 = new Origin(new Coord2D(2, 1));
        Point p1 = new Point(new Coord2D(2, 0));
        Point p2 = new Point(new Coord2D(-1, -3));
        Point p3 = new Point(new Coord2D(1, 2));
        Origin o3 = new Origin((new Coord2D(-4, 1)));
        Point p4 = new Point(new Coord2D(1, -2));
        testOrigin.addToChildren(o1);
        testOrigin.addToChildren(o3);
        testOrigin.addToChildren(p3);
        o3.addToChildren(p4);
        o1.addToChildren(p2);
        o1.addToChildren(o2);
        o2.addToChildren(p1);
        testBounds = testOrigin.getBounds();

        Origin testOrigin2 = new Origin(new Coord2D(0, 0));
        Point p5 = new Point(new Coord2D(-5, -2));
        Point p6 = new Point(new Coord2D(3, 4));
        testOrigin2.addToChildren(p5);
        testOrigin2.addToChildren(p6);
        BoundBox testBounds2 = testOrigin2.getBounds();
        assertTrue(testBounds.equalsByValues(testBounds2));
    }

    @Test
    void equalsByValuesFalse() throws DAGConstraintException {
        testOrigin = new Origin(new Coord2D(-2, 2));
        Point p1 = new Point(new Coord2D(2, 0));
        Point p2 = new Point(new Coord2D(-1, -3));
        testOrigin.addToChildren(p1);
        testOrigin.addToChildren(p2);
        testBounds = testOrigin.getBounds();
        Origin testOrigin2 = new Origin(new Coord2D(0, 0));
        Point p3 = new Point(new Coord2D(-5, -2));
        Point p4 = new Point(new Coord2D(3, 4));
        testOrigin2.addToChildren(p3);
        testOrigin2.addToChildren(p4);
        BoundBox testBounds2 = testOrigin2.getBounds();
        assertFalse(testBounds.equalsByValues(testBounds2));
    }

    @Test
    void getBoundsNull() {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        testBounds = testOrigin.getBounds();
        assertNull(testBounds.getBoundsArray());
    }

    @Test
    void getBoundsSingle() throws DAGConstraintException {
        testCoord2D = new Coord2D(13, -2);
        testOrigin = new Origin(testCoord2D);
        Coord2D coord2 = new Coord2D(40, -2);
        Point point1 = new Point(coord2);
        testOrigin.addToChildren(point1);
        testBounds = testOrigin.getBounds();
        assertTrue(testBounds.getBoundsArray()[0][0] == 53 && testBounds.getBoundsArray()[0][1] == -4 &&
                testBounds.getBoundsArray()[1][0] == 53 && testBounds.getBoundsArray()[1][1] == -4);
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
        testBounds = testOrigin.getBounds();
        assertTrue(testBounds.getBoundsArray()[0][0] == 34 && testBounds.getBoundsArray()[0][1] == 12 &&
                testBounds.getBoundsArray()[1][0] == 62 && testBounds.getBoundsArray()[1][1] == 123);
    }
}