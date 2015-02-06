package scanner;

public class Special extends AbstractSymbol {

	private final String text;
	
	public Special(String text) {
		this.text = text;
	}
	
	public String getAsText() {
		return this.text;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
