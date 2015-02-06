package appl;

import static java.lang.System.out;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Application {

	public static void main(String[] args) {
		{
			out.println("---------------------------");
			Date date1 = new Date();
			Instant instant = date1.toInstant();
			out.println(instant);
			instant = instant.plus(Duration.of(1, ChronoUnit.SECONDS));
			Date date2 = Date.from(instant);
			out.println(date2.getTime() - date1.getTime());
		}
		{
			out.println("---------------------------");
			GregorianCalendar calendar1 = (GregorianCalendar)GregorianCalendar.getInstance();
			ZonedDateTime zdt = calendar1.toZonedDateTime();
			out.println(zdt);
			zdt = zdt.plus(Duration.of(1, ChronoUnit.SECONDS));
			GregorianCalendar calendar2 = GregorianCalendar.from(zdt);
			out.println(calendar2.getTime().getTime() - calendar1.getTime().getTime());
		}
		{
			out.println("---------------------------");
			Calendar calendar = GregorianCalendar.getInstance();
			Instant instant = calendar.toInstant();
			out.println(instant);
		}
	}
}