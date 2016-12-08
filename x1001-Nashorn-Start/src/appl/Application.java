// http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html
// http://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/api.html
// http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/


package appl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

public class Application {

	public static void main(String[] args) throws Exception {
		demo0();
		demo1();
		demo2();
		demo3();
	}
	
	static void demo0() throws ScriptException {
		ScriptEngine engine1 = new ScriptEngineManager().getEngineByName("nashorn");
		ScriptEngine engine2 = new ScriptEngineManager().getEngineByName("JavaScript");
		System.out.println(engine1.getClass().getName());
		System.out.println(engine2.getClass().getName());
		System.out.println(engine1 == engine2);
	}

	static void demo1() throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("print('Hello World!');");
	}

	static void demo2() throws ScriptException {
		Reader reader = new StringReader("print('Hello World!')");
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(reader);
	}

	static void demo3() throws ScriptException, FileNotFoundException {
		Reader reader = new FileReader("src/hello.js");
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(reader);
	}
}
