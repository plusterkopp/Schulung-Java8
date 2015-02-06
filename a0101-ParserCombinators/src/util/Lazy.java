package util;

import java.util.function.Supplier;

public class Lazy<T> {
	private final Supplier<T> supplier;
	private T object;
	public Lazy(Supplier<T> supplier) {
		this.supplier = supplier;
	}
	public T get() {
		if (object == null)
			object = supplier.get();
		return object;
	}
}
