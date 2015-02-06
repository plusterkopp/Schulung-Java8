package appl;

import java.io.PrintStream;

public class Application {
	
	static class IntHolder {
		int	count;
	}

	static PrintStream out = System.out;

	public static void main(String[] args) throws Exception {
		final String FILENAME = "src/appl/Application.java";
		new FileReader<IntHolder>()
			.initialize( context -> context.count = 0)
			.process( ( context, ch) -> context.count++)
			.terminate( context -> out.println( context.count))
			.run( FILENAME, new IntHolder());
	}
}
