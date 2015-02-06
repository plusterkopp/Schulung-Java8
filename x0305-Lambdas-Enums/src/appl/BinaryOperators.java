package appl;

import java.util.function.BiFunction;

public enum BinaryOperators implements BiFunction<Integer, Integer, Integer> {
	PLUS("Plus", (v1, v2) -> v1 + v2),
	MINUS("Minus", (v1, v2) -> v1 - v2),
	TIMES("Times", (v1, v2) -> v1 * v2),
	DIV("Div", (v1, v2) -> v1 / v2)
	;
	private final String displayName;
	private final BiFunction<Integer, Integer, Integer> op;
	private BinaryOperators(String displayName, BiFunction<Integer, Integer, Integer> op) {
		this.displayName = displayName;
		this.op = op;
	}
	public Integer apply(Integer v1, Integer v2) {
		return this.op.apply(v1, v2);
	}
	public String displayName() {
		return this.displayName;
	}
}
