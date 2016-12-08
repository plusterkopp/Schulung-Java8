package utils;

import util.Catcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SimpleTest extends Test {

	final List<Run> runList = new ArrayList<>();

	public SimpleTest(TestContainer container, String name, int times) {
		super(name, times);
		container.add(this);
	}

	public SimpleTest(String name, int times) {
		super(name, times);
	}

	public Iterator<Test> iterator() {
		return new Iterator<Test>() {
			public boolean hasNext() {
				return false;
			}
			public Test next() {
				throw new RuntimeException();
			}
		};
	}
	
	public Test run(String name, int times, Catcher.Runnable run) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(run);
		this.runList.add(new Run(this, name, times, run));
		return this;
	}

	public Test run(String name, Catcher.Runnable run) {
		return run(name, 1, run);
	}

	public Test run(Catcher.Runnable run) {
		return run(String.valueOf(this.runList.size() + 1), run);
	}
	
	void perform() throws Throwable {
		this.initialize.run();
		for(int i = 0; i < this.times; i++) {
			for(Run run : this.runList) {
				for (int j = 0; j < run.times; j++) {
					this.before.run();
					final long start = System.nanoTime();
					run.run.run();
					final long end = System.nanoTime();
					run.nanos += (end - start);
					this.after.run();
				}
			}
		}
		this.terminate.run();
	}
}
