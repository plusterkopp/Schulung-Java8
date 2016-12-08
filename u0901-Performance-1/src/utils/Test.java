package utils;

import util.Catcher;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class Test implements Iterable<Test> {

	final String name;
	final int times;
	private TestContainer parent;

	private final Catcher.Runnable empty = () -> { };

	Catcher.Runnable initialize = empty;
	Catcher.Runnable before = empty;
	Catcher.Runnable after = empty;
	Catcher.Runnable terminate = empty;

	public Test(String name, int times) {
		Objects.requireNonNull(name);
		if (times < 0)
			throw new IllegalArgumentException();
		this.name = name;
		this.times = times;
	}

	void setParent(TestContainer parent) {
		if (this.parent != null)
			throw new AssertionError();
		this.parent = parent;
	}
	
	public TestContainer getParent() {
		return parent;
	}

	public Test initialize(Catcher.Runnable initialize) {
		this.initialize = initialize;
		return this;
	}

	public Test before(Catcher.Runnable before) {
		this.before = before;
		return this;
	}

	public Test after(Catcher.Runnable after) {
		this.after = after;
		return this;
	}

	public Test terminate(Catcher.Runnable terminate) {
		this.terminate = terminate;
		return this;
	}

	public String fullName() {
		if (this.parent == null)
			return this.name;
		final String parentName = this.parent.fullName();
		if (parentName.isEmpty())
			return this.name;
		return parentName + "." + this.name;
	}

	public String toString() {
		return this.name;
	}
	
	abstract void perform() throws Throwable;
	
	public void traverse(Consumer<Test> consumer) {
		consumer.accept(this);
		for (Test child : this)
			child.traverse(consumer);
	}
}
