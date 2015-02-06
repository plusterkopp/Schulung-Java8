package appl;

@FunctionalInterface
public interface Handler<T> {
	public abstract void handle(T value);
}
