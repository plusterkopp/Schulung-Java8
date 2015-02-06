package ls;

import static java.lang.System.out;
import util.Features;

public class Application {

	public static void main(String[] args) throws Exception {
		Features.print(Application.class);
		demo();
	}

	static void demo() throws Exception {
		final Runnable r = () -> out.println("Hello");
		Features.print(r.getClass());
		
//		// this will not work:
//		Method m = Application.class.getDeclaredMethod("lambda$0");
//		m.setAccessible(true);
//		m.invoke(null);

//		 final Class<? extends Runnable> cls = r.getClass();
//		 final Constructor<? extends Runnable> ctor =
//		 cls.getDeclaredConstructor();
//		 ctor.setAccessible(true);
//		 final Runnable rr = ctor.newInstance();
//		 rr.run();

	}
}
