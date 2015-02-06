package appl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

	public static void main(String[] args) {
		{
			List<String> list = Arrays.asList("red", "green", "blue");
			String s = String.join(", ", list);
			System.out.println(s);
		}
		{
			List<String> list = Arrays.asList("red", "green", "blue");
			String s = list.stream().collect(Collectors.joining(", "));
			System.out.println(s);
		}
		{
			List<Integer> list = Arrays.asList(10, 20, 30);
			String s = list.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(", "));
			System.out.println(s);
		}
		{
			List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
			Map<Integer, List<String>> map = list.stream().collect(Collectors.groupingBy(s -> s.length()));
			System.out.println(map);
		}
		{
			List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
			Map<Object, Long> map = list.stream().collect(Collectors.groupingBy(s -> s.length(), Collectors.counting()));
			System.out.println(map);
		}
		{
			List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
			Map<Boolean, List<String>> map = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
			System.out.println(map);
		}
	}
}
