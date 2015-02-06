package appl;

public class Application {
	public static void main(String[] args) throws Exception {
		final String FILENAME = "src/appl/Application.java";
		new PrintProcessor().run(FILENAME);
		new CountProcessor().run(FILENAME);
	}
}
