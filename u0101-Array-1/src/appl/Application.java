package appl;

import utils.Array;

import static java.lang.System.out;

public class Application {
	public static void main(String[] args) {
		Array<String> array = new Array<>();
		array.add("one");
		array.add("two");
		array.add("three");
		array.add("four");
		array.add("five");
		array.add("six");
		for (int i = 0; i < array.size(); i++) {
			out.print(array.get(i) + " ");
		}
		out.println();
	}
}
