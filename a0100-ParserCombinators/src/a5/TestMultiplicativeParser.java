package a5;

import org.junit.Assert;
import org.junit.Test;

public class TestMultiplicativeParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 * 3 * 4 / 12");
		Assert.assertEquals(2, result);
		
	}
	@Test
	public void testSeq3() {
		final int result = new YourParser().parse(" 2 + 4");
		Assert.assertEquals(6, result);
		
	}
}
