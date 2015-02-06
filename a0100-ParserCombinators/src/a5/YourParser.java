package a5;

import java.io.StringReader;

import parser.NumberParser;
import parser.OrParser;
import parser.Parser;
import parser.RepSepParser;
import parser.Seq3Parser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

public class YourParser {
	
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
		
		final Parser<Integer> add = Seq3Parser.seq3(
				() -> NumberParser.number, 
				() -> SpecialParser.special( PLUS),
				() -> NumberParser.number, 
				(v0, s, v1) -> v0 + v1);
		
		return add.parse(scanner);
	}
}
