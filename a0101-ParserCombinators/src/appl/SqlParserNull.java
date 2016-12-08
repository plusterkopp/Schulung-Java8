package appl;

import parser.Parser;
import parser.RepCollectParser;
import parser.Seq2Parser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Specials;

import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static parser.IdentifierParser.identifier;

public class SqlParserNull {
	
	static class Sql {
		List<String> columns;
		String table;
		Sql(String table, List<String> columns) {
			this.table = table;
			this.columns = columns;
		}
		@Override
		public String toString() {
			return table + " " + columns;
		}
	}
	
	public static void main(String[] args) {
		final Map<String,Sql> map = new LinkedHashMap<>();
		map.put("select isbn, title, price from book", new Sql("book", Arrays.asList("isbn", "title", "price")));
		map.put("select isbn, title from book", new Sql("book", Arrays.asList("isbn", "title")));
		map.put("select isbn from book", new Sql("book", Arrays.asList("isbn")));
		for (Map.Entry<String,Sql> entry : map.entrySet()) {
			final String text = entry.getKey();
			final Sql expectedResult = entry.getValue();
			final Sql result = SqlParserNull.parse(text);
			System.out.println(result + " (expected: " + expectedResult + ")");
		}
	}
	
	private static final Specials specials = new Specials();
	
	private static final SpecialParser SELECT = new SpecialParser(specials.create("select"));
	private static final SpecialParser FROM = new SpecialParser(specials.create("from"));
	private static final SpecialParser COMMA = new SpecialParser(specials.create(","));
	
	private final Parser<Sql> sql;
	private final Parser<List<String>> select;
	private final Parser<List<String>> columns;
	private final Parser<String> from;
	
	public SqlParserNull() {
		this.sql = new Seq2Parser<>(
				() -> getSelect(),
				() -> getFrom(),
				(s1, s2) -> null);
		this.select = new Seq2Parser<>(
				() -> SELECT,
				() -> getColumns(),
				(s1, s2) -> null);
		this.columns = new RepCollectParser<>(
				() -> identifier,
				() -> COMMA,
				() -> null,
				(l, s) -> {});
		this.from = new Seq2Parser<>(
				() -> FROM,
				() -> identifier,
				(s1, s2) -> null);
	}

	private Parser<List<String>> getColumns() {
		return this.columns;
	}

	private Parser<String> getFrom() {
		return this.from;
	}

	private Parser<List<String>> getSelect() {
		return this.select;
	}

	public static Sql parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new SqlParserNull().sql.parse(scanner);
	}
}
