package parser;

import scanner.Scanner;
import scanner.Special;
import scanner.Symbol;

public class SpecialParser extends Parser<Symbol> {
	public static SpecialParser special(Special special) {
		return new SpecialParser(special);
	}
	public final Special special;
	private SpecialParser(Special special) {
		this.special = special;
	}
	protected boolean canParse(Symbol symbol) {
		return symbol == special;
	}
	protected Symbol doParse(Scanner s) {
		s.next();
		return special;
	}
}
