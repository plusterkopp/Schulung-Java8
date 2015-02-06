package appl;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		{
			out.println("---------------------------");
			Instant d1 = Instant.ofEpochMilli(10);
			out.println(d1);
			Instant d2 = Instant.ofEpochSecond(10);
			out.println(d2);
			Instant d3 = Instant.ofEpochSecond(10, 20);
			out.println(d3);
			Instant d4 = Instant.now();
			out.println(d4);
		}
		{
			out.println("---------------------------");
			long millis = Instant.now().toEpochMilli();
			out.println(millis);
		}
		{
			out.println("---------------------------");
			Instant now = Instant.now();
			out.println(now);
			Instant d1 = now.plus(10, ChronoUnit.SECONDS);
			out.println(d1);
			Instant d2 = now.plus(10, ChronoUnit.MINUTES);
			out.println(d2);
			Instant d3 = now.minus(10, ChronoUnit.DAYS);
			out.println(d3);
		}
		{
			out.println("---------------------------");
			Instant now = Instant.now();
			Instant later = now.plus(10, ChronoUnit.SECONDS);
			out.println(now.isAfter(later));
			out.println(now.isBefore(later));
		}
		{
			out.println("---------------------------");
			Instant now = Instant.now();
			Instant result = now.truncatedTo(ChronoUnit.DAYS);
			out.println(result);
		}
		{
			out.println("---------------------------");
			// Instant.now().isSupported(...)
			// Instant now = Instant.now();
			// int year = now.get(ChronoField.YEAR);
			// out.println(second);
			// int second = now.get(ChronoField.SECOND_OF_MINUTE);
			// out.println(second);

		}
		{
			out.println("---------------------------");
			Instant d1 = Instant.ofEpochSecond(10);
			Instant d2 = Instant.ofEpochSecond(20);
			out.println(d1.compareTo(d2));
			out.println(d2.compareTo(d1));
		}
		{
			out.println("---------------------------");
			Instant d1 = Instant.parse("2015-01-20T13:10:05.429Z");
			out.println(d1);
			Instant d2 = Instant.parse("2015-01-20T13:10:05Z");
			out.println(d2);
			// weniger geht nicht...
			try {
				Instant.parse("2015-01-20T13:10Z");
			}
			catch (DateTimeParseException e) {
				out.println("Expected: " + e.getMessage());
			}

		}
		{
			out.println("---------------------------");
			Instant d1 = Instant.ofEpochSecond(10);
			Instant d2 = Instant.ofEpochSecond(10);
			out.println(d1.equals(d2));
			out.println(d1 == d2);
		}
	}
}
