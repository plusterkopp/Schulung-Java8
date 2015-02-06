package a3;

import org.junit.Assert;
import org.junit.Test;

public class TestExpressionParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 * 3 + 4");
		Assert.assertEquals(10, result);
		
	}
	@Test
	public void testAdditive() {
		final int result = new MyParser().parse(" 2 * (3 + 4) * 5");
		Assert.assertEquals(70, result);
	}
	@Test
	public void testAdditiveNegated() {
		final int result = new MyParser().parse(" 2 * -(3 + 4) * -5");
		Assert.assertEquals(70, result);
	}
}
