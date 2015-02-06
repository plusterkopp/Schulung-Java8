package a1;

import org.junit.Assert;
import org.junit.Test;

public class TestMultiplicativeParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 ");
		Assert.assertEquals(2, result);
		
	}
	@Test
	public void testMulti() {
		final int result = new MyParser().parse(" 2 * 30 / 4");
		Assert.assertEquals(15, result);
		
	}
	
	@Test(expected = RuntimeException.class)
	public void testIllegal() {
		new MyParser().parse(" 2 * 30.2 / 4");
	}

	@Test(expected = RuntimeException.class)
	public void testNotEof() {
		new MyParser().parse(" 2 * 30 / 4  777");
	}
}
