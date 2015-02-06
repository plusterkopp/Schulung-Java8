package parser;

import java.util.function.Supplier;

import scanner.Scanner;
import scanner.Symbol;
import util.Lazy;

public class ForwardParser<T> extends Parser<T> {

	public static <T> ForwardParser<T> forward(Supplier<Parser<T>> parser) {
		return new ForwardParser<T>(parser);
	}
	
	private Lazy<Parser<T>> parser;
	public ForwardParser(Supplier<Parser<T>> parser) {
		this.parser = new Lazy<>(parser);
	}
	
	protected boolean canParse(Symbol symbol) {
		return this.parser.get().canParse(symbol);
	}

	protected T doParse(Scanner s) {
		return this.parser.get().doParse(s);
	}
}
