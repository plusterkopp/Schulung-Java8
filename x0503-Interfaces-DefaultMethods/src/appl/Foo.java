package appl;

public interface Foo {
	void f();
	default void g() {
		System.out.println("g");
	}
//	default final void h() {
//		System.out.println("h");
//	}
}
