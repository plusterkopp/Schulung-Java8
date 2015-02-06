package appl;

import static appl.Action.exec;

public class Application {

	static void demo1() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println("Thread starts");
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Thread terminates");
			}
		});
		t.start();
		try {
			t.join();
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Thread terminated");
	}

	static void demo2() {
		Thread t = new Thread(() -> {
			System.out.println("Thread starts");
			exec(() -> Thread.sleep(1000));
			System.out.println("Thread terminates");
		});
		t.start();
		exec(() -> t.join());
		exec(t::join);
		System.out.println("Thread terminated");
	}

	public static void main(String[] args) {
		demo1();
		demo2();
	}

}
