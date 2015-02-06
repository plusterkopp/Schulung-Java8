package parser;

import scanner.Scanner;
import scanner.Symbol;

public class OrParser<T> extends Parser<T> {
	public static <T> OrParser<T> or(Parser<? extends T> p0, Parser<? extends T> p1) {
		return new OrParser<T>(p0, p1);
	}
	private final Parser<? extends T> p0;
	private final Parser<? extends T> p1;
	public OrParser(Parser<? extends T> p0, Parser<? extends T> p1) {
		this.p0 = p0;
		this.p1 = p1;
	}
	protected boolean canParse(Symbol symbol) {
		return p0.canParse(symbol) || p1.canParse(symbol);
	}
	protected T doParse(Scanner s) {
		final T value;
		if (p0.canParse(s.current()))
			value = p0.doParse(s);
		else
			value = p1.parse(s);
		return value;
	}
}

