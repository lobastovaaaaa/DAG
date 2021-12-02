package hse.software_design;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point testPoint;
    private Coord2D testCoord2D;

    @BeforeAll
    static void prepare() {
        System.out.println("Testing class Point started");
    }

    @AfterAll
    static void end() {
        System.out.println("Testing class Point ended");
    }

    @BeforeEach
    void setUp() {
        System.out.println("FuncTest started");
    }

    @AfterEach
    void tearDown() {
        testPoint = null;
        System.out.println("FuncTest ended");
    }

    @Test
    void constructWithParams() {
        testCoord2D = new Coord2D(9, 10);
        testPoint = new Point(testCoord2D);
        assertTrue(testPoint.getPositionArray()[0] == 9 && testPoint.getPositionArray()[1] == 10);
    }

    @Test
    void getPosition() {
        testCoord2D = new Coord2D(2, 5);
        testPoint = new Point(testCoord2D);
        assertEquals(testPoint.getPosition(), testCoord2D);
    }

    @Test
    void getPositionByValue() {
        testCoord2D = new Coord2D(2, 5);
        testPoint = new Point(testCoord2D);
        assertTrue(testPoint.getPosition().getCoordinates()[0] == 2 &&
                testPoint.getPosition().getCoordinates()[1] == 5);
    }

    @Test
    void getPositionArray() {
        testCoord2D = new Coord2D(2, 5);
        testPoint = new Point(testCoord2D);


        assertTrue(testPoint.getPositionArray()[0] == 2 && testPoint.getPositionArray()[1] == 5);
    }

    @Test
    void setPosition() {
        testCoord2D = new Coord2D(2, 5);
        testPoint = new Point(new Coord2D(1, 1));
        testPoint.setPosition(testCoord2D);
        assertEquals(testPoint.getPosition(), testCoord2D);
    }

    @Test
    void boundBox() {
        testCoord2D = new Coord2D(19, -3);
        testPoint = new Point(testCoord2D);
        BoundBox bounds = testPoint.getBounds();
        assertTrue(bounds.getBoundsArray()[0][0] == 19 && bounds.getBoundsArray()[0][1] == -3 &&
                bounds.getBoundsArray()[1][0] == 19 && bounds.getBoundsArray()[1][1] == -3);
    }

    @Test
    void cloneValues() {
        testCoord2D = new Coord2D(13, -2);
        testPoint = new Point(testCoord2D);
        Point test2 = testPoint.clone();
        assertTrue(testPoint.equalsByValues(test2));
    }

    @Test
    void cloneDeepCopy() {
        testCoord2D = new Coord2D(13, -2);
        testPoint = new Point(testCoord2D);
        Point test2 = testPoint.clone();
        assertNotSame(testPoint, test2);
    }

    @Test
    void equalsByValuesTrue() {
        testCoord2D = new Coord2D(13, -2);
        testPoint = new Point(testCoord2D);
        Point test2 = testPoint.clone();
        assertTrue(testPoint != test2 && testPoint.equalsByValues(test2));
    }

    @Test
    void equalsByValuesFalse() {
        testCoord2D = new Coord2D(13, -2);
        testPoint = new Point(testCoord2D);
        Coord2D test2 = new Coord2D(3, -12);
        Point test = new Point(test2);
        assertFalse(testPoint.equalsByValues(test));
    }
}