package appl;

import java.util.List;

import static appl.Query.from;

public class Application {
	
	public static void main(String[] args) throws Exception {
		List<Book> books = from(Book.class)
				.select(b -> b.isbn, b -> b.title)
				.where(b -> b.price > 0).list();
		books.forEach(System.out::println);
	}
}
