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

public class ExprParser2 {
	
	public static void main(String[] args) {
		final Map<String,Integer> map = new LinkedHashMap<>();
		map.put("42", 42);
		map.put("1 + 1", 2);
		map.put("1 + 2 + 3 + 4", 10);
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			final String text = entry.getKey();
			final Integer expectedResult = entry.getValue();
			final int result = ExprParser2.parse(text);
			System.out.println(result + " (expected: " + expectedResult + ")");
		}
	}
	
	private static final Specials specials = new Specials();
	
	private static final SpecialParser PLUS = new SpecialParser(specials.create("+"));

	private final Parser<Integer> expression;
	private final Parser<Integer> additiveExpression;

	public ExprParser2() {
		
		this.additiveExpression = new RepReduceParser<>(
				() -> number, 
				() -> PLUS,
				(v0, op, v1) -> v0 + v1);
		this.expression = new ForwardParser<>(
				() -> additiveExpression); 

	}
	
	public static int parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new ExprParser2().expression.parse(scanner);
	}
}
