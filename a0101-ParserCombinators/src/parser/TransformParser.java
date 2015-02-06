package parser;

import java.util.function.Supplier;

import scanner.Scanner;
import scanner.Symbol;
import util.Func1;
import util.Lazy;

public class TransformParser<T,R> extends Parser<R> {

	public static <T,R> TransformParser<T,R> transform(Supplier<Parser<T>> parser, Func1<T,R> transformer) {
		return new TransformParser<T,R>(parser, transformer);
	}
	
	private final Lazy<Parser<T>> parser;
	private final Func1<T,R> transformer;
	
	public TransformParser(Supplier<Parser<T>> parser, Func1<T,R> transformer) {
		this.parser = new Lazy<>(parser);
		this.transformer = transformer;
	}
	
	protected boolean canParse(Symbol symbol) {
		return this.parser.get().canParse(symbol);
	}

	protected R doParse(Scanner s) {
		return this.transformer.apply(this.parser.get().doParse(s));
	}
}
