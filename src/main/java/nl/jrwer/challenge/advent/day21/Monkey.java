package nl.jrwer.challenge.advent.day21;

import java.util.List;

public class Monkey {
	private final List<Monkey> monkeys;
	private final String id;
	private Long value = null;
	
	private final String monkeyLeftOfOperator;
	private final Operator operator;
	private final String monkeyRightOfOperator;
	
	public Monkey(List<Monkey> monkeys, String id, Long value) {
		this.monkeys = monkeys;
		this.id = id;
		this.value = value;
		
		this.operator = null;
		this.monkeyLeftOfOperator = null;
		this.monkeyRightOfOperator = null;
	}

	public Monkey(List<Monkey> monkeys, String id, Operator op, String monkeyLeft, String monkeyRight) {
		this.monkeys = monkeys;
		this.id = id;
		
		this.operator = op;
		this.monkeyLeftOfOperator = monkeyLeft;
		this.monkeyRightOfOperator = monkeyRight;
	}
	
	public boolean is(String compareId) {
		return this.id.equals(compareId);
	}
	
	public boolean findValue() {
		if(getValue() != null)
			return true;
		
		Monkey l = getMonkey(monkeyLeftOfOperator);
		Monkey r = getMonkey(monkeyRightOfOperator);
		
		if(l.value == null || r.value == null)
			return false;
		
		value = calculate(l.value, r.value, operator);
		
		return true;
	}
	
	private Long calculate(Long l, Long r, Operator op) {
		switch(op) {
		case ADD:
			return l + r;
		case SUBTRACT:
			return l - r;
		case DIVIDE:
			return l / r;
		case MULTIPLY:
			return l * r;
		default:
			throw new RuntimeException("Unsupported operator: " + op.name());
		}
	}
	
	private Monkey getMonkey(String id) {
		for(Monkey m : monkeys)
			if(m.is(id))
				return m;
		
		throw new RuntimeException("Monkey not in collection: " + id);
	}
	
	public Long getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Monkey) {
			Monkey c = (Monkey) obj;
			
			return c.id.endsWith(id);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(id).append(": ");
		
		if(monkeyLeftOfOperator == null) {
			sb.append(value);
		} else {
			sb.append(monkeyLeftOfOperator).append(' ')
					.append(operator.name()).append(' ')
					.append(monkeyRightOfOperator);
		}
		
		if(value != null) {
			sb.append(" --> ").append(value);
		}
		
		return sb.toString();
	}
}
