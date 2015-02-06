package appl;

public interface Worker {

	void work( int value);
	
	default Worker andThen( Worker other) {
		return new Worker() {
			@Override
			public void work( int value) {
				Worker.this.work( value);
				other.work( value);
			}};
	}
}
