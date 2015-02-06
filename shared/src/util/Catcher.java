package util;

public class Catcher<T, R> {
	
	@FunctionalInterface
	public interface Supplier<R> {
		public abstract R get() throws Throwable;
	}
	
	@FunctionalInterface
	public interface Runnable {
		public abstract void run() throws Throwable;
	}
	
	public static <R> R eval(Catcher.Supplier<R> supplier) {
		try {
			return supplier.get();
		}
		catch(Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	public static void exec(Catcher.Runnable runnable) {
		try {
			runnable.run();
		}
		catch(Throwable t) {
			throw new RuntimeException(t);
		}
	}
}
