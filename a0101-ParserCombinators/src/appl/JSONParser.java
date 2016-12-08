package appl;

import parser.Parser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Specials;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static parser.ForwardParser.forward;
import static parser.IdentifierParser.identifier;
import static parser.NumberParser.number;
import static parser.Or3Parser.or3;
import static parser.RepCollectParser.repCollect;
import static parser.Seq3Parser.seq3;
import static parser.StringParser.string;
import static parser.TransformParser.transform;

public class JSONParser {
	
	public static void main(String[] args) {
		final List<String> list = new ArrayList<>();
		list.add("{ a : 1, b : [ null, 2, \"abc\"], uvw : null }");
		for (String text : list) {
			final Object result = JSONParser.parse(text);
			System.out.println(result + " (expected: " + text + ")");
		}
	}
	
	private static final Specials specials = new Specials();
	
	private static final SpecialParser COLON = new SpecialParser(specials.create(":"));
	private static final SpecialParser NULL = new SpecialParser(specials.create("null"));
	private static final SpecialParser COMMA = new SpecialParser(specials.create(","));
	private static final SpecialParser LOBJECT = new SpecialParser(specials.create("{"));
	private static final SpecialParser ROBJECT = new SpecialParser(specials.create("}"));
	private static final SpecialParser LARRAY = new SpecialParser(specials.create("["));
	private static final SpecialParser RARRAY = new SpecialParser(specials.create("]"));

	private Parser<Object> json;
	private Parser<Object> jsonObject;
	private Parser<Object> jsonArray;
	private Parser<Object> jsonArrayList;
	private Parser<Object> jsonObjectList;
	private Parser<Object> jsonValue;
	private Parser<Object> jsonNamedValue;
	private Parser<Object> jsonSimpleValue;
	
	final int x ;

	public JSONParser() {

		x = 77;
		this.json = forward(
				() -> jsonObject);
		
		this.jsonObject = seq3(
				() -> LOBJECT, 
				() -> jsonObjectList,
				() -> ROBJECT,
				(p1, p2, p3) -> null);
		
		this.jsonArray = seq3(
				() -> LARRAY, 
				() -> jsonArrayList,
				() -> RARRAY,
				(p1, p2, p3) -> null);

		this.jsonArrayList = repCollect(
				() -> jsonValue, 
				() -> COMMA,
				() -> null,
				(l, v) -> {});
		
		this.jsonObjectList = repCollect(
				() -> jsonNamedValue, 
				() -> COMMA,
				() -> null,
				(l, v) -> {});

		this.jsonValue = or3(
				() -> jsonSimpleValue,
				() -> jsonObject,
				() -> jsonArray);
		
		this.jsonNamedValue = seq3(
				() -> identifier, 
				() -> COLON,
				() -> jsonValue,
				(p1, p2, p3) -> null);
				
		this.jsonSimpleValue = or3(
				() -> transform(() -> number, (v) -> null),
				() -> transform(() -> string, (v) -> null),
				() -> transform(() -> NULL, (v) -> null));

	}
	
	public static Object parse(String text) {
		Scanner scanner = new Scanner(specials, new StringReader(text));
		scanner.next();
		return new JSONParser().json.parse(scanner);
	}
}
