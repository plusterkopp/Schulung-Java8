package appl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		final List<String> words = Arrays.asList("eine", "Rose", "ist", "eine", "Rose", "ist", "eine", "Rose");
		{
			out.println("------------------------------");
			Map<String,Integer> counts = new HashMap<>();
			for (String word : words) {
				Integer count = counts.get(word);
				if (count == null) {
					count = 0;
					counts.put(word, count);
				}
				counts.put(word, count + 1);
			}
			out.println(counts);
		}
		{
			out.println("------------------------------");
			Map<String,Integer> counts = new HashMap<>();
			for (String word : words) {
				// Integer count = counts.getOrDefault(word, 0);
				int count = counts.getOrDefault(word, 0);
				counts.put(word, count + 1);
			}
			out.println(counts);
		}
		{
			out.println("------------------------------");
			Map<String,Integer> counts = new HashMap<>();
			for (String word : words) {
				counts.putIfAbsent(word, 0);
				int count = counts.get(word);
				counts.replace(word, count + 1);
			}
			out.println(counts);
		}
		{
			out.println("------------------------------");
			Map<String,Integer> counts = new HashMap<>();
			for (String word : words) {
				//counts.putIfAbsent(word, 0);
				counts.computeIfAbsent(word, (String k) -> 0);
				counts.computeIfPresent(word, (String k, Integer v) -> v + 1);
			}
			out.println(counts);
		}
	}
}
