package appl;

import static java.lang.System.out;

import java.time.LocalDate;
import java.time.Period;

public class Application {

	public static void main(String[] args) {
		{
			out.println("---------------------------");
			Period p1 = Period.ofYears(1);
			Period p2 = Period.ofYears(1).withMonths(6);
			Period p3 = Period.ofYears(1).withMonths(6).withDays(15);
			out.println(p1);
			out.println(p2);
			out.println(p3);
			out.println(p3.getYears());
			out.println(p3.getMonths());
			out.println(p3.getDays());
			p1 = p1.plusMonths(10);
			p1 = p1.plusDays(10);
			p1 = p1.minusDays(3);
			out.println(p1);
			p1 = Period.parse("P1Y6M15D");
			
			Period pp = Period.between( LocalDate.parse( "2015-01-29"), LocalDate.parse( "2015-05-21"));
			out.println(pp);
		}
	}
}
