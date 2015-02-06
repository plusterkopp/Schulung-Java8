package appl;

import java.util.Collections;
import java.util.Comparator;

public class Application {

	static void demo1() {
		Collections.sort( Book.list, new Comparator<Book>() {
			public int compare( Book b1, Book b2) {
				return b1.title.compareTo( b2.title);
			}
		});
		for ( Book b : Book.list)
			System.out.println( b);
	}

	static Comparator<Book> titleComparator = ( b1, b2) -> b1.title.compareTo( b2.title);

	static void demo2() {
		Collections.sort( Book.list, titleComparator);
		for ( Book b : Book.list)
			System.out.println( b);
	}

	static void demo3() {
		Collections.sort( Book.list, titleComparator.reversed());
		for ( Book b : Book.list)
			System.out.println( b);
	}

	static void demo4() {
		Collections.sort(
				Book.list, ( ( Comparator<Book>) (( b1, b2) -> b1.title.compareTo( b2.title)))
					.thenComparing( ( b1, b2) -> 
						Double.valueOf( b1.price).compareTo( Double.valueOf( b2.price)))
					.thenComparing( ( b1, b2) -> b1.isbn.compareTo( b2.isbn)));
		for ( Book b : Book.list)
			System.out.println( b);
	}

	public static void main( String[] args) {
		demo1();
		System.out.println( "-----------------------");
		demo2();
		System.out.println( "-----------------------");
		demo3();
		System.out.println( "-----------------------");
		demo4();
	}
}
