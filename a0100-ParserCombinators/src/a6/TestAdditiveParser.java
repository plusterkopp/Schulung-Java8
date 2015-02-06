package a6;

import org.junit.Assert;
import org.junit.Test;

public class TestAdditiveParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 * 3 + 4 * 5 ");
		Assert.assertEquals(26, result);
		
	}
}
