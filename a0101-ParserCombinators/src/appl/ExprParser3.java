package appl;

import parser.ForwardParser;
import parser.Parser;
import parser.RepReduceParser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Specials;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import static parser.NumberParser.number;

public class ExprParser3 {
	
	public static void main(String[] args) {
		final Map<String,Integer> map = new LinkedHashMap<>();
		map.put("42", 42);
		map.put("1 + 1", 2);
		map.put("1 + 2 * 3 + 4", 11);
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			final String text = entry.getKey();
			final Integer expectedResult = entry.getValue();
			final int result = ExprParser3.parse(text);
			System.out.println(result + " (expected: " + expectedResult + ")");
		}
	}
	
	private static final Specials specials = new Specials();
	
	private static final SpecialParser TIMES = new SpecialParser(specials.create("*"));
	private static final SpecialParser PLUS = new SpecialParser(specials.create("+"));

	private final Parser<Integer> expression;
	private Parser<Integer> additiveExpression;
	private final Parser<Integer> multiplictiveExpression;

	public ExprParser3() {
		
		this.multiplictiveExpression = new RepReduceParser<>(
				() -> number, 
				() -> TIMES,
				(v0, op, v1) -> v0 * v1);
	
		this.expression = new ForwardParser<>(
				() -> additiveExpression); 
		
		this.additiveExpression = new RepReduceParser<>(
				() -> multiplictiveExpression, 
				() -> PLUS,
				(v0, op, v1) -> v0 + v1);
	}
	
	public static int parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new ExprParser3().expression.parse(scanner);
	}
}
