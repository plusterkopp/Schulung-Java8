package a2;

import org.junit.Assert;
import org.junit.Test;

public class TestAdditiveParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 * 3");
		Assert.assertEquals(6, result);
		
	}
	@Test
	public void testAdditive() {
		final int result = new MyParser().parse("  2 * 3 + 4 * 5");
		Assert.assertEquals(26, result);
	}
}
