package a5;

import parser.*;
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
		final int result = parse(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected");
		return result;
	}
	
	private int parse(Scanner scanner) {
		
		final Parser<Integer> multiplicative = RepSepParser.repSep(
				NumberParser.number, 
				OrParser.or( SpecialParser.special(TIMES), SpecialParser.special(DIV)), 
				(v0, s, v1) -> s == TIMES? v0 * v1 : v0 / v1);
		
		return multiplicative.parse(scanner);
	}
}
