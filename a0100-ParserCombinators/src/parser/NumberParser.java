package parser;

import scanner.Scanner;
import scanner.Symbol;

public class NumberParser extends Parser<Integer> {
	public static NumberParser number = new NumberParser();
	private NumberParser() {
	}
	protected boolean canParse(Symbol symbol) {
		return symbol.isLiteral() && symbol.asLiteral().isInt();
	}
	protected Integer doParse(Scanner s) {
		final int result = s.current().asLiteral().intValue();
		s.next();
		return result;
	}
}

