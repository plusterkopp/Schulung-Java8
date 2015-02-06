package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Features {
	public static void print(Class<?> cls) {
		System.out.println(cls.getName() + " (" + cls.getDeclaringClass() + ")");
		final Constructor<?>[] constructors = cls.getDeclaredConstructors();
		if (constructors.length > 0) {
			System.out.println("\tConstructors");
			for (Constructor<?> c : constructors)
				System.out.println("\t\t" + c);
		}
		final Field[] fields = cls.getDeclaredFields();
		if (fields.length > 0) {
			System.out.println("\tFields");
			for (Field f : fields)
				System.out.println("\t\t" + f);
		}
		final Method[] methods = cls.getDeclaredMethods();
		if (methods.length > 0) {
			System.out.println("\tMethods");
			for (Method m : methods)
				System.out.println("\t\t" + m);
		}
		final Class<?>[] classes = cls.getDeclaredClasses();
		if (classes.length > 0) {
			System.out.println("\tClasses");
			for (Class<?> c : classes)
				System.out.println("\t\t" + c);
		}
	}
}
