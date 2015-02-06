package appl;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import appl.Application.IntHolder;

public class FileReader<T> {

	private Consumer<T> initializer;
	private BiConsumer<T, Character> processor;
	private Consumer<T> terminator;

	public FileReader<T> initialize( Consumer<T> c) {
		initializer = c;
		return this;
	}

	public FileReader<T> process( BiConsumer<T, Character> c) {
		processor = c;
		return this;
	}

	public FileReader<T> terminate( Consumer<T> c) {
		terminator = c;
		return this;
	}

	public void run( String filename, T t) {
		try ( InputStreamReader reader = new InputStreamReader( new FileInputStream( filename))) {
			if ( initializer != null) {
				initializer.accept( t);
			}
			int ch = reader.read();
			while ( ch != -1) {
				if ( processor != null) {
					processor.accept( t, ( char) ch);
				}
				ch = reader.read();
			}
			if ( terminator != null) {
				terminator.accept( t);
			}
		}
		catch ( FileNotFoundException e) {
			throw new RuntimeException( e);
		}
		catch ( IOException e) {
			System.err.println( e);
			System.exit( 0);
		}
	}

}
