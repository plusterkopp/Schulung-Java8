package util;

import java.io.File;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JSLoader {
	public static ScriptEngine load(String... directories) {
		final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		for (String directoty : directories) {
			new File(directoty).listFiles((dir, name) -> {
				if (!name.endsWith(".js"))
					return false;
				try {
					engine.eval(new FileReader(dir + "/" + name));
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
				return false;
			});
		}
		return engine;
	}
}
