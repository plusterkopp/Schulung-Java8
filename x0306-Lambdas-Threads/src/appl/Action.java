package appl;

public interface Action {
	public abstract void execThrow() throws Throwable;
	static void exec(Action action) {
		try {
			action.execThrow();
		}
		catch(Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
