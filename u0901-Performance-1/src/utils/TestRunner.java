package utils;

import static java.lang.System.out;

import java.util.List;


public class TestRunner {

	public static void run(Test test) throws Throwable {
		if (test instanceof SimpleTest) {
			final TestContainer container = new TestContainer("", 1);
			container.add(test);
			test = container;
		}
		test.perform();
		printResult(test);
	}

	private static void printResult(Test test) {
		final int l = maxNameLength(test);
		final int length = l < 5 ? 5 : l;
		final StringBuilder buf = new StringBuilder();
		buf.append('+');
		for (int i = 0; i < length + 24; i++)
			buf.append('-');
		buf.append('+');
		final String line = buf.toString();
		out.println(line);
		out.printf("| %-" + length + "s | %10s | %6s |\n", "Name", "times", "millis");
		out.println(line);
		test.traverse(t -> {
			if (! (t instanceof SimpleTest))
				return; 
			final List<Run> runs = ((SimpleTest)t).runList;
			final int times = times(t);
			runs.forEach(r -> {
				out.printf("| %-" + length + "s | %10d | %6d |\n", 
						r.fullName(), times * r.times, r.nanos / 1_000_000);
			});
		});
		out.println(line);
	}
	
	private static int maxNameLength(Test test) {
		final IntHolder context = new IntHolder();
		test.traverse(t -> { 
			if (! (t instanceof SimpleTest))
				return;
			final List<Run> runs = ((SimpleTest)t).runList;
			runs.forEach(r -> {
				int length = r.fullName().length();
				if (length > context.value)
					context.value = length;
			});
		});
		return context.value;
	}

	private static int times(Test test) {
		return test == null ? 1 : test.times * times(test.getParent());
	}
}
