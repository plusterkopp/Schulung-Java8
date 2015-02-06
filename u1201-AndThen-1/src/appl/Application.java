package appl;

import static java.lang.System.out;

public class Application {

	public static void main( String[] args) {
		Worker w1 = v -> out.println( "w1: " + v);
		Worker w2 = v -> out.println( "w2: " + v);
		Worker w3 = v -> out.println( "w3: " + v);
		
		w1.andThen( w2).andThen( w3).work( 42);
	}
}
