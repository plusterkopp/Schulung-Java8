package parser;

import scanner.Scanner;
import scanner.Symbol;
import util.Func3;

public class RepSepParser<T, S> extends Parser<T> {
	public static <T,S> RepSepParser<T, S> repSep(Parser<T> parser, Parser<S> sepParser, Func3<T, S, T, T> reducer) {
		return new RepSepParser<>(parser, sepParser, reducer);
	}
	private final Parser<T> parser;
	private final Parser<S> sepParser;
	private final Func3<T, S, T, T> reducer;
	public RepSepParser(Parser<T> parser, Parser<S> sepParser, Func3<T, S, T, T> reducer) {
		this.parser = parser;
		this.sepParser = sepParser;
		this.reducer= reducer;
	}
	protected boolean canParse(Symbol symbol) {
		return parser.canParse(symbol);
	}
	protected T doParse(Scanner s) {
		T value = parser.doParse(s);
		while(sepParser.canParse(s.current())) {
			S sep = sepParser.doParse(s);
			T v = parser.parse(s);
			value = reducer.apply(value, sep, v);
		}
		return value;
	}
}
