package a4;

import java.io.StringReader;

import parser.NumberParser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

public class MyParser {
	
	final static Specials specials = new Specials();
	final static Special PLUS = specials.create("+");
	
	public int parse(String s) {
		final Scanner scanner = new Scanner(specials, new StringReader(s));
		scanner.next();
		final int result = parse(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected");
		return result;
	}
	
	private int parse(Scanner scanner) {
		int v0 = NumberParser.number.parse(scanner);
		SpecialParser.special(PLUS).parse(scanner);
		int v1 = NumberParser.number.parse(scanner);
		return v0 + v1;
	}
}
