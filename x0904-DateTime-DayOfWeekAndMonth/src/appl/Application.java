package appl;

import static java.lang.System.out;

import java.time.DayOfWeek;
import java.time.Month;

public class Application {

	public static void main(String[] args) {
		{
			out.println("---------------------------");
			for (DayOfWeek d : DayOfWeek.values()) {
				out.print(d + " ");
			}
			out.println();
			
			out.println(DayOfWeek.of(1) == DayOfWeek.MONDAY);
			out.println(DayOfWeek.of(7) == DayOfWeek.SUNDAY);
			
			out.println(DayOfWeek.SUNDAY.getValue() == 7);
			out.println(DayOfWeek.MONDAY.getValue() == 1);

			out.println(DayOfWeek.MONDAY.plus(2) == DayOfWeek.WEDNESDAY);
			out.println(DayOfWeek.WEDNESDAY.minus(2) == DayOfWeek.MONDAY);
			
			out.println(DayOfWeek.MONDAY.compareTo(DayOfWeek.WEDNESDAY) == -2);
			out.println(DayOfWeek.WEDNESDAY.compareTo(DayOfWeek.MONDAY) == 2);
		}
		{
			out.println("---------------------------");
			for (Month m : Month.values()) {
				out.print(m + " ");
			}
			out.println();
			
			out.println(Month.of(1) == Month.JANUARY);
			out.println(Month.of(12) == Month.DECEMBER);
			
			out.println(Month.JANUARY.getValue() == 1);
			out.println(Month.DECEMBER.getValue() == 12);

			out.println(Month.DECEMBER.minus(11) == Month.JANUARY);
			out.println(Month.JANUARY.plus(11) == Month.DECEMBER);
			
			out.println(Month.JANUARY.compareTo(Month.MARCH) == -2) ;
			out.println(Month.MARCH.compareTo(Month.JANUARY) == 2) ;

			out.println(Month.FEBRUARY.firstMonthOfQuarter() == Month.JANUARY);
			out.println(Month.DECEMBER.firstMonthOfQuarter() == Month.OCTOBER);

			out.println(Month.MARCH.firstDayOfYear(true) == 61);
			out.println(Month.MARCH.firstDayOfYear(false) == 60);
			
			out.println(Month.FEBRUARY.length(true) == 29);
			out.println(Month.FEBRUARY.length(false) == 28);

			for (Month m : Month.values()) {
				out.print(m.minLength() + " ");
			}
			out.println();
			for (Month m : Month.values()) {
				out.print(m.maxLength() + " ");
			}
			out.println();
			
		}
	}
}