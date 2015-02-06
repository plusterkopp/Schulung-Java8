package a8;

public class NumberExpression extends Expression {

	private final int value;
	public NumberExpression(int value) {
		this.value = value;
	}
	public int evaluate() {
		return this.value;
	}
}
