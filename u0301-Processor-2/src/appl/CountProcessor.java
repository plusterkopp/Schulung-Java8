package appl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class CountProcessor {
	public void run(String filename) {
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filename))) {
			int n = 0;
			int ch = reader.read();
			while (ch != -1) {
				n++;
				ch = reader.read();
			}
			out.println(n);
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
