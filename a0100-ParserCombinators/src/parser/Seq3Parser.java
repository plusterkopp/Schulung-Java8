package parser;

import scanner.Scanner;
import scanner.Symbol;
import util.Func3;

import java.util.function.Supplier;

public class Seq3Parser<T0, T1, T2, R> extends Parser<R> {
	public static <T0, T1, T2, R> Seq3Parser<T0, T1, T2, R> seq3(
			Supplier<Parser<T0>> sparser0, Supplier<Parser<T1>> sparser1, Supplier<Parser<T2>> sparser2, Func3<T0, T1, T2, R> reducer) {
		return new Seq3Parser<>(sparser0, sparser1, sparser2, reducer);
	}
	private final Supplier<Parser<T0>> sparser0;
	private final Supplier<Parser<T1>> sparser1;
	private final Supplier<Parser<T2>> sparser2;
	private final Func3<T0, T1, T2, R> reducer;

	private Seq3Parser( 
			Supplier<Parser<T0>> sparser0, Supplier<Parser<T1>> sparser1, Supplier<Parser<T2>> sparser2, 
			Func3<T0, T1, T2, R> reducer) {
		this.sparser0 = sparser0;
		this.sparser1 = sparser1;
		this.sparser2 = sparser2;
		this.reducer = reducer;
	}
	protected boolean canParse(Symbol symbol) {
		return sparser0.get().canParse(symbol);
	}
	protected R doParse(Scanner s) {
		T0	v0 = sparser0.get().parse( s);
		T1	v1 = sparser1.get().parse( s);
		T2	v2 = sparser2.get().parse( s);
		R value = reducer.apply(v0, v1, v2);
		return value;
	}
}
