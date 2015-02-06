package parser;

import scanner.Literal;
import scanner.Scanner;
import scanner.Symbol;

public class StringParser extends Parser<String> {
	public static StringParser string = new StringParser();
	private StringParser() {
	}
	protected boolean canParse(Symbol symbol) {
		return symbol.isLiteral() && symbol.asLiteral().isString();
	}
	protected String doParse(Scanner s) {
		Literal literal = s.current().asLiteral();
		s.next();
		return literal.stringValue();
	}
}

