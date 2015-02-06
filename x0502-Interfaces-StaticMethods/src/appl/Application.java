package appl;

public class Application {
	public static void main(String[] args) {
		Foo foo = new FooImpl();
		System.out.println(Foo.x);
		Foo.printX();
		foo.f();
	}
}
