package appl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Application {

	static Field getField(Class<?> cls, String name) {
		if (cls == Object.class)
			return null;
		try {
			Field field = cls.getDeclaredField(name);
			return field;
		}
		catch(Exception e) {
			cls = cls.getSuperclass();
			return getField(cls, name);
		}
	}
	static Object readField(Object obj, String name) {
		try {
			final Field field = getField(obj.getClass(), name);
			field.setAccessible(true);
			return field.get(obj);
		}
		catch (Exception e) {
			//out.println(e);
			return null;
		}
	}

	static void inspectStream(Stream<?> stream) {
		
		// java.util.stream.ReferencePipeline
		// java.util.stream.AbstractPipeline
		
		out.println("Stream " + stream.getClass().getName()); // + " == " + stream.getClass().getSuperclass().getName());
		out.println("\tsource            " + readField(stream, "sourceStage"));
		out.println("\tprevious          " + readField(stream, "previousStage"));
		out.println("\tnext              " + readField(stream, "nextStage"));
		out.println("\tsourceSpliterator " + readField(stream, "sourceSpliterator"));
		out.println("\tval$predicate     " + readField(stream, "val$predicate"));
		out.println("\tval$mapper        " + readField(stream, "val$mapper"));
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("World");
		Stream<String> s1 = list.stream();
		Stream<String> s2 = s1.filter(s -> s.startsWith("H"));
		Stream<Integer> s3 = s2.map(s -> s.length());
		s3.forEach(i -> out.println(i));
		inspectStream(s1);
		inspectStream(s2);
		inspectStream(s3);
	}
}
