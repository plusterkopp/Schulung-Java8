package a3;

import java.io.StringReader;

import scanner.Scanner;
import scanner.Special;
import scanner.Specials;

public class MyParser {
	
	final static Specials specials = new Specials();
	final static Special TIMES = specials.create("*");
	final static Special DIV = specials.create("/");
	final static Special PLUS = specials.create("+");
	final static Special MINUS = specials.create("-");
	final static Special OPEN = specials.create("(");
	final static Special CLOSE = specials.create(")");
	
	public int parse(String s) {
		final Scanner scanner = new Scanner(specials, new StringReader(s));
		scanner.next();
		final int result = this.parseExpression(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected");
		return result;
	}

	public int parseExpression(Scanner scanner) {
		return this.parseAdditive(scanner);
	}

	public int parseAdditive(Scanner scanner) {
		int result = this.parseMultiplicative(scanner);
		while (scanner.current() == PLUS || scanner.current() == MINUS) {
			final boolean plus = scanner.current() == PLUS;
			scanner.next();
			final int value = this.parseMultiplicative(scanner);
			if (plus)
				result += value;
			else
				result -= value;
		}
		return result;
	}

	public int parseMultiplicative(Scanner scanner) {
		int result = this.parseSimple(scanner);
		while (scanner.current() == TIMES || scanner.current() == DIV) {
			final boolean times = scanner.current() == TIMES;
			scanner.next();
			final int value = this.parseSimple(scanner);
			if (times)
				result *= value;
			else
				result /= value;
		}
		return result;
	}
	
	private int parseSimple(Scanner scanner) {
		final int result;
		boolean negate = false;
		if ( scanner.current() == MINUS) {
			negate = true;
			scanner.next();
		}
		if (scanner.current() == OPEN) {
			scanner.next();
			result = this.parseExpression(scanner);
			if (scanner.current() != CLOSE)
				throw new RuntimeException(") expected");
			scanner.next();
		}
		else {
			result = this.parseNumber(scanner);
		}
		return negate ? -result : result;
	}

	private int parseNumber(Scanner scanner) {
		if (! scanner.current().isLiteral()) 
			throw new RuntimeException("number expected");
		if (! scanner.current().asLiteral().isInt())
			throw new RuntimeException("number expected");
		final int result = scanner.current().asLiteral().intValue();
		scanner.next();
		return result;
	}
}
