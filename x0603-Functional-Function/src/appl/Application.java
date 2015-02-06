package appl;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class Application {
	public static void main(String[] args) {
		{
			Function<String,Integer> f = new Function<String,Integer>() {
				public Integer apply(String v) {
					return Integer.parseInt(v);
				}
			};
			int v = f.apply("42");
			System.out.println(v);
		}
		{
			Function<String, Integer> f = v -> Integer.parseInt(v);
			int v = f.apply("42");
			System.out.println(v);
		}
		
		{
			Supplier<String> supplier = () -> "42";
			Function<String,Integer> function = v -> Integer.parseInt(v);
			Consumer<Integer> consumer = v -> System.out.println(v);
			consumer.accept(function.apply(supplier.get()));
		}
		
		// ---------------------------------------------------------------
		{
			Supplier<String> supplier = () -> "42";
			Function<String,Integer> function = v -> Integer.parseInt(v);
			Consumer<Integer> consumer = v -> System.out.println(v);
			pipe(supplier, function, consumer);
		}
		{
			Supplier<C> supplier = () -> new C(1, 2, 3);
			Function<C,B> function = (c) -> new B(c.x + 1, c.y + 1);
			Consumer<A> consumer = a -> System.out.println(a.x);
			pipe(supplier, function, consumer);
		}

		{
			Function<Integer,Integer> f1 = x -> x + 1;
			Function<Integer,Integer> f2 = x -> 2 * x;
			Function<Integer,Integer> f3 = x -> x * x;
			int v = f1.andThen(f2).andThen(f3).apply(3);
			System.out.println(v); // -> 64
		}
		{
			Function<Integer,Integer> f1 = x -> x + 1;
			Function<Integer,Integer> f2 = x -> 2 * x;
			Function<Integer,Integer> f3 = x -> x * x;
			int v = f1.compose(f2).compose(f3).apply(3);
			System.out.println(v); // -> 19
		}
		{
			Function<Integer,Integer> f1 = x -> x + 1;
			Function<Integer,Integer> f2 = x -> 2 * x;
			Function<Integer,Integer> f3 = x -> x * x;
			int v = f1.andThen(f2).compose(f3).apply(3);
			System.out.println(v); // -> 20
		}
		{
			Function<Integer,Integer> f = Function.identity();
			int v = f.apply(42);
			System.out.println(v); // -> 42
		}

		{
			IntFunction<Integer> f = x -> 2 * x;
			int v = f.apply(42);
			System.out.println(v); // -> 84
		}
		{
			IntFunction<Double> f = x -> Math.sqrt(x);
			double v = f.apply(2);
			System.out.println(v); // -> 1.41...
		}
		
		// ----------------------------------------------------------------
		
		{
			BiFunction<Integer,Integer,Double> f = new BiFunction<Integer,Integer,Double>() {
				public Double apply(Integer x, Integer y) {
					return Math.sqrt(x * x + y * y);
				}
			};
			double d = f.apply(3, 4);
			System.out.println(d);
		}
		{
			BiFunction<Integer, Integer, Double> f = (x, y) -> Math.sqrt(x * x + y * y);
			double d = f.apply(3, 4);
			System.out.println(d);
		}
		{
			BiFunction<A, B, C> f = (a, b) -> new C(a.x, b.x, b.y);
			C c = f.apply(new A(1), new B(2, 3));
			System.out.println(c.x + " " + c.y + " " + c.z);
		}
		
	}
	
	static <S,T> void pipe(Supplier<? extends S> s, Function<S,T> f, Consumer<? super T> c) {
		c.accept(f.apply(s.get()));
	}
}