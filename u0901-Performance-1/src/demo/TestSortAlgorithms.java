package demo;

import utils.Assert;
import utils.SimpleTest;
import utils.Test;
import utils.TestRunner;

import java.util.Arrays;


class AlphaSort {
	public static void sort(final int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minPos = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minPos])
					minPos = j;
			}
			final int park = array[minPos];
			array[minPos] = array[i];
			array[i] = park;
		}
	}
}

class BubbleSort {
	public static void sort(final int[] array) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j + 1] < array[j]) {
					final int park = array[j];
					array[j] = array[j + 1];
					array[j + 1] = park;
					sorted = false;
				}
			}
		}
	}
}

public class TestSortAlgorithms {

	public static void main(final String[] args) throws Throwable {
		TestRunner.run(sort);
	}

	static Test sort = new SimpleTest("sort", 2) {
		{
			final int SIZE = 1000;
			final int TIMES = 1000;
			final int[] array = new int[SIZE];
			final int[] sortedArray = new int[SIZE];

			initialize(() -> {
				for (int i = 0; i < SIZE; i++)
					sortedArray[i] = i;
			});
			before(() -> {
				for (int i = 0; i < SIZE; i++)
					array[i] = SIZE - i - 1;
			});
			
			run("alpha",  TIMES, () -> AlphaSort.sort(array));
			run("bubble", TIMES, () -> BubbleSort.sort(array));
			run("arrays", TIMES, () -> Arrays.sort(array));
			
			after(() -> Assert.assertArrayEquals(sortedArray, array));
			terminate(() -> { });
		}
	};
}

