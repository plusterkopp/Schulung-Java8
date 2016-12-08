package a7;

import parser.*;
import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

import java.io.StringReader;

public class MyParser {
	
	final static Specials specials = new Specials();
	final static Special TIMES = specials.create("*");
	final static Special DIV = specials.create("/");
	final static Special PLUS = specials.create("+");
	final static Special MINUS = specials.create("-");
	final static Special OPEN = specials.create("(");
	final static Special CLOSE = specials.create(")");
	
	public int parse(String s) {
		final Scanner scanner = new Scanner(specials, new StringReader(s));
		scanner.next();
		final int result = parse(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected at: " + scanner.current());
		return result;
	}
	
	Parser<Integer> additive;
	Parser<Integer> simpleNoMinus;
	
	private int parse(Scanner scanner) {
		
		final Parser<Integer> unarySimple = Optional0Parser.o0p(
				() -> SpecialParser.special( MINUS),
				() -> simpleNoMinus,
				( oFound, v) -> oFound==null ? v : -v);
		
		final Parser<Integer> enclosedExpression = Seq3Parser.seq3(
				() -> SpecialParser.special( OPEN),
				() -> additive,
				() -> SpecialParser.special( CLOSE),
				( o, v, c) -> v);
		
		simpleNoMinus = OrParser.or( NumberParser.number, enclosedExpression);
		
		final Parser<Integer> mulitplicative = RepSepParser.repSep(
				unarySimple, 
				OrParser.or(SpecialParser.special(TIMES), SpecialParser.special(DIV)), 
				(v0, s, v1) -> s == TIMES? v0 * v1 : v0 / v1);

		additive = RepSepParser.repSep(
				mulitplicative, 
				OrParser.or(SpecialParser.special(PLUS), SpecialParser.special(MINUS)), 
				(v0, s, v1) -> s == PLUS? v0 + v1 : v0 - v1);

		return additive.parse(scanner);
	}
}
