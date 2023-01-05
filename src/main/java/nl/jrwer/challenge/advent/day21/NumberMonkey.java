package nl.jrwer.challenge.advent.day21;

import java.util.List;

public class NumberMonkey extends BaseMonkey {
	
	public NumberMonkey(List<BaseMonkey> monkeys, String id, Long value) {
		super(monkeys, id, value);
	}
	
	@Override
	public boolean findValue() {
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(id).append(": ");
		sb.append(getValue());

		return sb.toString();
	}

	@Override
	public NumberMonkey clone() {
		return new NumberMonkey(monkeys, id, getValue());
	}
}
