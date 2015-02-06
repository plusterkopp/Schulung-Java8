package a8;

import java.util.function.BinaryOperator;

public class BinaryExpression extends Expression {
	private final BinaryOperator<Integer> op;
	private final Expression left;
	private final Expression right;
	public BinaryExpression(BinaryOperator<Integer> op, Expression left, Expression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public int evaluate() {
		return op.apply(this.left.evaluate(), this.right.evaluate());
	}
}
