package hse.software_design;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Coord2DTest {
    private Coord2D testCoord2D;

    @BeforeAll
    static void prepare() {
        System.out.println("Testing class Coord2D started");
    }

    @AfterAll
    static void end() {
        System.out.println("Testing class Coord2D ended");
    }

    @BeforeEach
    void setUp() {
        System.out.println("FuncTest started");
    }

    @AfterEach
    void tearDown() {
        testCoord2D = null;
        System.out.println("FuncTest ended");
    }

    @Test
    void getCoordinates() {
        testCoord2D = new Coord2D(2, 5);
        assertTrue(testCoord2D.getCoordinates()[0] == 2 &&
                testCoord2D.getCoordinates()[1] == 5);
    }

    @Test
    void testCloneValues() {
        testCoord2D = new Coord2D(13, -2);
        Coord2D test2 = testCoord2D.clone();
        assertTrue(testCoord2D.equalsByValues(test2));
    }

    @Test
    void testCloneDeepCopy() {
        testCoord2D = new Coord2D(13, -2);
        Coord2D test2 = testCoord2D.clone();
        assertNotSame(testCoord2D, test2);
    }

    @Test
    void testEqualByValuesTrue() {
        testCoord2D = new Coord2D(13, -2);
        Coord2D test2 = new Coord2D(13, -2);
        assertTrue(testCoord2D.equalsByValues(test2) && test2 != testCoord2D);
    }

    @Test
    void testEqualByValuesFalse() {
        testCoord2D = new Coord2D(13, -2);
        Coord2D test2 = new Coord2D(3, -12);
        assertFalse(testCoord2D.equalsByValues(test2));
    }
}