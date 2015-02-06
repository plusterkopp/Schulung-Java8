package appl;

import java.util.function.Function;
import java.util.function.Predicate;

public class Select<T> {
	public final From<T> from;
	public final Function<T,?>[] columns;
	
	@SafeVarargs
	public Select(From<T> from, Function<T,?>...columns) {
		this.from = from;
		this.columns = columns;
	}
	public Where<T> where(Predicate<T> predicate) {
		return new Where<T>(this, predicate);
	}
}
