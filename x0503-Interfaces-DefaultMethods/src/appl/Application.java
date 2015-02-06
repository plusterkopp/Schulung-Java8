package appl;


public class Application {
	public static void main(String[] args) {
		Foo foo = new FooImpl();
		foo.f();
		foo.g();
	}
}
