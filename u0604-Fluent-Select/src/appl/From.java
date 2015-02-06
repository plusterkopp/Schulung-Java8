package appl;

import java.util.function.Function;

public class From<T> {
	public final Class<T> tableClass;
	public From(Class<T> tableClass) {
		this.tableClass = tableClass;
	}
	@SafeVarargs
	public final Select<T> select(Function<T,?>... columns) {
		return new Select<T>(this, columns);
	}
}
