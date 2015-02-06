package parser;

import java.util.function.Supplier;

import scanner.Scanner;
import scanner.Symbol;
import util.Func2;
import util.Lazy;

public class Seq2Parser<T0, T1, R> extends Parser<R> {
	private final Lazy<Parser<T0>> p0;
	private final Lazy<Parser<T1>> p1;
	private final Func2<T0, T1, R> reducer;
	public Seq2Parser(Supplier<Parser<T0>> p0, Supplier<Parser<T1>> p1, Func2<T0, T1, R> reducer) {
		this.p0 = new Lazy<>(p0);
		this.p1 = new Lazy<>(p1);
		this.reducer= reducer;
	}
	protected boolean canParse(Symbol symbol) {
		return p0.get().canParse(symbol);
	}
	protected R doParse(Scanner s) {
		T0 v0 = p0.get().doParse(s);
		T1 v1 = p1.get().doParse(s);
		return reducer.apply(v0, v1);
	}
}

