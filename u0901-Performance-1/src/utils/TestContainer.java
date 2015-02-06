package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestContainer extends Test {

	private final List<Test> children = new ArrayList<>();

	public TestContainer(TestContainer container, String name, int times) {
		super(name, times);
		container.add(this);
	}
	public TestContainer(String name, int times)  {
		super(name, times);
	}
	public Iterator<Test> iterator() {
		return children.iterator();
	}

	final void add(final Test test) {
		children.add(test);
		test.setParent(this);
	}
	
	void perform() throws Throwable {
		this.initialize.run();
		for(int i = 0; i < this.times; i++) {
			this.before.run();
			for(Test test : this) {
				test.perform();
			}
			this.after.run();
		}
		this.terminate.run();
	}
}
