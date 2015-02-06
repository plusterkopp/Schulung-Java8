package appl;

public class A {

	private int value;

	public A(int value) {
		this.value = value;
	}
	
	public void add(int v) {
		this.value += v;
	}

	public void multiply(int v) {
		this.value *= v;
	}

	public void pow(int v) {
		this.value = (int) Math.pow(this.value, v);
	}

	public int getValue() {
		return this.value;
	}
}
