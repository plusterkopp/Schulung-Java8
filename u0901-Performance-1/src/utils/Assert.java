package utils;

import java.util.Arrays;

public class Assert {
	public static void assertEquals(final Object expected, final Object actual) {
		if (!expected.equals(actual))
			throw new AssertionError("Expected: " + expected + "; but was: " + actual);
	}

	public static void assertEquals(final int expected, final int actual) {
		if (expected != actual)
			throw new AssertionError("Expected: " + expected + "; but was: " + actual);
	}

	public static void assertEquals(final long expected, final long actual) {
		if (expected != actual)
			throw new AssertionError("Expected: " + expected + "; but was: " + actual);
	}

	public static void assertEquals(final float expected, final float actual, final double delta) {
		if (Math.abs(expected - actual) > delta)
			throw new AssertionError("Expected: " + expected + "; but was: " + actual);
	}

	public static void assertEquals(final double expected, final double actual, final double delta) {
		if (Math.abs(expected - actual) > delta)
			throw new AssertionError("Expected: " + expected + "; but was: " + actual);
	}

	public static void assertNotNull(final Object obj) {
		if (obj == null)
			throw new AssertionError("Expected: not null; but was: null");
	}

	public static void assertNull(final Object obj) {
		if (obj != null)
			throw new AssertionError("Expected: null; but was: " + obj);
	}

	public static void assertSame(final Object expected, final Object actual) {
		if (expected != actual)
			throw new AssertionError("Expected: " + expected + "; but was: " + actual);
	}

	public static void assertNotSame(final Object expected, final Object actual) {
		if (expected == actual)
			throw new AssertionError("Expected: " + expected + "; and was: " + actual);
	}

	public static void assertTrue(final boolean condition) {
		if (!condition)
			throw new AssertionError("Expected: true; but was: false");
	}

	public static void assertFalse(final boolean condition) {
		if (condition)
			throw new AssertionError("Expected: false; but was: true");
	}

	public static void assertArrayEquals(final Object[] expected, final Object[] actual) {
		if (!Arrays.equals(expected, actual))
			throw new AssertionError("Expected: " + Arrays.toString(expected) + "; but was: " + Arrays.toString(actual));
	}

	public static void assertArrayEquals(final int[] expected, final int[] actual) {
		if (!Arrays.equals(expected, actual))
			throw new AssertionError("Expected: " + Arrays.toString(expected) + "; but was: " + Arrays.toString(actual));
	}

	public static void assertArrayEquals(final long[] expected, final long[] actual) {
		if (!Arrays.equals(expected, actual))
			throw new AssertionError("Expected: " + Arrays.toString(expected) + "; but was: " + Arrays.toString(actual));
	}

	public static void assertArrayEquals(final float[] expected, final float[] actual, final double delta) {
		if (!Arrays.equals(expected, actual))
			throw new AssertionError("Expected: " + Arrays.toString(expected) + "; but was: " + Arrays.toString(actual));
	}

	public static void assertArrayEquals(final double[] expected, final double[] actual, final double delta) {
		if (!Arrays.equals(expected, actual))
			throw new AssertionError("Expected: " + Arrays.toString(expected) + "; but was: " + Arrays.toString(actual));
	}
}
