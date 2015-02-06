package appl;

public class Query {
	public static <T> From<T> from(Class<T> tableClass) {
		return new From<T>(tableClass);
	}
}
