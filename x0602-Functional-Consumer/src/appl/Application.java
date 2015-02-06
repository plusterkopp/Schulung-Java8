package appl;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;


@FunctionalInterface
interface Foo<T> {

    void accept(T t);

    default Foo<T> andThen1(Foo<? super T> after) {
        return (T t) -> { accept(t); after.accept(t); };
    }
    
    default Foo<T> andThen2(Foo<? super T> after) {
        return new Foo<T>() {
        	public void accept(T t) { 
        		Foo.this.accept(t); after.accept(t); 
        	}
        };
    }
}


@SuppressWarnings("unused")
public class Application {
	public static void main(String[] args) {
		{
			Consumer<Integer> c = new Consumer<Integer>() {
				public void accept(Integer v) {
					System.out.println(v);
				}
			};
			c.accept(42);
		}
		{
			Consumer<Integer> c = v -> System.out.println(v);
			c.accept(42);
		}
		
		{
			Supplier<Integer> supplier = () -> 42;
			Consumer<Integer> consumer = v -> System.out.println(v);
			consumer.accept(supplier.get());
		}
		
		// ---------------------------------------------------------------
		{
			Supplier<Integer> supplier = () -> 42;
			Consumer<Integer> consumer = v -> System.out.println(v);
			pipe(supplier, consumer);
		}
		// ---------------------------------------------------------------
		{
			Supplier<C> supplier = () -> new C(1, 2, 3);
			Consumer<C> consumer = c -> System.out.println(c.x + c.y + c.z);
			pipe(supplier, consumer);
		}
		{
			Supplier<C> supplier = () -> new C(1, 2, 3);
			Consumer<A> consumer = a -> System.out.println(a.x);
			pipe(supplier, consumer);
		}
		{
			Supplier<C> supplier = () -> new C(1, 2, 3);
			Consumer<B> consumer = b -> System.out.println(b.x + b.y);
			pipe(supplier, consumer);
		}
		{
			Supplier<B> supplier = () -> new B(1, 2);
			Consumer<A> consumer = a -> System.out.println(a.x);
			pipe(supplier, consumer);
		}

		{
			Supplier<B> supplier = () -> new B(1, 2);
			Consumer<C> consumer = c -> System.out.println(c.x + c.y + c.z);
			//pipe(supplier, consumer);
		}

		{
			Consumer<Integer> c1 = v -> System.out.println("c1: " + v);
			Consumer<Integer> c2 = v -> System.out.println("c2: " + v);
			Consumer<Integer> c3 = v -> System.out.println("c3: " + v);
			c1.andThen(c2).andThen(c3).accept(42);
		}
		{
			Consumer<C> c1 = c -> System.out.println("c1: " + (c.x + c.y + c.z));
			Consumer<B> c2 = b -> System.out.println("c2: " + (b.x + b.y));
			Consumer<A> c3 = a -> System.out.println("c3: " + (a.x));
			c1.andThen(c2).andThen(c3).accept(new C(1, 2, 3));
		}
		
		{
			List<Integer> list = Arrays.asList(10, 20, 30);
			list.forEach(element -> System.out.println(element));
		}

		{
			IntConsumer c = new IntConsumer() {
				public void accept(int v) {
					System.out.println(v);
				}
			};
			c.accept(42);
		}
		{
			IntConsumer c = x -> System.out.println(x);
			c.accept(42);
		}
		// -----------------------------------------------------------
		{
			BiConsumer<Integer,String> c = new BiConsumer<Integer,String>() {
				public void accept(Integer i, String s) {
					System.out.println(i + " " + s);
				}
			};
			c.accept(42, "Hello");
		}
		{
			BiConsumer<Integer,String> c = (i, s) -> System.out.println(i + " " + s);
			c.accept(42, "Hello");
		}
		
		// -----------------------------------------------------------
		Foo<Integer> f1 = v -> System.out.println("c1: " + v);
		Foo<Integer> f2 = v -> System.out.println("c2: " + v);
		f1.andThen2(f2).accept(42);
	}

	static <T> void pipe(Supplier<? extends T> s, Consumer<? super T> c) {
		c.accept(s.get());
	}
}