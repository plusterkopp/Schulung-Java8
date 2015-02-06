package appl;

import static java.lang.System.out;
import utils.Array;

public class Application {
	public static void main( String[] args) {
		Array<String> array = new Array<>();
		array.add( "one");
		array.add( "two");
		array.add( "three");
		array.add( "four");
		array.add( "five");
		array.add( "six");

		array.pipe()
			.forEach( s -> out.print( s + " "));
		out.println();
		array.pipe()
			.filter( s -> s.length() > 3)
			.filter( s -> s.endsWith( "e"))
			.forEach( s -> out.print( s + " "));
		out.println();
	}
}
