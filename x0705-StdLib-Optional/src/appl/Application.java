package appl;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Application {

	public static void main(String[] args) {
		{
			Optional<Integer> o = Optional.empty();
			System.out.println(o);
			try {
				o.get();
			}
			catch(NoSuchElementException e) {
				System.out.println("Expected: " + e.getMessage());
			}
		}
		{
			Optional<Integer> o = Optional.of(42);
			System.out.println(o);
			Integer v = o.get();
			System.out.println(v);
		}
		{
			Optional<Integer> o = Optional.empty();
			if (o.isPresent()) 
				System.out.println(o.get());
			else
				System.out.println("not present");
		}
		{
			Optional<Integer> o = Optional.of(42);
			if (o.isPresent()) 
				System.out.println(o.get());
			else
				System.out.println("not present");
		}
		{
			Optional<Integer> o = Optional.of(42);
			Integer v = o.orElse(77);
			System.out.println(v);
		}
		{
			Optional<Integer> o = Optional.empty();
			Integer v = o.orElse(77);
			System.out.println(v);
		}
		{
			Integer i = null;
			try {
				Optional<Integer> o = Optional.of(i);
				System.out.println(o);
			}
			catch(NullPointerException e) {
				System.out.println("Expected");
			}
		}
		{
			Integer i = null;
			Optional<Integer> o = Optional.ofNullable(i);
			Integer v = o.orElse(77);
			System.out.println(v);
		}
	}
}
