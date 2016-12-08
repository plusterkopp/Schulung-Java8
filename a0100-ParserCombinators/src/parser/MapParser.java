package parser;

import scanner.Scanner;
import scanner.Symbol;

import java.util.function.Function;

public class MapParser<I, O> extends Parser<O> {

	Parser<I> inner;
	
	private MapParser( Parser<I> inner, Function<I, O> mapper) {
		this.inner = inner;
		this.mapper = mapper;
	}

	Function<I, O>	mapper;
	
	@Override
	protected boolean canParse( Symbol symbol) {
		return inner.canParse( symbol);
	}

	@Override
	protected O doParse( Scanner s) {
		I iv = inner.doParse( s);
		return mapper.apply( iv);
	}

	public static<I, O> MapParser<I, O> map( Parser<I> inner, Function<I, O> mapper) {
		return new MapParser<>( inner, mapper);
	}

}
