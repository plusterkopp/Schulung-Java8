package parser;

import scanner.Scanner;
import scanner.Symbol;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Optional0Parser<O, T, R> extends Parser<R> {

	public static<O, T, R> Optional0Parser<O, T, R> o0p( 
			Supplier<Parser<O>> optional, Supplier<Parser<T>> sparser, BiFunction<O, T, R> func) {
		
		return new Optional0Parser<O, T, R>( optional, sparser, func);
	}
	
	private	Supplier<Parser<O>>	optional;
	private	Supplier<Parser<T>>	parser;
	private	BiFunction<O, T, R>	func;

	private Optional0Parser( 
			Supplier<Parser<O>> optional, Supplier<Parser<T>> parser, BiFunction<O, T, R> func) {
		this.optional = optional;
		this.parser = parser;
		this.func = func;
	}

	@Override
	protected boolean canParse( Symbol symbol) {
		return true; // optional.get().canParse( symbol) || parser.get().canParse( symbol);
	}

	@Override
	protected R doParse( Scanner s) {
		Symbol curr = s.current();
		O	ov = null;
		if ( optional.get().canParse( curr)) {
			ov = optional.get().parse( s);
		}
		T	v = parser.get().parse( s);
		return func.apply( ov, v);
	}
}
