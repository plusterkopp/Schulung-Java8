package appl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static java.lang.System.out;

public class Application {

	// javac -parameters
	
	public static void main(String[] args) {
		{
			out.println("-------------------------------------");
			for (Method m : Object.class.getMethods()) {
				out.print(m.getReturnType().getSimpleName() + " ");
				out.print(m.getName()+ "(");
				Class<?>[] types = m.getParameterTypes();
				for (int i = 0; i < types.length; i++) {
					if (i > 0)
						out.print(", ");
					out.print(types[i].getSimpleName() + " p" + i);
				}
				out.println(")");
			}
		}
		{
			out.println("-------------------------------------");
//			for (Method m : Foo.class.getDeclaredMethods()) {
			for (Method m : Object.class.getMethods()) {
				out.print(m.getReturnType().getSimpleName() + " ");
				out.print(m.getName()+ "(");
				Parameter[] parameters = m.getParameters();
				for (int i = 0; i < parameters.length; i++) {
					Parameter p = parameters[i];
					if (i > 0)
						out.print(", ");
					out.print(p.getType().getSimpleName() + " " + p.getName());
				}
				out.println(")");
			}
			
		}
	}
}
