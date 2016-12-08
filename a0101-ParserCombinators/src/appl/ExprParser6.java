package appl;

import parser.Parser;
import parser.RepReduceParser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import static parser.ForwardParser.forward;
import static parser.NumberParser.number;
import static parser.Or2Parser.or2;
import static parser.Seq3Parser.seq3;

public class ExprParser6 {
	
	public static void main(String[] args) {
		final Map<String,Integer> map = new LinkedHashMap<>();
		map.put("42", 42);
		map.put("1 + 1", 2);
		map.put("(10 - (5 + 3)) * 3", 6);
		map.put("(1 + 2) * (3 + 4) * 5 * 6", 630);
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			final String text = entry.getKey();
			final Integer expectedResult = entry.getValue();
			final int result = ExprParser6.parse(text);
			System.out.println(result + " (expected: " + expectedResult + ")");
		}
	}
	
	private static final Specials specials = new Specials();
	
	private static final SpecialParser TIMES = new SpecialParser(specials.create("*"));
	private static final SpecialParser DIV = new SpecialParser(specials.create("/"));
	private static final SpecialParser PLUS = new SpecialParser(specials.create("+"));
	private static final SpecialParser MINUS = new SpecialParser(specials.create("-"));
	private static final SpecialParser OPEN = new SpecialParser(specials.create("("));
	private static final SpecialParser CLOSE = new SpecialParser(specials.create(")"));

	private Parser<Integer> expression;
	private Parser<Integer> additiveExpression;
	private Parser<Integer> multiplictiveExpression;
	private Parser<Integer> simpleExpression;
	private Parser<Integer> enclosedExpression;

	public ExprParser6() {

		this.expression = forward(
				() -> additiveExpression);
		
		this.additiveExpression = new RepReduceParser<Integer, Special>(
				() -> multiplictiveExpression, 
				() -> or2(() -> PLUS, () -> MINUS),
				(v0, op, v1) -> op == PLUS.special ? v0 + v1 : v0 - v1);
		
		this.multiplictiveExpression = new RepReduceParser<Integer, Special>(
				() -> simpleExpression, 
				() -> or2(() -> TIMES, () -> DIV),
				(v0, op, v1) -> op == TIMES.special ? v0 * v1 : v0 / v1);
		
		this.simpleExpression = or2(
				() -> number,
				() -> enclosedExpression);

		this.enclosedExpression = seq3(
				() -> OPEN,
				() -> expression,
				() -> CLOSE,
				(o, e, c) -> e);
	}
	
	public static int parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new ExprParser6().expression.parse(scanner);
	}
}
