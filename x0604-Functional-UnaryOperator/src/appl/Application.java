package appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class Application {
	public static void main(String[] args) {
		{
			UnaryOperator<Integer> op = new UnaryOperator<Integer>() {
				public Integer apply(Integer v) {
					return -v;
				}
			};
			System.out.println(op.apply(42));
		}
		{
			UnaryOperator<Integer> op = x -> -x;
			System.out.println(op.apply(42));
		}
		{
			UnaryOperator<Integer> op = UnaryOperator.identity();
			System.out.println(op.apply(42));
		}
		{
			List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30));
			list.replaceAll(x -> x * 10);
			list.forEach(x -> System.out.println(x)); // -> 100 200 300
		}
	}		
}