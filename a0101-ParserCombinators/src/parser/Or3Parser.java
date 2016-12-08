package parser;

import scanner.Scanner;
import scanner.Symbol;
import util.Func1;
import util.Lazy;

import java.util.function.Supplier;

public class Or3Parser<T> extends Parser<T> {
	public static <T> Or3Parser<T> or3(Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1, Supplier<Parser<? extends T>> p2, Func1<T, T> reducer) {
		return new Or3Parser<>(p0, p1, p2, reducer);
	}
	public static <T> Or3Parser<T> or3(Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1, Supplier<Parser<? extends T>> p2) {
		return new Or3Parser<T>(p0, p1, p2);
	}
	private final Lazy<Parser<? extends T>> p0;
	private final Lazy<Parser<? extends T>> p1;
	private final Lazy<Parser<? extends T>> p2;
	private final Func1<T, T> reducer;
	public Or3Parser(Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1, Supplier<Parser<? extends T>> p2, Func1<T, T> reducer) {
		this.p0 = new Lazy<>(p0);
		this.p1 = new Lazy<>(p1);
		this.p2 = new Lazy<>(p2);
		this.reducer= reducer;
	}
	public Or3Parser(Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1, Supplier<Parser<? extends T>> p2) {
		this(p0, p1, p2, null);
	}
	protected boolean canParse(Symbol symbol) {
		return p0.get().canParse(symbol) || p1.get().canParse(symbol) || p2.get().canParse(symbol);
	}
	protected T doParse(Scanner s) {
		final T value;
		if (p0.get().canParse(s.current()))
			value = p0.get().doParse(s);
		else if (p1.get().canParse(s.current()))
			value = p1.get().doParse(s);
		else
			value = p2.get().doParse(s);
		return reducer != null ? reducer.apply(value) : value;
	}
}

