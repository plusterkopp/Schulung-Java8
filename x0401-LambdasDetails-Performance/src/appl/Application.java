package appl;

import static java.lang.System.out;
import util.PerformanceRunner;

// try JVM-Arg -server!

public class Application {

	private static final long TIMES = 1 * 1_000_000_000;
	private static final int LOOPS = 5;

	private static long n;

	public static void main( String[] args) throws Exception {
		out.println( "Performace-Test: this will take some time ...");
		PerformanceRunner runner = new PerformanceRunner();
		for ( int i = 0; i < LOOPS; i++) {
			{
				Runnable r = new Runnable() {
					public void run() {
						n++;
					}
				};
				runner.run( "anonymous class", TIMES, r);
			}
			{
				Runnable r = ( ) -> n++;
				runner.run( "lambda", TIMES, r);
			}
			out.println( n);
			n = 0;
		}
	}
}
