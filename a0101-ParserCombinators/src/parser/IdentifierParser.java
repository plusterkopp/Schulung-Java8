package parser;

import scanner.Identifier;
import scanner.Scanner;
import scanner.Symbol;

public class IdentifierParser extends Parser<String> {
	public static final IdentifierParser identifier = new IdentifierParser();
	private IdentifierParser() {
	}
	protected boolean canParse(Symbol symbol) {
		return symbol.isIdentifier();
	}
	protected String doParse(Scanner s) {
		Identifier ident = s.current().asIdentifier();
		s.next();
		return ident.getValue();
	}
}
