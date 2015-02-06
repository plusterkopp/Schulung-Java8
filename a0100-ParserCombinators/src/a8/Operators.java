package a8;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Operators {
	public static final BinaryOperator<Integer> PLUS = (x, y) -> x + y; 
	public static final BinaryOperator<Integer> MINUS = (x, y) -> x - y; 
	public static final BinaryOperator<Integer> TIMES = (x, y) -> x * y; 
	public static final BinaryOperator<Integer> DIV = (x, y) -> x / y; 
	public static final UnaryOperator<Integer> UMINUS = x -> -x; 
}
