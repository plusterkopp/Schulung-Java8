package appl;

import util.PerformanceRunner;

import java.util.Arrays;
import java.util.Spliterator.OfInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		
		testParallelSort(10_000_000, 10);
		testParallelSort(10, 10_000_000);

		System.gc();
		testParallelSort(10_000_000, 10);
		System.gc();
		testParallelSort(10, 10_000_000);

		{
			final int[] array = new int[10];
			Arrays.setAll(array, new IntUnaryOperator() {
				public int applyAsInt(int index) {
					return index * 2;
				}
			});
			for(int value : array)
				out.print(value + " ");
			out.println();
		}

		{
			final int[] array = new int[10];
			Arrays.setAll(array, index -> index * 2);
			for(int value : array)
				out.print(value + " ");
			out.println();
		}

		// testParallelSetAll(10_000_000, 10);
		// testParallelSetAll(10, 10_000_000);
		
		{
			final int[] array = new int[10];
			Arrays.setAll(array, index -> index + 1);
			Arrays.parallelPrefix(array, new IntBinaryOperator() {
				public int applyAsInt(int left, int right) {
					return left + right;
				}
			});
			for (int value : array) 
				out.print(value + " ");
			out.println();
		}
		{
			final int[] array = new int[10];
			Arrays.setAll(array, index -> index + 1);
			Arrays.parallelPrefix(array, (left, right) -> left + right);
			for (int value : array) 
				out.print(value + " ");
			out.println();
		}
		{
			final int[] array = new int[10];
			Arrays.setAll(array, index -> index * 2);
			OfInt s = Arrays.spliterator(array);
			s.forEachRemaining(new IntConsumer() {
				public void accept(int value) {
					out.print(value + " ");
				}
			});
			out.println();
		}
		{
			final int[] array = new int[10];
			Arrays.setAll(array, index -> index * 2);
			Arrays.spliterator(array).forEachRemaining((int v) -> out.print(v + " "));
			out.println();
		}
	}

	static void testParallelSort(int loops, int arraySize) {
		final int[] array = new int[arraySize];
		new PerformanceRunner().run("sort " + array.length, loops, 
				() -> initArray(array), 
				() -> Arrays.sort(array));
		new PerformanceRunner().run("parallelSort " + array.length, loops, 
				() -> initArray(array),
				() -> Arrays.parallelSort(array));

	}

	static void initArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (array.length * Math.random());
		}
	}

	static void testParallelSetAll(int loops, int arraySize) {
		final int[] array = new int[arraySize];
		new PerformanceRunner().run("setAll " + array.length, loops, 
				() -> Arrays.setAll(array, index -> 2 * index));
		new PerformanceRunner().run("parallelSetAll " + array.length, loops,
				() -> Arrays.parallelSetAll(array, index -> 2 * index));
	}
}
