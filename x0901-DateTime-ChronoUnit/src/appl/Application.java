package appl;

import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

public class Application {

	public static void main(String[] args) {
		{
			out.println("---------------------------");
			for (ChronoUnit u : ChronoUnit.values()) {
				out.println(u);
			}
			out.println(ChronoUnit.SECONDS.compareTo(ChronoUnit.YEARS));
			out.println(ChronoUnit.SECONDS.compareTo(ChronoUnit.DAYS));
			out.println(ChronoUnit.SECONDS.compareTo(ChronoUnit.MINUTES));
		}
	}
}
