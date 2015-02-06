package parser;

import scanner.Scanner;
import scanner.Special;
import scanner.Symbol;

public class SpecialParser extends Parser<Special> {
	public final Special special;
	public SpecialParser(Special special) {
		this.special = special;
	}
	protected boolean canParse(Symbol symbol) {
		return symbol == special;
	}
	protected Special doParse(Scanner s) {
		s.readSpecial(special);
		return special;
	}
}
