package appl;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintProcessor {
	public void run(String filename) {
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filename))) {
			out.println(">>> jetzt geht's los!");
			int ch = reader.read();
			while (ch != -1) {
				out.print((char) ch);
				ch = reader.read();
			}
			out.println("<<< das war's dann!");
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		catch (IOException e) {
			System.err.println(e);
			System.exit(0);
		}
	}
}
