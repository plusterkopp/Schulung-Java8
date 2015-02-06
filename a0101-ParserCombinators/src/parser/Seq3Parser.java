package parser;

import java.util.function.Supplier;

import scanner.Scanner;
import scanner.Symbol;
import util.Func3;
import util.Lazy;

public class Seq3Parser<T0, T1, T2, R> extends Parser<R> {
	
	public static <T0, T1, T2, R> Seq3Parser<T0, T1, T2, R> seq3(Supplier<Parser<T0>> p0, Supplier<Parser<T1>> p1, Supplier<Parser<T2>> p2, Func3<T0, T1, T2, R> reducer) {
		return new Seq3Parser<>(p0, p1, p2, reducer);
	}
	private final Lazy<Parser<T0>> p0;
	private final Lazy<Parser<T1>> p1;
	private final Lazy<Parser<T2>> p2;
	private final Func3<T0, T1, T2, R> reducer;
	public Seq3Parser(Supplier<Parser<T0>> p0, Supplier<Parser<T1>> p1, Supplier<Parser<T2>> p2, Func3<T0, T1, T2, R> reducer) {
		this.p0 = new Lazy<>(p0);
		this.p1 = new Lazy<>(p1);
		this.p2 = new Lazy<>(p2);
		this.reducer= reducer;
	}
	protected boolean canParse(Symbol symbol) {
		return p0.get().canParse(symbol);
	}
	protected R doParse(Scanner s) {
		T0 v0 = p0.get().doParse(s);
		T1 v1 = p1.get().doParse(s);
		T2 v2 = p2.get().doParse(s);
		return reducer.apply(v0, v1, v2);
	}
}

