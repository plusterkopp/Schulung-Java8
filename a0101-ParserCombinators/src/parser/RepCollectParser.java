package parser;

import scanner.Scanner;
import scanner.Symbol;
import util.Lazy;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class RepCollectParser<T, E> extends Parser<T> {
	public static <T,E> RepCollectParser<T, E> repCollect(Supplier<Parser<E>> parser, Supplier<Parser<?>> sepParser, Supplier<T> init, BiConsumer<T, E> action) {
		return new RepCollectParser<>(parser, sepParser, init, action);
	}
	private final Lazy<Parser<E>> parser;
	private final Lazy<Parser<?>> sepParser;
	private final Supplier<T> init;
	private final BiConsumer<T, E> action;
	public RepCollectParser(Supplier<Parser<E>> parser, Supplier<Parser<?>> sepParser, Supplier<T> init, BiConsumer<T, E> action) {
		this.parser = new Lazy<>(parser);
		this.sepParser = new Lazy<>(sepParser);
		this.action = action;
		this.init = init;
	}
	protected boolean canParse(Symbol symbol) {
		return parser.get().canParse(symbol);
	}
	protected T doParse(Scanner s) {
		T result = init.get();
		E v = parser.get().doParse(s);
		action.accept(result, v);
		while(sepParser.get().canParse(s.current())) {
			sepParser.get().doParse(s);
			v = parser.get().doParse(s);
			action.accept(result, v);
		}
		return result;
	}
}
