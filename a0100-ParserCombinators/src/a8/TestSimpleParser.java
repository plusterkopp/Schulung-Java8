package a8;

import org.junit.Assert;
import org.junit.Test;
import parser.Parser;

public class TestSimpleParser {
	@Test
	public void testSimple() {
		final Expression result = new MyParser().parse(" 2 * (3 + 4) * -5 ");
		Assert.assertEquals( -70, result.evaluate());
		System.out.println( "parsers: " + Parser.count);
	}
}
