package parser;

import scanner.Scanner;
import scanner.Symbol;

public abstract class Parser<T> {
	public static int count;

	public Parser() {
		count++;
	}

	protected abstract boolean canParse( Symbol symbol);

	protected abstract T doParse( Scanner s);

	public T parse( Scanner s) {
		if ( ! canParse( s.current()))
			throw new RuntimeException( this + "cannot parse: " + s.current());
		return doParse( s);
	}
}
