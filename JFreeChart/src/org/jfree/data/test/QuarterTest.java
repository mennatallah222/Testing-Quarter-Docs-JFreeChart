package org.jfree.data.test;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.TimePeriodFormatException;
import org.jfree.data.time.Year;
import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.TimeZone;

public class QuarterTest {

    // 1- To test Quarter Default Constructor
    @Test
    public void testDefaultConstructor() {
        Quarter quarter = new Quarter();
        Calendar calendar = Calendar.getInstance();//It gets the current quarter and year
        int Current_quarter = (calendar.get(Calendar.MONTH) / 3) + 1; // Current quarter
        int Current_year = calendar.get(Calendar.YEAR);
        Assert.assertEquals(Current_quarter, quarter.getQuarter());
        Assert.assertEquals(Current_year, quarter.getYear().getYear());
    }//////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////////////
    // 2- To test Quarter Parameterize Constructor with Int (year) and quarter
    @Test
    public void testConstructorWithYearAndQuarter1() {
        Quarter quarter = new Quarter(2, 2024);
        Assert.assertEquals(2, quarter.getQuarter());
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithYearAndQuarter2() {
        Quarter quarter = new Quarter(0, 2024);
        Assert.assertEquals(0, quarter.getQuarter());
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithYearAndQuarter3() {
        Quarter quarter = new Quarter(2, 10000);
        Assert.assertEquals(2, quarter.getQuarter());
        Assert.assertEquals(10000, quarter.getYear().getYear());
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    // 3- To test Quarter Parameterize Constructor with Year(year) and quarter
    @Test
    public void testConstructorWithYear1() {
        Year y = new Year(2024); // Create a Year object representing the year 2024
        Quarter quarter = new Quarter(3, y);// Create a Quarter object for the third quarter of the year 2024
        Assert.assertEquals(3, quarter.getQuarter());
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithYear2() {
        Year y = new Year(9999); // Create a Year object representing the year 2024
        Quarter quarter = new Quarter(0, y);// Create a Quarter object for the third quarter of the year 2024
        Assert.assertEquals(0, quarter.getQuarter());
        Assert.assertEquals(9999, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithYear3() {
        Year y = new Year(9999); // Create a Year object representing the year 2024
        Quarter quarter = new Quarter(5, y);// Create a Quarter object for the third quarter of the year 2024
        Assert.assertEquals(5, quarter.getQuarter());
        Assert.assertEquals(9999, quarter.getYear().getYear());
    }

    @Test
    public void testConstructorWithYear4() {
        Year y = new Year(10000); // Create a Year object representing the year 2024
        Quarter quarter = new Quarter(2, y);// Create a Quarter object for the third quarter of the year 2024
        Assert.assertEquals(2, quarter.getQuarter());
        Assert.assertEquals(10000, quarter.getYear().getYear());
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    // 4- To test Quarter Parameterize Constructor with Date object
    @Test
    public void testConstructorWithDate1() {
        Date d = getDateFromString("2024-06-15");
        assert d != null;
        Quarter quarter = new Quarter(d);
        Assert.assertEquals(2, quarter.getQuarter());
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithDate2() {
        Date d = getDateFromString("2024-00-15");
        assert d != null;
        Quarter quarter = new Quarter(d);
        Assert.assertEquals(0, quarter.getQuarter());
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    //5- To test Quarter Parameterize Constructor with Date and timezone Object
    @Test
    public void testConstructorWithDateAndTimeZone1() {
        Date date = getDateFromString("2024-06-15");
        TimeZone timeZone = TimeZone.getTimeZone("GMT+3");
        assert date != null;
        Quarter quarter = new Quarter(date, timeZone);
        Assert.assertEquals(2, quarter.getQuarter());
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    @Test
    public void testConstructorWithDateAndTimeZone2() {
        Date date = getDateFromString("2024-08-30");
        TimeZone timeZone = TimeZone.getTimeZone("GMT+5");
        assert date != null;// Assert that the Date object is not null
        Quarter quarter = new Quarter(date, timeZone);
        // Assert that the quarter is set correctly (This assumes that the date corresponds to a valid quarter)
        Assert.assertEquals(3, quarter.getQuarter());
        // Assert that the year is set correctly (This assumes that the date corresponds to a valid year)
        Assert.assertEquals(2024, quarter.getYear().getYear());
    }

    // Helper methods
    private Date getDateFromString(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////ALL TEST METHODS//////////////////////////////////
    //Here are some test cases for the getQuarter method:
    //Check if the method returns the correct quarter for a valid Quarter instance.
    @Test
    public void testGetQuarter_ValidQuarter() {
        Quarter quarter = new Quarter(3, new Year(2024));
        Assert.assertEquals(3, quarter.getQuarter());
    }

    // Check if the method returns the correct quarter for the first quarter (boundary case).
    @Test
    public void testGetQuarter_BoundaryFirstQuarter() {
        Quarter quarter = new Quarter(1, new Year(2024));
        Assert.assertEquals(1, quarter.getQuarter());
    }

    //Check if the method returns the correct quarter for the last quarter (boundary case).
    @Test
    public void testGetQuarter_BoundaryLastQuarter() {
        Quarter quarter = new Quarter(4, new Year(2024));
        Assert.assertEquals(4, quarter.getQuarter());
    }

    //Check if the method handles an invalid quarter value correctly.
    @Test(expected = IllegalArgumentException.class)
    public void testGetQuarter_InvalidQuarter() {
        Quarter quarter = new Quarter(0, new Year(2024));
        quarter.getQuarter();
    }

    // Check if the method handles the maximum possible value of the quarter (boundary case).
    @Test(expected = IllegalArgumentException.class)
    public void testGetQuarter_MaxValueQuarter() {
        Quarter quarter = new Quarter(Integer.MAX_VALUE, new Year(2024));
        quarter.getQuarter();
    }

    //Here are some test cases for the getQuarter method:
    //check for valid year
    @Test
    public void testGetYear_ValidYear() {
        Year year = new Year(1900);
        Quarter quarter = new Quarter(3, year);
        Assert.assertEquals(year, quarter.getYear());
    }

    // Check if the method returns the correct year for Smallest possible integer value.
    @Test
    public void testGetYear_Min_valueYear() {
        Year year = new Year(Integer.MIN_VALUE);
        Quarter quarter = new Quarter(1, year);
        Assert.assertEquals(year, quarter.getYear());
    }

    // Check if the method returns the correct year for largest possible integer value.
    @Test
    public void testGetYear_Max_valueYear() {
        Year year = new Year(Integer.MAX_VALUE); // Largest possible integer value
        Quarter quarter = new Quarter(4, year);
        Assert.assertEquals(year, quarter.getYear());
    }

    //check the getYear method correctly returns null when the Year object is not provided
    @Test
    public void testGetYear_NullYear() {
        Quarter quarter = new Quarter(2, null);
        Assert.assertNull(quarter.getYear());
    }
    ////////////////////////PREVIOUS////////////////////////////////////////////
    //Test case for the previous quarter within the same year
    @Test
    public void testPreviousWithinSameYear() {
        Quarter currentQuarter = new Quarter(2, 2024);
        Quarter expectedPrevious = new Quarter(1, 2024);
        Assert.assertEquals(expectedPrevious, currentQuarter.previous());
    }
    //Test case for the previous quarter spanning over to the previous year:
    @Test
    public void testPreviousSpanningToPreviousYear() {
        Quarter currentQuarter = new Quarter(1, 2024);
        Quarter expectedPrevious = new Quarter(4, 2023);
        Assert.assertEquals(expectedPrevious, currentQuarter.previous());
    }
    //Test case for the previous quarter when it's the first quarter of the first year
    @Test
    public void testPreviousFirstQuarterFirstYear() {
        Quarter currentQuarter = new Quarter(1, 1900);
        Assert.assertNull(currentQuarter.previous());
    }
    //Test case for a custom scenario
    @Test
    public void testPreviousCustomScenario() {
        Quarter currentQuarter = new Quarter(3, 2010);
        Quarter expectedPrevious = new Quarter(2, 2010);
        Assert.assertEquals(expectedPrevious, currentQuarter.previous());
    }
    //Test case for the last quarter of a year:
    @Test
    public void testPreviousLastQuarterOfYear() {
        Quarter currentQuarter = new Quarter(4, 2022);
        Quarter expectedPrevious = new Quarter(3, 2022);
        Assert.assertEquals(expectedPrevious, currentQuarter.previous());
    }
    ///////////////////////////////NEXT///////////////////////////////////////////////////
    ///Test Case FOR Next Quarter within the Same Year
    @Test
    public void testNextQuarterWithinSameYear() {
        Quarter currentQuarter = new Quarter(1, 2024);
        Quarter nextQuarter = (Quarter) currentQuarter.next();
        Assert.assertEquals(2, nextQuarter.getQuarter());
        Assert.assertEquals(2024, nextQuarter.getYear().getYear());
    }
    /// Test case for Next Quarter Crossing Year Boundary
    @Test
    public void testNextQuarterCrossingYearBoundary() {
        Quarter currentQuarter = new Quarter(4, 2023);
        Quarter nextQuarter = (Quarter) currentQuarter.next();
        Assert.assertEquals(1, nextQuarter.getQuarter());
        Assert.assertEquals(2024, nextQuarter.getYear().getYear());
    }
    //Test Case for  Next Quarter of the Last Possible Year
    @Test
    public void testNextQuarterLastPossibleYear() {
        Quarter currentQuarter = new Quarter(3, 9998);
        Quarter nextQuarter = (Quarter) currentQuarter.next();
        Assert.assertEquals(4, nextQuarter.getQuarter());
        Assert.assertEquals(9998, nextQuarter.getYear().getYear());
    }
    // Test Case for Next Quarter of the Last Possible Year (Boundary Case)
    @Test
    public void testNextQuarterLastPossibleYearBoundaryCase() {
        Quarter currentQuarter = new Quarter(4, 9998);
        Quarter nextQuarter = (Quarter) currentQuarter.next();
        Assert.assertNull(nextQuarter);
    }
    //Test Case for Next Quarter for Custom Year Range
    @Test
    public void testNextQuarterCustomYearRange() {
        Quarter currentQuarter = new Quarter(2, 1000);
        Quarter nextQuarter = (Quarter) currentQuarter.next();
        Assert.assertEquals(3, nextQuarter.getQuarter());
        Assert.assertEquals(1000, nextQuarter.getYear().getYear());
    }

    /////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testGetSerialIndex() {
        // Test case 1: Create a Quarter instance for Q1 2023
        Quarter q1_2023 = new Quarter(1, 2023);
        long expectedIndex1 = 2023 * 4 + 1; // Serial index for Q1 2023
        Assert.assertEquals(expectedIndex1, q1_2023.getSerialIndex());
    }

    @Test
    public void testGetSerialIndex3() {
        // Test case 2: Create a Quarter instance for Q2 2000
        Quarter q2_2000 = new Quarter(2, 2000);
        long expectedIndex2 = 2000 * 4 + 2; // Serial index for Q2 2000
        Assert.assertEquals(expectedIndex2, q2_2000.getSerialIndex());

    }
    @Test
    public void testGetSerialIndex4() {
        // Test case 3: Create a Quarter instance for Q3 9999
        Quarter q3_9999 = new Quarter(3, 9999);
        long expectedIndex3 = 9999 * 4 + 3; // Serial index for Q3 9999
        Assert.assertEquals(expectedIndex3, q3_9999.getSerialIndex());

    }


    @Test
    public void testGetSerialIndex2(){
        // Test with a quarter in the middle of the range
        Quarter quarter = new Quarter(2, 2024);
        long expectedIndex = 2024 * 4 + 1;
        Assert.assertEquals(expectedIndex, quarter.getSerialIndex());

        // Test with the first quarter
        Quarter firstQuarter = new Quarter(1, 1900);
        expectedIndex = 1900 * 4;
        Assert.assertEquals(expectedIndex, firstQuarter.getSerialIndex());

        // Test with the last quarter
        Quarter lastQuarter = new Quarter(4, 9999);
        expectedIndex = 9999L * 4 + 3;
        Assert.assertEquals(expectedIndex, lastQuarter.getSerialIndex());
    }


    /////////////////////////////////////////////////////
    @Test
    public void testEqualObject() {
        Quarter q1=new Quarter(3, 2024);
        Quarter q2=new Quarter(1, 2024);
        Assert.assertFalse(q1.equals(q2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDifferentObjects1() {
        Quarter q1=new Quarter(3, 2024);
        Assert.assertFalse(q1.equals("This is not a Quarter object!"));
    }
    @Test
    public void testDifferentObjects2() {
        Quarter q1=new Quarter(3,  new Year(2024));
        Quarter q2=new Quarter(3,  new Year(2025));
        Assert.assertFalse(q1.equals(q2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqualsWithBoundaries1() {
        Quarter q1=new Quarter(3, 100000);
        Quarter q2=new Quarter(3, 100000);

        Assert.assertTrue(q1.equals(q2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testEqualsWithBoundaries2() {
        Quarter q1=new Quarter(5, 2024);
        Quarter q2=new Quarter(5, 2024);

        Assert.assertTrue(q1.equals(q2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testEqualsWithBoundaries3() {
        Quarter q1=new Quarter(0, 2024);
        Quarter q2=new Quarter(0, 2024);

        Assert.assertTrue(q1.equals(q2));
    }
    @Test(expected = NullPointerException.class)
    public void testEqualsWithNull() {
        Quarter q1=new Quarter(2, 2024);

        Assert.assertFalse(q1.equals(null));
    }

    ////////////////////////////////////////////////////////////////

    @Test
    public void testHashCodeConsistency() {
        Quarter q1=new Quarter(2, new Year(2024));
        int hc1=q1.hashCode();
        int hc2=q1.hashCode();
        Assert.assertEquals(hc1, hc2);
    }

    @Test
    public void testHashCodeConsistencyWithEqualQuarters() {
        Quarter q1=new Quarter(2, new Year(2024));
        Quarter q2=new Quarter(2, new Year(2024));

        Assert.assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentQuarters() {
        Quarter q1=new Quarter(2, new Year(2024));
        Quarter q2=new Quarter(3, new Year(2024));

        Assert.assertNotEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testHashCodeConsistencyWithEqualQuartersWithBoundaries1() {
        Quarter q1=new Quarter(0, new Year(2024));
        Quarter q2=new Quarter(0, new Year(2024));

        Assert.assertEquals(q1.hashCode(), q2.hashCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testHashCodeConsistencyWithEqualQuartersWithBoundaries2() {
        Quarter q1=new Quarter(5, new Year(2024));
        Quarter q2=new Quarter(5, new Year(2024));

        Assert.assertEquals(q1.hashCode(), q2.hashCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testHashCodeConsistencyWithEqualQuartersWithBoundaries3() {
        Quarter q1=new Quarter(2, new Year(10000));
        Quarter q2=new Quarter(2, new Year(10000));

        Assert.assertEquals(q1.hashCode(), q2.hashCode());
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testHashCodeConsistencyWithEqualQuartersWithBoundaries4() {
        Quarter q1=new Quarter(0, new Year(2024));
        Quarter q2=new Quarter(0, new Year(2024));

        Assert.assertEquals(q1.hashCode(), q2.hashCode());
    }
////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testCompareToWithEquals() {
        Quarter q1 = new Quarter(3, new Year(2024));
        Quarter q2 = new Quarter(3, new Year(2024));
        Assert.assertEquals(0, q1.compareTo(q2));
    }
    @Test
    public void testCompareToOrdering1() {
        Quarter q1 = new Quarter(1, new Year(2024));
        Quarter q2 = new Quarter(3, new Year(2024));
        Assert.assertTrue( q1.compareTo(q2)<0);
        Assert.assertTrue( q2.compareTo(q1)>0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompareToOrderingWithOneBoundary() {
        Quarter q1 = new Quarter(1, new Year(2024));
        Quarter q2 = new Quarter(5, new Year(2024));
        Assert.assertTrue( q1.compareTo(q2)<0);
        Assert.assertTrue( q2.compareTo(q1)>0);

    }
    @Test(expected = NullPointerException.class)
    public void testWithNull() {
        Quarter quarter = new Quarter(2, 2024);
        quarter.compareTo(null);
    }
    @Test
    public void testOnBoundaries() {
        Quarter q1=new Quarter(1, new Year(1900));
        Quarter q2= new Quarter(4, new Year(9999));
        Assert.assertTrue(q1.compareTo(q2)<0);
    }


    ////////////////////////////////////////////////////
    @Test
    public void testToStringWithBoundary() {
        Quarter q=new Quarter(4, 2024);
        Assert.assertEquals("Q4/2024", q.toString());
    }
    @Test
    public void testToStringOWithCurrentQuarter() {
        Quarter q=new Quarter();
        int currentYear=Calendar.getInstance().get(Calendar.YEAR);
        int currentQuarter=(Calendar.getInstance().get(Calendar.MONTH)/3)+1;
        Assert.assertEquals("Q"+currentQuarter+"/"+currentYear, q.toString());

    }

    @Test
    public void testToStringWithParseQuarter() {
        Quarter q=Quarter.parseQuarter("2024-Q1");
        Assert.assertEquals("Q1/2024", q.toString());
    }

    @Test
    public void testToStringOnBoundaries1() {
        Quarter q=new Quarter(1, 1900);
        Assert.assertEquals("Q1/1900", q.toString());
    }
    @Test
    public void testToStringOnBoundaries2() {
        Quarter q=new Quarter(4, 9999);
        Assert.assertEquals("Q4/9999", q.toString());
    }
    @Test
    public void testTosStringWithDate(){
        Date d = getDateFromString("2024-10-15");
        assert d != null;
        Quarter q = new Quarter(d);
        Assert.assertEquals("Q4/2024", q.toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWithOutOfBoundaryQuarter() {
        new Quarter(0, 2024);
    }

    ///////////////////////////////////////////////////////////////////
    @Test
    public void testGetFirstMillisecond() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter();

        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetFirstMillisecondWithBoundaries1() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter(1, 9999);

        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();

        Assert.assertEquals(expected, actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstMillisecondWithBoundaries2() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter(5, 9999);

        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetFirstMillisecondWithBoundaries3() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.OCTOBER);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter(4, 9999);

        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();

        Assert.assertEquals(expected, actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstMillisecondWithBoundaries4() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter(1, 10000);

        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();

        Assert.assertEquals(expected, actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstMillisecondWithBoundaries5() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter(0, new Year(9999));

        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstMillisecondWithBoundaries6() {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter q=new Quarter(-1, new Year(9999));
        long actual=q.getFirstMillisecond(c);
        long expected = c.getTimeInMillis();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetFirstMillisecondWithTimeZone() {
        //a calendar for Q1/2024 in GMT+2 (Egypt's) time zone
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+2"));
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Quarter quarter = new Quarter(1, 2024);
        long expectedFirstMillisecond = c.getTimeInMillis();
        Assert.assertEquals(expectedFirstMillisecond, quarter.getFirstMillisecond(c));
    }

    @Test(expected = NullPointerException.class)
    public void testGetFirstMillisecondWithNull(){
        Quarter q=new Quarter(1, 2024);
        q.getFirstMillisecond((Calendar) null);
    }

    //////////////////////////////////////////////////////////////////
    @Test
    public void testGetLastMillisecond() {
        Calendar c = Calendar.getInstance();
        c.set(2024, Calendar.JUNE, 30, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999); //the last millisecond of the day
        Quarter quarter = new Quarter(2, 2024);
        Assert.assertEquals(c.getTimeInMillis(), quarter.getLastMillisecond(c));
    }

    @Test
    public void testGetLastMillisecondOnBoundary1() {
        Calendar c = Calendar.getInstance();
        c.set(1900, Calendar.MARCH, 31, 23, 59, 59); // March 31st is the last day of Q1
        c.set(Calendar.MILLISECOND, 999); //the last millisecond of the day
        Quarter q = new Quarter(1, 1900);
        Assert.assertEquals(c.getTimeInMillis(), q.getLastMillisecond(c));
    }
    @Test
    public void testGetLastMillisecondOnBoundary2() {
        Calendar c = Calendar.getInstance();
        c.set(9999, Calendar.DECEMBER, 31, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);
        Quarter q = new Quarter(4, 9999);
        Assert.assertEquals(c.getTimeInMillis(), q.getLastMillisecond(c));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetLastMillisecondOutOfBoundary1() {
        Quarter q = new Quarter(0, 2024);
        Calendar c = Calendar.getInstance();
        c.set(1900, Calendar.MARCH, 31, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);
        Assert.assertEquals(c.getTimeInMillis(), q.getLastMillisecond(c));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetLastMillisecondOutOfBoundary2() {
        Quarter q = new Quarter(0, 2024);
        Calendar c = Calendar.getInstance();
        c.set(1900, Calendar.MARCH, 31, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);
        Assert.assertEquals(c.getTimeInMillis(), q.getLastMillisecond(c));
    }

    //////////////////////////////////////////////////////////////////
    @Test
    public void testParseQuarter() {
        Quarter q=new Quarter(1, 2024);
        Assert.assertEquals(Quarter.parseQuarter("Q1-2024"), q);
        Assert.assertEquals(Quarter.parseQuarter("Q1 2024"), q);
        Assert.assertEquals(Quarter.parseQuarter("Q1/2024"), q);
        Assert.assertEquals(Quarter.parseQuarter("Q1,2024"), q);

        Assert.assertEquals(Quarter.parseQuarter("2024-Q1"), q);
        Assert.assertEquals(Quarter.parseQuarter("2024/Q1"), q);
        Assert.assertEquals(Quarter.parseQuarter("2024,Q1"), q);
        Assert.assertEquals(Quarter.parseQuarter("2024 Q1"), q);

        Assert.assertEquals(4, Quarter.parseQuarter("Q4-2024").getQuarter());
        Assert.assertEquals(2024, Quarter.parseQuarter("Q4-2024").getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterWithEmptyString() {
        Quarter q=new Quarter(1, 2024);
        Assert.assertEquals(Quarter.parseQuarter(""), q);
    }
    @Test
    public void testParseQuarterInvalidQuarter() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {Quarter.parseQuarter("Q5/2024");
        });
    }


}