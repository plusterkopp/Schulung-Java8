package parser;

import java.util.function.Supplier;

import scanner.Scanner;
import scanner.Symbol;
import util.Func1;
import util.Lazy;

public class Or2Parser<T> extends Parser<T> {
	public static <T> Or2Parser<T> or2(
			Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1, Func1<T, T> reducer) {
		return new Or2Parser<>(p0, p1, reducer);
	}
	public static <T> Or2Parser<T> or2(
			Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1) {
		return new Or2Parser<T>(p0, p1);
	}
	private final Lazy<Parser<? extends T>> p0;
	private final Lazy<Parser<? extends T>> p1;
	private final Func1<T, T> reducer;
	public Or2Parser(Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1, Func1<T, T> reducer) {
		this.p0 = new Lazy<>(p0);
		this.p1 = new Lazy<>(p1);
		this.reducer= reducer;
	}
	public Or2Parser(Supplier<Parser<? extends T>> p0, Supplier<Parser<? extends T>> p1) {
		this(p0, p1, null);
	}
	protected boolean canParse(Symbol symbol) {
		return p0.get().canParse(symbol) || p1.get().canParse(symbol);
	}
	protected T doParse(Scanner s) {
		final T value;
		if (p0.get().canParse(s.current()))
			value = p0.get().doParse(s);
		else
			value = p1.get().doParse(s);
		return reducer != null ? reducer.apply(value) : value;
	}
}

