package appl;

public interface Worker {

	void work( int value);

	default Worker andThen( Worker other) {
		return value -> { 
			work( value);
			other.work( value);
		};
	}
}
