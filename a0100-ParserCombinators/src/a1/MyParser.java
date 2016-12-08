package a1;

import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

import java.io.StringReader;

public class MyParser {
	
	final static Specials specials = new Specials();
	final static Special TIMES = specials.create("*");
	final static Special DIV = specials.create("/");
	
	public int parse(String s) {
		final Scanner scanner = new Scanner(specials, new StringReader(s));
		scanner.next();
		final int result = this.parseMultiplicative(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected");
		return result;
	}
	
	public int parseMultiplicative(Scanner scanner) {
		int result = this.parseNumber(scanner);
		while (scanner.current() == TIMES || scanner.current() == DIV) {
			final boolean times = scanner.current() == TIMES;
			scanner.next();
			final int value = this.parseNumber(scanner);
			if (times)
				result *= value;
			else
				result /= value;
		}
		return result;
	}
	
	private int parseNumber(Scanner scanner) {
		if (! scanner.current().isLiteral()) 
			throw new RuntimeException("number expected");
		if (! scanner.current().asLiteral().isInt())
			throw new RuntimeException("number expected");
		final int result = scanner.current().asLiteral().intValue();
		scanner.next();
		return result;
	}
}
