package util;


public class Log {
	
	public static void method() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		header(elements[2].getMethodName());
	}
	public static void header(String text) {
		final String LINE = "+------------------------------------------------";
		System.out.println(LINE);
		System.out.println("+ " + text);
		System.out.println(LINE);
	}
	public static void thread(String text) {
		System.out.println(text + " Thread: " + Thread.currentThread().getId());
	}
}
