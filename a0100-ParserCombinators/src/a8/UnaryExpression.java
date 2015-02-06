package a8;

import java.util.function.UnaryOperator;

public class UnaryExpression extends Expression {
	private final UnaryOperator<Integer> op;
	private final Expression inner;
	public UnaryExpression(UnaryOperator<Integer> op, Expression inner) {
		this.op = op;
		this.inner = inner;
	}
	public int evaluate() {
		return op.apply(this.inner.evaluate());
	}
}
