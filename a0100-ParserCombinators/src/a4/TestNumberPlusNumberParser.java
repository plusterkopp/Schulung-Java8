package a4;

import org.junit.Assert;
import org.junit.Test;

public class TestNumberPlusNumberParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 + 3 ");
		Assert.assertEquals(5, result);
		
	}
	@Test(expected = RuntimeException.class)
	public void testException() {
		final int result = new MyParser().parse(" 2 3 ");
		Assert.assertEquals(5, result);
		
	}
}
