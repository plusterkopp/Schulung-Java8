package utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Pipe<T> {

	public Pipe<T> getHead();
	public Pipe<T> getPrevious();
	
	public void forEach( Consumer<T> consumer);
	public Pipe<T> filter( Predicate<T> pred);
	public Pipe<T> map( Function<T, T> function);
	public Iterable<T> iterable();

}
