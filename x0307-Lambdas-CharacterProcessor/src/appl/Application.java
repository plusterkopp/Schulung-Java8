package appl;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Application {

	static void demo0() {
		Reader reader = new StringReader("Hello World");
		CharacterProcessor.process(reader, new Handler<Character>() {
			public void handle(Character ch) {
				System.out.println(ch);
			}
		});
	}

	static void demo1() {
		Reader reader = new StringReader("Hello World");
		CharacterProcessor.process(reader, (ch) -> System.out.println(ch) );
	}
	
	static void demo2() {
		Reader reader = new StringReader("Hello World");
		List<Character> chars = new ArrayList<>();
		CharacterProcessor.process(reader, (ch) -> chars.add(ch));
		for (Character ch : chars)
			System.out.println(ch);
	}

//	static void demo3Illegal() {
//		Reader reader = new StringReader("Hello World");
//		int n = 0;
//		CharacterProcessor.process(reader, (ch) -> n++);
//		System.out.println(n);
//	}

	static void demo3() {
		Reader reader = new StringReader("Hello World");
		Box<Integer> n = new Box<>(0);
		CharacterProcessor.process(reader, (ch) -> n.value++);
		System.out.println(n);
	}
	
	static void demo4() {
		Reader reader = new StringReader("Hello World");
		Box<Integer> n = new Box<>(0);
		CharacterProcessor.process(reader, (ch) -> { if (! Character.isWhitespace(ch)) n.value++; });
		System.out.println(n);
	}

	static void demo5() {
		Reader reader = new StringReader("Hello World");
		Box<Integer> n = new Box<>(0);
		CharacterProcessor.process(reader, ch -> ! Character.isWhitespace(ch), ch -> n.value++);
		System.out.println(n);
	}

	public static void main(String[] args) {
		demo0();
		demo1();
		demo2();
		demo3();
		demo4();
		demo5();
	}

}
