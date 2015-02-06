package appl;

import static java.lang.System.out;

import java.lang.reflect.Field;

import util.Features;

public class Application {

	static Object readField(Object obj, String name) {
		try {
			final Field field = obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field.get(obj);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		out.println("------------------------------------------");
		{
			WorkerA w1 = new WorkerA() {
				public void work(int v) {
					out.println("w1 : " + v);
				}
			};
			WorkerA w2 = new WorkerA() {
				public void work(int v) {
					out.println("w2 : " + v);
				}
			};
			WorkerA w3 = w1.andThen(w2);
			out.println(w1);
			out.println(w2);
			out.println(w3);
			w3.work(42);
			Features.print(w3.getClass());
			out.println(readField(w3, "first") == w1);
			out.println(readField(w3, "second") == w2);

		}
		out.println("------------------------------------------");
		{
			WorkerB w1 = new WorkerB() {
				public void work(int v) {
					out.println("w1 : " + v);
				}
			};
			WorkerB w2 = new WorkerB() {
				public void work(int v) {
					out.println("w2 : " + v);
				}
			};
			WorkerB w3 = w1.andThen(w2);
			out.println(w1);
			out.println(w2);
			out.println(w3);
			w3.work(42);
			Features.print(w3.getClass());
			out.println(readField(w3, "this$0") == w1);
			out.println(readField(w3, "val$other") == w2);

		}
		out.println("------------------------------------------");
		{
			WorkerC w1 = v -> out.println("w1 : " + v);
			WorkerC w2 = v -> out.println("w2 : " + v);
			WorkerC w3 = w1.andThen(w2);
			out.println(w1);
			out.println(w2);
			out.println(w3);
			w3.work(42);
			Features.print(w3.getClass());
			out.println(readField(w3, "arg$1") == w1);
			out.println(readField(w3, "arg$2") == w2);
		}
		out.println("------------------------------------------");
		{
			WorkerC w1 = v -> out.println("w1 : " + v);
			WorkerC w2 = v -> out.println("w2 : " + v);
			w1.andThen(w2).work(42);
		}
	}
}
