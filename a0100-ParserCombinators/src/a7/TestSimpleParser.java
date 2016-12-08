package a7;

import org.junit.Assert;
import org.junit.Test;
import parser.Parser;

public class TestSimpleParser {
	@Test
	public void testSimple() {
		final int result = new MyParser().parse(" 2 * (3 + 4) * -5 ");
		Assert.assertEquals( -70, result);
		System.out.println( "parsers: " + Parser.count);
	}
}
