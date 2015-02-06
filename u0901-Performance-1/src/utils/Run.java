package utils;

import util.Catcher;

class Run {
	public final SimpleTest test;
	public final String name;
	public final int times;
	public final Catcher.Runnable run;
	public long nanos;

	public Run(SimpleTest test, String name, int times, Catcher.Runnable run) {
		this.test = test;
		this.name = name;
		this.times = times;
		this.run = run;
	}
	
	public String fullName() {
		return this.test.fullName() + "." + this.name;
	}
}

