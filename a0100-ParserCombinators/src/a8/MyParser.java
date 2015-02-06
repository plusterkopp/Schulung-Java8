package a8;

import java.io.StringReader;

import parser.MapParser;
import parser.NumberParser;
import parser.Optional0Parser;
import parser.OrParser;
import parser.Parser;
import parser.RepSepParser;
import parser.Seq3Parser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

public class MyParser {
	
	final static Specials specials = new Specials();
	final static Special TIMES = specials.create("*");
	final static Special DIV = specials.create("/");
	final static Special PLUS = specials.create("+");
	final static Special MINUS = specials.create("-");
	final static Special OPEN = specials.create("(");
	final static Special CLOSE = specials.create(")");
	
	public Expression parse(String s) {
		final Scanner scanner = new Scanner(specials, new StringReader(s));
		scanner.next();
		final Expression result = parse(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected at: " + scanner.current());
		return result;
	}
	
	Parser<Expression> additive;
	Parser<Expression> simpleNoMinus;
	
	private Expression parse(Scanner scanner) {
		
		final Parser<Expression> unarySimple = Optional0Parser.o0p(
				() -> SpecialParser.special( MINUS),
				() -> simpleNoMinus,
				( oFound, v) -> oFound==null ? v : new UnaryExpression( Operators.UMINUS, v));
		
		final Parser<Expression> enclosedExpression = Seq3Parser.seq3(
				() -> SpecialParser.special( OPEN),
				() -> additive,
				() -> SpecialParser.special( CLOSE),
				( o, v, c) -> v);
		
		simpleNoMinus = OrParser.or( 
				MapParser.map( NumberParser.number, v -> new NumberExpression( v)), 
				enclosedExpression);
		
		final Parser<Expression> mulitplicative = RepSepParser.repSep(
				unarySimple, 
				OrParser.or(SpecialParser.special(TIMES), SpecialParser.special(DIV)), 
				(v0, s, v1) -> 
				s == TIMES? 
					new BinaryExpression( Operators.TIMES, v0, v1) : 
					new BinaryExpression( Operators.DIV, v0, v1));

		additive = RepSepParser.repSep(
				mulitplicative, 
				OrParser.or(SpecialParser.special(PLUS), SpecialParser.special(MINUS)), 
				(v0, s, v1) -> 
				s == PLUS ? 
					new BinaryExpression( Operators.PLUS, v0, v1)  : 
					new BinaryExpression( Operators.MINUS, v0, v1));

		return additive.parse(scanner);
	}
}
