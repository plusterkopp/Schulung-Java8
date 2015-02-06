package appl;

import static java.lang.System.out;

public class Application {
	public static void main(String[] args) {
		A a = new A(5);
		a.add(4);
		a.multiply(3);
		a.pow(2);
		out.println(a.getValue());
	}
}
