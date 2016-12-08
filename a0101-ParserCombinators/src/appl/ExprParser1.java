package appl;

import parser.Parser;
import scanner.Scanner;
import scanner.Specials;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import static parser.NumberParser.number;

public class ExprParser1 {
	
	public static void main(String[] args) {
		final Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1", 1);
		map.put("42", 42);
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			final String text = entry.getKey();
			final Integer expectedResult = entry.getValue();
			final int result = ExprParser1.parse(text);
			System.out.println(result + " (expected: " + expectedResult + ")");
		}
	}
	
	private static final Specials specials = new Specials();
	
	private final Parser<Integer> expression;

	public ExprParser1() {
		this.expression = number;
	}
	
	public static int parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new ExprParser1().expression.parse(scanner);
	}
}
