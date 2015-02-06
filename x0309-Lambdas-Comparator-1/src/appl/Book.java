package appl;

import java.util.ArrayList;
import java.util.List;

public class Book {
	public String isbn;
	public String title;
	public String author;
	public double price;

	public Book(String isbn, String title, String author, double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" + isbn + ", " + title + ", " + author + ", " + price + "]";
	}

	public static final List<Book> list = new ArrayList<>();
	static {
		list.add(new Book("1111", "Pascal", "Wirth", 44.44));
		list.add(new Book("1112", "Pascal", "Wirth", 44.44));
		list.add(new Book("2222", "Modula", "Wirth", 33.33));
		list.add(new Book("3333", "Oberon", "Wirth", 22.22));
		list.add(new Book("3334", "Oberon", "Wirth", 22.21));
		list.add(new Book("4444", "Eiffel", "Meyer", 11.11));
	}
}
