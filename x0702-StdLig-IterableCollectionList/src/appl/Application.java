package appl;

import java.util.*;

import static java.lang.System.out;

public class Application {
	
	public static void main(String[] args) {
		{
			out.println("---------------------------------");
			Iterable<Integer> list = Arrays.asList(20, 40, 10, 30);
			list.forEach(elem -> out.print(elem + " ")); 
			out.println();
		}
		{
			out.println("---------------------------------");
			//Collection<Integer> list = Arrays.asList(20, 40, 10, 30);
			Collection<Integer> list = new ArrayList<>(Arrays.asList(20, 40, 10, 30));
		    list.removeIf(elem -> elem >= 30);
			list.forEach(s -> out.print(s + " "));
			out.println();
		}
		{
			out.println("---------------------------------");
			List<Integer> list = Arrays.asList(20, 40, 10, 30);
			// list.sort((i0, i1) -> i0.compareTo(i1)); // List
			list.sort(Comparator.naturalOrder()); // List
			list.forEach(s -> out.print(s + " "));
			out.println();
		}
		{
			out.println("---------------------------------");
			List<Integer> list = Arrays.asList(20, 40, 10, 30);
		    list.replaceAll(elem -> 2 * elem); // List
			list.forEach(s -> out.print(s + " "));
			out.println();
		}
	}
}
