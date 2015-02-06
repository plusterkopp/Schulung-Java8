package demo;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

import utils.Assert;
import utils.SimpleTest;
import utils.TestContainer;
import utils.TestRunner;


public class TestArraysAndLists {

	public static void main(final String[] args) throws Throwable {
		TestRunner.run(all);
	}

	static TestContainer all = new TestContainer("all", 2) {
		{
			final int SIZE = 1000;
			final int N = 10_000;
			final int LOOPS = 20;
			final int EXPECTED = (SIZE - 1) / 2 * (SIZE) + SIZE / 2;
			
			initialize(() -> out.println("initialize"));
			before(() -> out.println("before"));
			
			new SimpleTest(this, "arraysTest", LOOPS) {
				final int[] array = new int[SIZE];
				{
					initialize(() -> {
						for (int i = 0; i < array.length; i++) 
							array[i] = i;
					});
					
					run("arrays",  N, () -> {
						int sum = 0;
						final int n = array.length;
						for (int i = 0; i < n; i++)
							sum += array[i];
						Assert.assertEquals(EXPECTED, sum);
					});
				}
			};

			new SimpleTest(this, "listTest", LOOPS) {
				final List<Integer> list = new ArrayList<>();
				{
					initialize(() -> {
						list.clear();
						for (int i = 0; i < SIZE; i++) {
							list.add(i);
						}
					});
					
					run("list",  N, () -> {
						final int n = list.size();
						int sum = 0;
						for (int i = 0; i < n; i++)
							sum += list.get(i);
						Assert.assertEquals(EXPECTED, sum);
					});
				}
			};
			
			after(() -> out.println("after"));
			terminate(() -> out.println("terminate"));
		}
	};
}


