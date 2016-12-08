package appl;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {

	public static void main(String[] args) {
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			stream.forEach(s -> System.out.print(s + " "));
			System.out.println();
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			stream.forEachOrdered(s -> System.out.print(s + " "));
			System.out.println();
		}
		// -------------------------------------------------------------
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Object[] array = stream.toArray();
			for (Object v : array)
				System.out.print(v + " ");
			System.out.println();
		}
		{
			IntStream stream = IntStream.of(10, 20, 30, 40, 50).parallel();
			int[] array = stream.toArray();
			for (int v : array)
				System.out.print(v + " ");
			System.out.println();
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Integer[] array = stream.toArray(n -> new Integer[n]);
			//Integer[] array = stream.toArray(Integer[]::new);
			for (Integer v : array)
				System.out.print(v + " ");
			System.out.println();
		}
		// -------------------------------------------------------------
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Integer sum = stream.reduce(0, (x, y) -> x + y);
			System.out.println(sum);
		}
		{
			Stream<Integer> stream = Stream.empty();
			Integer sum = stream.reduce(0, (x, y) -> x + y);
			System.out.println(sum);
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Integer product = stream.reduce(1, (x, y) -> x * y);
			System.out.println(product);
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Optional<Integer> result = stream.reduce((x, y) -> x + y);
			int sum = result.get();
			System.out.println(sum);
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Double result = stream.reduce(new Double(0.1), (x, y) -> x * y, (x, y) -> x + y);
			System.out.println(result);
		}
		// -------------------------------------------------------------
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
			List<Integer> list = stream.collect(
					() -> new ArrayList<Integer>(), 
					(List<Integer> l, Integer v) -> l.add(v), 
					(List<Integer> l1, List<Integer> l2) -> l1.addAll(l2)
			);
			System.out.println(list);
		}
		{
			//Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			List<Integer> list = stream.collect(
					() -> new ArrayList<Integer>(), 
					(List<Integer> l, Integer v) -> l.add(v), 
					(List<Integer> l1, List<Integer> l2) -> { 
						System.out.println(Thread.currentThread().getId() 
								+ ": combine " + l1 + " and " + l2);
						l1.addAll(l2); 
					}
			);
			System.out.println(list);
		}
		{
			Collector<Integer, List<Integer>, Integer[]> collector = 
					new Collector<Integer, List<Integer>, Integer[]>() {
				public Supplier<List<Integer>> supplier() {
					return () -> new ArrayList<Integer>();
				}
				public BiConsumer<List<Integer>, Integer> accumulator() {
					return (l, v) -> l.add(v);
				}
				public BinaryOperator<List<Integer>> combiner() {
					return (l1, l2) -> { l1.addAll(l2); return l1; };
				}
				public Function<List<Integer>, Integer[]> finisher() {
					return l -> l.toArray(new Integer[l.size()]);
				}
				public Set<Characteristics> characteristics() {
					return new HashSet<Characteristics>();
				}
			};
			//Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			Integer[] array = stream.collect(collector);
			System.out.println(Arrays.toString(array));
			Collectors.toList();
		}
		{
			Collector<Integer, List<Integer>, List<Integer>> collector = 
					new Collector<Integer, List<Integer>, List<Integer>>() {
				public Supplier<List<Integer>> supplier() {
					return () -> new ArrayList<Integer>();
				}
				public BiConsumer<List<Integer>, Integer> accumulator() {
					return (l, v) -> l.add(v);
				}
				public BinaryOperator<List<Integer>> combiner() {
					return (l1, l2) -> { l1.addAll(l2); return l1; };
				}
				public Function<List<Integer>, List<Integer>> finisher() {
					return l -> l;
				}
				public Set<Characteristics> characteristics() {
					return new HashSet<Characteristics>();
				}
			};
			//Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			List<Integer> list = stream.collect(collector);
			System.out.println(list);
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			List<Integer> list = stream.collect(Collectors.toList());
			System.out.println(list);
		}
		
		// -------------------------------------------------------------
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.min((x, y) -> x.compareTo(y)));
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.max((x, y) -> x.compareTo(y)));
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.count());
		}
		// -------------------------------------------------------------
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.anyMatch(x -> x == 20));
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.allMatch(x -> x <= 50));
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.noneMatch(x -> x == 42));
		}
		// -------------------------------------------------------------
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.findAny()); // mal so, mal so....
		}
		{
			Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
			System.out.println(stream.findFirst());
		}
		
	}
}
