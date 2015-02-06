package appl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import util.PerformanceRunner;

public class Application {

	public static void main(String[] args) {
		{
			final List<Integer> list = Arrays.asList(1, 2, 3);
			Stream<Integer> stream = list.stream();
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		{
			final Integer[] array = new Integer[] { 1, 2, 3 };
			Stream<Integer> stream = Arrays.stream(array);
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		{
			final int[] array = new int[] { 1, 2, 3 };
			IntStream stream = Arrays.stream(array);
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			//stream.forEach((int x) -> System.out.print(x + " "));
			System.out.println();
		}
		{
			final long[] array = new long[] { 1, 2, 3 };
			LongStream stream = Arrays.stream(array);
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		{
			final double[] array = new double[] { 1.0, 2.0, 3.0 };
			DoubleStream stream = Arrays.stream(array);
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		{
			Stream<Integer> stream = Stream.of(1, 2, 3); 
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		{
			IntStream stream = IntStream.range(1, 4); 
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		{
			IntStream stream = "123".chars(); 
			System.out.println(stream.getClass().getName());
			stream.forEach(x -> System.out.print((char)x + " "));
			System.out.println();
		}
		
		final PerformanceRunner runner = new PerformanceRunner();
		final int size = 1_000_000;
		final int loops = 1_000;
		{
			System.out.println("test Integer-stream...");
			final Integer[] array = new Integer[size];
			for (int i = 0; i < size; i++)
				array[i] = i;
			final IntHolder h = new IntHolder();
			runner.run("Integer", loops, () -> {
				final Stream<Integer> stream = Arrays.stream(array);
				stream.forEach((Integer x) -> h.value += x);
			});
			System.out.println(h.value);
		}
		{
			System.out.println("test int-stream...");
			final int[] array = new int[size];
			for (int i = 0; i < size; i++)
				array[i] = i;
			final IntHolder h = new IntHolder();
			runner.run("int", loops, () -> {
				final IntStream stream = Arrays.stream(array);
				stream.forEach((int x) -> h.value += x);
			});
			System.out.println(h.value);
		}
		{
			final IntStream stream = IntStream.iterate(10, x -> x + 2).limit(5);
			stream.forEach((int x) -> System.out.print(x + " "));
			System.out.println();
		}
		{
			final Stream<Integer> stream = Stream.iterate(10, x -> x + 2).limit(5);
			stream.forEach((Integer x) -> System.out.print(x + " "));
			System.out.println();
		}
		{
			AtomicInteger i = new AtomicInteger(10);
			final IntStream stream = IntStream.generate(() -> i.getAndIncrement()).limit(5);
			stream.forEach((int x) -> System.out.print(x + " "));
			System.out.println();
		}
		{
			AtomicInteger i = new AtomicInteger(10);
			final Stream<Integer> stream = Stream.generate(() -> i.getAndIncrement()).limit(5);
			stream.forEach((Integer x) -> System.out.print(x + " "));
			System.out.println();
		}
		{
			Stream<Integer> stream = Stream.empty();
			stream.forEach((Integer x) -> System.out.print(x + " "));
			System.out.println();
		}
	}
}
