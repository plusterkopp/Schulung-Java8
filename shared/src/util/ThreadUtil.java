package util;

public class ThreadUtil {
	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public static void join(Thread thread) {
		try {
			thread.join();
		}
		catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public static void wait(Object obj) {
		try {
			obj.wait();
		}
		catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
