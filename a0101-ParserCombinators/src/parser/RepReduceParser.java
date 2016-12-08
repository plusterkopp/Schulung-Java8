package parser;

import scanner.Scanner;
import scanner.Symbol;
import util.Func3;
import util.Lazy;

import java.util.function.Supplier;

public class RepReduceParser<T, S> extends Parser<T> {
	public static <T,S> RepReduceParser<T, S> repReduce(
			Supplier<Parser<T>> parser, Supplier<Parser<S>> sepParser, Func3<T, S, T, T> reducer) {
		return new RepReduceParser<>(parser, sepParser, reducer);
	}
	private final Lazy<Parser<T>> parser;
	private final Lazy<Parser<S>> sepParser;
	private final Func3<T, S, T, T> reducer;
	public RepReduceParser(Supplier<Parser<T>> parser, Supplier<Parser<S>> sepParser, Func3<T, S, T, T> reducer) {
		this.parser = new Lazy<>(parser);
		this.sepParser = new Lazy<>(sepParser);
		this.reducer= reducer;
	}
	protected boolean canParse(Symbol symbol) {
		return parser.get().canParse(symbol);
	}
	protected T doParse(Scanner s) {
		T value = parser.get().doParse(s);
		while(sepParser.get().canParse(s.current())) {
			S sep = sepParser.get().doParse(s);
			T v = parser.get().doParse(s);
			value = reducer.apply(value, sep, v);
		}
		return value;
	}
}
