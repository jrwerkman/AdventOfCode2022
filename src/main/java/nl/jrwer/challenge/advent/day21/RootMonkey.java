package nl.jrwer.challenge.advent.day21;

import java.util.List;

public class RootMonkey extends CalculatorMonkey {

	public RootMonkey(List<BaseMonkey> monkeys, String id, Operator op, String monkeyLeft, String monkeyRight) {
		super(monkeys, id, op, monkeyLeft, monkeyRight);
	}
	
	@Override
	protected Long calculate(Long l, Long r, Operator op) {
		return l.longValue() == r.longValue() ? 0L : 1L;
	}
}
