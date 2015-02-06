package appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Application {
	public static void main(String[] args) {
		{
			Predicate<Integer> p = new Predicate<Integer>() {
				public boolean test(Integer v) {
					return v % 2 == 0;
				}
			};
			System.out.println(p.test(3));  // -> false
			System.out.println(p.test(4));  // -> true
		}
		{
			Predicate<Integer> p = v -> v % 2 == 0;
			System.out.println(p.test(3));  // -> false
			System.out.println(p.test(4));  // -> true
		}
		{
			Predicate<Integer> p1 = v -> v > 10;
			Predicate<Integer> p2 = v -> v < 20;
			System.out.println(p1.and(p2).test(3));   // -> false
			System.out.println(p1.and(p2).test(30));  // -> false
			System.out.println(p1.and(p2).test(13));  // -> true
		}
		{
			//List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
			List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
			list.removeIf(x -> x % 2 == 0);
			list.forEach(x -> System.out.println(x)); // 1 3 5
		}
	}		
}