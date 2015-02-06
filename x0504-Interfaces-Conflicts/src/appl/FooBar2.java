package appl;

public class FooBar2 implements Foo, Bar {
	public void f() {
		Foo.super.f();
		Bar.super.f();
	}
}
