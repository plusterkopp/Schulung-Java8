package appl;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		Book book1 = new Book("1111", "Pascal", 10);
		Book book2 = new Book("3333", "Modula", 30);
		Book book3 = new Book("5555", "Pascal", 30);
		{
			out.println("---------------------------------");
			Comparator<Book> c = (b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn());
			out.println(c.compare(book1, book2));  // -> -2
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = Comparator.comparing(b -> b.getIsbn());
			out.println(c.compare(book1, book2));  // -> -2
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = (b1, b2) -> {
				if (b1.getPrice() > b2.getPrice())
					return 1;
				if (b1.getPrice() < b2.getPrice())
					return -1;
				return 0;
			};
			out.println(c.compare(book1, book2));  // -> -1
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = Comparator.comparing(b -> b.getPrice());
			out.println(c.compare(book1, book2));  // -> -1
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = Comparator.comparingInt(b -> b.getPrice());
			out.println(c.compare(book1, book2));  // -> -1
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = Comparator.comparingInt(b -> b.getPrice());
			c = c.reversed();
			out.println(c.compare(book1, book2));  // -> 1
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = Comparator.comparingInt((Book b) -> b.getPrice()).reversed();
			out.println(c.compare(book1, book2));  // -> 1
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c = Comparator.comparing(b -> b.getTitle());
			c = c.thenComparingInt(b -> b.getPrice());
			out.println(c.compare(book1, book3));  // -> -1
		}
		{
			out.println("---------------------------------");
            Comparator<Book> c = Comparator.comparing((Book b) -> b.getTitle()).thenComparing(b -> b.getIsbn());
            out.println(c.compare(book1, book3));  // -> -4
		}
		{
			out.println("---------------------------------");
            Comparator<Book> c = Comparator.comparing(b -> b.getTitle());
            c = c.thenComparingInt(b -> b.getPrice());
            out.println(c.compare(book1, book3));  // -> -1
		}
		out.println("====================================================");
		{
			out.println("---------------------------------");
			Comparator<Book> c = (b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn());
			out.println(c.compare(book1, book2));  // -> -2
			c = c.reversed();
			out.println(c.compare(book1, book2));  // -> 2
		}
		{
			out.println("---------------------------------");
			Comparator<Book> c1 = (b1, b2) -> b1.getTitle().compareTo(b2.getTitle());
			Comparator<Book> c2 = (b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn());
			Comparator<Book> c = c1.thenComparing(c2);
			out.println(c1.compare(book2, book3));
			out.println(c.compare(book2, book3));
		}
		{
			out.println("---------------------------------");
			Comparator<Integer> c1 = Comparator.naturalOrder();
			out.println(c1.compare(20, 30));  // -1
			Comparator<Integer> c2 = Comparator.reverseOrder();
			out.println(c2.compare(20, 30));  // 1
		}
		{
			out.println("---------------------------------");
			Comparator<Integer> c = Comparator.naturalOrder();
			c = Comparator.nullsFirst(c);
			out.println(c.compare(20, 30));  	// -1
			out.println(c.compare(null, 30));  	// -1
			out.println(c.compare(30 , null));  // 1
		}
		{
			out.println("---------------------------------");
			List<Integer> list = Arrays.asList(10, null, 30, null, 20);
			Comparator<Integer> c = Comparator.nullsFirst(Comparator.naturalOrder());
			list.sort(c);
			for(Integer v : list)
				out.print(v + " ");
			out.println();
		}
		{
			out.println("---------------------------------");
			List<Integer> list = Arrays.asList(10, null, 30, null, 20);
			Comparator<Integer> c = Comparator.nullsLast(Comparator.reverseOrder());
			list.sort(c);
			for(Integer v : list)
				out.print(v + " ");
			out.println();
		}
		
	}
}
