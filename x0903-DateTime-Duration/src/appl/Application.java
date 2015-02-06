package appl;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		// Duration: Zeitdauer
		{
			out.println("---------------------------");
			Duration d = Duration.of(10, ChronoUnit.MINUTES);
			out.println(d);
			out.println(Duration.of(10, ChronoUnit.SECONDS));
			out.println(Duration.of(10, ChronoUnit.MILLIS));
		}
		{
			out.println("---------------------------");
			out.println(Duration.ofDays(10));
			out.println(Duration.ofHours(10));
			out.println(Duration.ofMinutes(10));
		}
		{
			out.println("---------------------------");
			Duration d = Duration.of(10_007, ChronoUnit.MILLIS);
			out.println(d.getSeconds());
			out.println(d.getNano());
		}
		{
			out.println("---------------------------");
			Duration d = Duration.of(10, ChronoUnit.MINUTES);
			Duration d1 = d.plus(Duration.of(5, ChronoUnit.MINUTES));
			out.println(d1);
			Duration d2 = d.plus(5, ChronoUnit.MINUTES);
			out.println(d2);
		}
		{
			out.println("---------------------------");
			Duration d = Duration.between(Instant.now(), Instant.now().plus(10, ChronoUnit.MINUTES));
			out.println(d);
		}
		{
			out.println("---------------------------");
			Duration d = Duration.parse("PT15M");
			out.println(d);
			try {
				Duration.parse("PT15");
			}
			catch(DateTimeParseException e) {
				out.println("Expected: " + e.getMessage());
			}
		}
		{
			out.println("---------------------------");
			Duration d = ChronoUnit.MINUTES.getDuration();
			out.println(d);
			out.println(ChronoUnit.SECONDS.getDuration());
			out.println(ChronoUnit.FOREVER.getDuration());
		}
		{
			out.println("---------------------------");
			Duration d1 = Duration.ofDays(10);
			Duration d2 = Duration.ofDays(10);
			out.println(d1.equals(d2));
			out.println(d1 == d2);
		}
	}
}
