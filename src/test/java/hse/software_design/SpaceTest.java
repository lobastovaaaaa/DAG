package hse.software_design;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {
    private Space testSpace;

    @BeforeAll
    static void prepare() {
        System.out.println("Testing class Space started");
    }

    @AfterAll
    static void end() {
        System.out.println("Testing class Space ended");
    }

    @BeforeEach
    void setUp() { System.out.println("FuncTest started"); }

    @AfterEach
    void tearDown() {
        testSpace = null;
        System.out.println("FuncTest ended");
    }

    @Test
    void zeroParamConstructor() {
        testSpace = new Space();
        assertTrue(testSpace.getRoot().equalsByValues(new Origin(new Coord2D(0, 0))));
    }

    @Test
    void singleParamConstructor() {
        testSpace = new Space(new Origin(new Coord2D(12, -4)));
        assertTrue(testSpace.getRoot().equalsByValues(new Origin(new Coord2D(12, -4))));
    }

    @Test
    void getRoot() {
        Origin testOrigin = new Origin(new Coord2D(12, 23));
        testSpace = new Space(testOrigin);
        assertEquals(testSpace.getRoot(), testOrigin);
    }

    @Test
    void setAsRoot() {
        testSpace = new Space();
        testSpace.setAsRoot(new Origin(new Coord2D(9, 10)));
        Origin testOrigin = new Origin(new Coord2D(9, 10));
        assertTrue(testSpace.getRoot().equalsByValues(testOrigin) &&
                testSpace.getRoot() != testOrigin);
    }

    @Test
    void getBounds() throws DAGConstraintException {
        Origin testOrigin = new Origin(new Coord2D(2, 5));
        testSpace = new Space(testOrigin);
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
        BoundBox bounds = testSpace.getBounds();
        assertTrue(bounds.getBoundsArray()[0][0] == 34 && bounds.getBoundsArray()[0][1] == 12 &&
                bounds.getBoundsArray()[1][0] == 62 && bounds.getBoundsArray()[1][1] == 123);
    }

    @Test
    void testClone() throws DAGConstraintException {
        testSpace = new Space(new Origin(new Coord2D(2, 10)));
        testSpace.getRoot().addToChildren(new Origin(new Coord2D(-1, -3)));
        testSpace.getRoot().addToChildren(new Point(new Coord2D(3, -1)));
        assertTrue(testSpace.equalsByValues(testSpace.clone()) && testSpace != testSpace.clone());
    }
}