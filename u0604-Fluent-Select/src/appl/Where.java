package appl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Where<T> {
	public final Select<T> select;
	public final Predicate<T> predicate;
	
	public Where(Select<T> select, Predicate<T> predicate) {
		this.select = select;
		this.predicate = predicate;
	}

	public List<T> list() {
		System.out.print("select ");
		for (int i = 0; i < this.select.columns.length; i++) {
			if (i > 0)
				System.out.print(", ");
			System.out.print(this.select.columns[i].getClass());
		}
		System.out.print(" from ");
		System.out.print(select.from.tableClass.getSimpleName());
		System.out.print(" where ");
		System.out.println(predicate);
		
		return new ArrayList<T>();
	}
}
