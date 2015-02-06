package scanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Specials implements Iterable<Special> {
	private final List<Special> list = new ArrayList<Special>();
	public Special create(String text) {
		Special special = new Special(text);
		this.list.add(special);
		return special;
	}
	public Iterator<Special> iterator() {
		return this.list.iterator();
	}
}
