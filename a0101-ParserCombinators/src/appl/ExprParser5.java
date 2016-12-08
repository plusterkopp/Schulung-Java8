package appl;

import parser.*;
import scanner.Scanner;
import scanner.Specials;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import static parser.NumberParser.number;

public class ExprParser5 {
	
	public static void main(String[] args) {
		final Map<String,Integer> map = new LinkedHashMap<>();
		map.put("42", 42);
		map.put("1 + 1", 2);
		map.put("(10 - (5 + 3)) * 3", 6);
		map.put("(1 + 2) * (3 + 4) * 5 * 6", 630);
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			final String text = entry.getKey();
			final Integer expectedResult = entry.getValue();
			final int result = ExprParser5.parse(text);
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

	private final Parser<Integer> expression;
	private Parser<Integer> additiveExpression;
	private Parser<Integer> multiplictiveExpression;
	private Parser<Integer> simpleExpression;
	private Parser<Integer> enclosedExpression;

	public ExprParser5() {
	
		this.expression = new ForwardParser<>(
				() -> additiveExpression);

		this.additiveExpression = new RepReduceParser<>(
				() -> multiplictiveExpression, 
				() -> new Or2Parser<>(() -> PLUS, () -> MINUS),
				(v0, op, v1) -> op == PLUS.special ? v0 + v1 : v0 - v1);
		
		this.multiplictiveExpression = new RepReduceParser<>(
				() -> simpleExpression, 
				() -> new Or2Parser<>(() -> TIMES, () -> DIV),
				(v0, op, v1) -> op == TIMES.special ? v0 * v1 : v0 / v1);
		
		this.simpleExpression = new Or2Parser<>(
				() -> number,
				() -> enclosedExpression);
		
		this.enclosedExpression = new Seq3Parser<>(
				() -> OPEN,
				() -> expression,
				() -> CLOSE,
				(o, e, c) -> e);
	}
	
	public static int parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new ExprParser5().expression.parse(scanner);
	}
}
