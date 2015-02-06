package appl;

import static java.lang.System.out;

public class Application {
	public static void main(String[] args) {
		A a = new A(0);
		a.add(1);
		a.multiply(2);
		a.pow(3);
		out.println(a.getValue());
	}
}
