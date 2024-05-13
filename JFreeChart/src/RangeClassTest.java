import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jfree.data.Range;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RangeClassTest {
    Range range;

    private void arrange(double lower, double upper) {
        range = new Range(lower, upper);
    }

    @Ignore
    @Test
    public void testEPS() {
        Assert.assertEquals(3.14249, 3.14359, 1E-2);
    }

    @Test
    public void testRangeCtor() {
        // AAA
        // arrange
        int lower = 2;
        int upper = 7;
        // act
        Range r = new Range(lower, upper);
        // assert
        assertEquals(lower, r.getLowerBound(), 1E-13);
        assertEquals(upper, r.getUpperBound(), 1E-13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeCtor2() {
        arrange(9, 3);
    }

    @Test
    public void testRangeCtor3() {
        arrange(9, 9);
        // assert
        assertEquals(9, range.getLowerBound(), 1E-13);
    }

    @Test
    public void testGetUpperBound() {
        arrange(2, 7);
        // assert
        assertEquals(7, range.getUpperBound(), 1E-13);
    }

    @Test
    public void testGetLength() {
        arrange(2, 7);
        // assert
        assertEquals(5, range.getLength(), 1E-13);
    }

    @Test
    public void testGetLength2() {
        arrange(7, 7);
        // assert
        assertEquals(0, range.getLength(), 1E-13);
    }

    @Test
    public void testGetCentralValue() {
        arrange(0, 10);
        // assert
        assertEquals(5, range.getCentralValue(), 1E-13);
    }

    @Test
    public void testGetCentralValue2() {
        arrange(1, 10);
        // assert
        assertEquals(5.5, range.getCentralValue(), 1E-13);
    }

    @Test
    public void testGetCentralValue3() {
        // -1 0 1 2 3 4 5
        arrange(-1, 5);
        // assert
        assertEquals(2, range.getCentralValue(), 1E-13);
    }

    @Test
    public void testContains() {
        // -1 0 1 2 3 4 5
        arrange(-1, 5);
        // assert
        // System.out.println(range.contains(Double.MAX_VALUE));
        // System.out.println(range.contains(Double.MIN_VALUE));
        assertFalse(range.contains(Double.MIN_VALUE));
        assertFalse(range.contains(-1 - 1E-13));
        assertTrue(range.contains(-1));
        assertTrue(range.contains(0));
        assertTrue(range.contains(5));
        assertFalse(range.contains(5 + 1E-13));
        assertFalse(range.contains(Double.MAX_VALUE));
    }

    @Test
    public void testIntersect() {
        arrange(-5, 5);
        // assert
        assertTrue(range.intersects(-7, -5));
        assertTrue(range.intersects(-7, -4));
        assertTrue(range.intersects(-4, 4));
        assertFalse(range.intersects(6, 7));
    }

    @Test
    public void testIntersect2() {
        arrange(-5, 5);
        // assert
        assertTrue(range.intersects(4, 7));
        assertTrue(range.intersects(5, 7));
        assertTrue(range.intersects(-7, 7));
        assertFalse(range.intersects(-7, -6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersect3() {
        arrange(-5, 5);
        // assert
        range.intersects(7, 4);
    }

    @Test
    public void testConstrain() {
        arrange(-5, 5);
        // assert
        assertEquals(2, range.constrain(2), 1E-13);
        assertEquals(-5, range.constrain(-5), 1E-13);
        assertEquals(5, range.constrain(5), 1E-13);

        assertEquals(-5 + 1E-13, range.constrain(-5 + 1E-13), 1E-13);
        assertEquals(-5, range.constrain(-5 - 1E-13), 1E-13);

        assertEquals(5 - 1E-13, range.constrain(5 - 1E-13), 1E-13);
        assertEquals(5, range.constrain(5 + 1E-13), 1E-13);
    }

    @Test
    public void testCombine() {
        arrange(-5, 5);
        Range r = new Range(-10, -6);
        // act
        Range result = Range.combine(range, r);
        // assert
        assertEquals(-10, result.getLowerBound(), 1E-13);

        assertEquals(5, result.getUpperBound(), 1E-13);
    }

    @Test
    public void testCombine2() {
        arrange(-5, 5);
        // act
        Range result = Range.combine(range, null);
        // assert
        assertEquals(range.getLowerBound(), result.getLowerBound(), 1E-13);
        assertEquals(range.getUpperBound(), result.getUpperBound(), 1E-13);
    }

}
