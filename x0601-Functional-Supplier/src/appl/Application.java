package appl;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Application {
	public static void main(String[] args) {
		{
			Supplier<Integer> s = new Supplier<Integer>() {
				public Integer get() {
					return 42;
				}
			};
			int v = s.get();
			System.out.println(v);
		}
		{
			Supplier<Integer> s = () -> 42;
			int v = s.get();
			System.out.println(v);
		}

		{
			IntSupplier s = new IntSupplier() {
				public int getAsInt() {
					return 42;
				}
			};
			int v = s.getAsInt();
			System.out.println(v);
		}
		{
			IntSupplier s = () -> 42;
			int v = s.getAsInt();
			System.out.println(v);
		}

		{
			IntSupplier s = new IntSupplier() {
				int n = 0;
				public int getAsInt() {
					return n == 10 ? 0 : ++n;
				}
			};
			for (int v = s.getAsInt(); v != 0; v = s.getAsInt()) 
				System.out.print(v + " ");
			System.out.println();
		}
	}
}