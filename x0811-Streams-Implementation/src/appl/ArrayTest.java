package appl;

import utils.Array;
import utils.Stream;

public class ArrayTest {
	public static void main(String[] args) {
		demo1();
		demo2();
	}
	static void demo1() {
		Array<String> array = new Array<String>();
		array.add("red");
		array.add("green");
		array.add("blue");
		
		Stream<String> s1 = array.stream();
		Stream<String> s2 = s1.filter(s -> s.length() > 3); 
		Stream<String> s3 = s2.map(s -> s.substring(2)); 
		Stream<Integer> s4 = s3.map(s -> s.length()); 
		Stream<Integer> s5 = s4.filter(i -> i <= 2); 
		s5.forEach(v -> System.out.println(v));
	}

	static void demo2() {
		Array<String> array = new Array<String>();
		array.add("red");
		array.add("green");
		array.add("blue");
		
		array.stream()
			.filter(s -> s.length() > 3)
			.map(s -> s.substring(2))
			.map(s -> s.length()) 
			.filter(i -> i <= 2) 
			.forEach(v -> System.out.println(v));
	}
}
