package org.jfree.data.test;

import org.jfree.data.Range;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {
	Range range;

	@Before
	public void arrange() {
		range = new Range(1, 10);
	}

	private void arrange(double lower, double upper) {
		range = new Range(lower, upper);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testConst() {
		Range range1 = new Range(20, 1);
	}

	@Test
	public void testConst1() {
		Range range1 = new Range(1, 20);
		Assert.assertEquals(1, range1.getLowerBound(), 1E-10);
		Assert.assertEquals(20, range1.getUpperBound(), 1E-10);
	}

	@Test
	public void testGetLength() {
		Assert.assertEquals(9, range.getLength(), 1E-7);
	}


}