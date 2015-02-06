package appl;

import java.util.Collections;
import java.util.Comparator;

public class Application {

	static void demo1() {
		Collections.sort(Book.list, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				return b1.title.compareTo(b2.title);
			}
		});
		for (Book b : Book.list)
			System.out.println(b);
	}
	static void demo2() {
		Collections.sort(Book.list, (b1, b2) -> - b1.title.compareTo(b2.title));
		for (Book b : Book.list)
			System.out.println(b);
	}
	public static void main(String[] args) {
		demo1();
		System.out.println("-----------------------");
		demo2();
	}
}
