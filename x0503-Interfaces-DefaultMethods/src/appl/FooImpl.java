package appl;

public class FooImpl implements Foo {
	public void f() {
		System.out.println("f()");
	}
	@Override
	public void g() {
		System.out.println("g()");
	}
}
