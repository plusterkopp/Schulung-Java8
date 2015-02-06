package appl;

import static java.lang.System.out;

import java.util.Spliterator;
import java.util.Spliterators;

import util.ThreadUtil;


public class Application {

	public static void main(String[] args) {
		{
			out.println("---------------------------------");
			final Integer[] array = new Integer[] { 10, 20, 30, 40, 50, 60};			
			final Spliterator<Integer> s = Spliterators.spliterator(array, 0);
			while(s.tryAdvance((Integer v) -> out.print(v + " ")));
			out.println();
		}
		{
			out.println("---------------------------------");
			final Integer[] array = new Integer[] { 10, 20, 30, 40, 50, 60};			
			final Spliterator<Integer> s = Spliterators.spliterator(array, 0);
			s.forEachRemaining((Integer v) -> out.print(v + " "));
			out.println();
		}
		{
			out.println("---------------------------------");
			final Integer[] array = new Integer[] { 10, 20, 30, 40, 50, 60};			
			final Spliterator<Integer> s = Spliterators.spliterator(array, 0);
			s.tryAdvance((Integer v) -> out.print(v + " "));
			s.forEachRemaining((Integer v) -> out.print(v + " "));
			out.println();
		}
		{
			out.println("---------------------------------");
			final Integer[] array = new Integer[] { 10, 20, 30, 40, 50, 60};			
			final Spliterator<Integer> s1 = Spliterators.spliterator(array, 0);
			final Spliterator<Integer> s2 = s1.trySplit();
			System.out.println(s1);
			System.out.println(s2);
			s1.forEachRemaining(v -> out.print(v + " "));
			s2.forEachRemaining(v -> out.print(v + " "));
			out.println();
		}
		{
			out.println("---------------------------------");
			final Integer[] array = new Integer[] { 10, 20, 30, 40, 50, 60};			
			final Spliterator<Integer> s1 = Spliterators.spliterator(array, 0);
			final Spliterator<Integer> s2 = s1.trySplit();
			final Thread t1 = new Thread(() -> { 
				s1.forEachRemaining(v -> {
					ThreadUtil.sleep(100);
					out.print(v + " ");
				});
			});
			t1.start();
			ThreadUtil.sleep(50);
			final Thread t2 = new Thread(() -> {
				s2.forEachRemaining(v -> {
					ThreadUtil.sleep(100);
					out.print(v + " ");
				});
			});
			t2.start();
			ThreadUtil.join(t1);
			ThreadUtil.join(t2);
			out.println();
		}
		{
			out.println("---------------------------------");
			final Spliterator.OfInt s1 = Spliterators.spliterator(new int[] { 10, 20, 30, 40, 50, 60}, 0);
			final Spliterator.OfInt s2 = s1.trySplit();
			System.out.println(s1);
			System.out.println(s2);
			s1.forEachRemaining((int v) -> out.print(v + " "));
			s2.forEachRemaining((Integer v) -> out.print(v + " "));
			out.println();
		}
		out.println("=====================================");
		{
			out.println("---------------------------------");
			final Array<Integer> array = new Array<>(new Integer[] { 10, 20, 30, 40, 50, 60});
			final Spliterator<Integer> s = array.spliterator();
			s.forEachRemaining(v -> out.print(v + " "));
			out.println();
		}
		{
			out.println("---------------------------------");
			final Array<Integer> array = new Array<>(new Integer[] { 10, 20, 30, 40, 50, 60});
			final Spliterator<Integer> s = array.spliterator();
			while(s.tryAdvance(v -> out.print(v + " ")));
			out.println();
		}
		{
			out.println("---------------------------------");
			final Array<Integer> array = new Array<>(new Integer[] { 10, 20, 30, 40, 50, 60});
			final Spliterator<Integer> s1 = array.spliterator();
			System.out.println("s1   : " + s1.estimateSize());
			final Spliterator<Integer> s2 = s1.trySplit();
			System.out.println("s1,s2: " + s1.estimateSize() + " " + s2.estimateSize());
			while(s2.tryAdvance(v -> out.print(v + " ")));
			while(s1.tryAdvance(v -> out.print(v + " ")));
			out.println();
		}
	}
}
