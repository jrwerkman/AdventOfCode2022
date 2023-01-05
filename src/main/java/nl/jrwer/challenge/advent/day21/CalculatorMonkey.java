package nl.jrwer.challenge.advent.day21;

import java.util.List;

public class CalculatorMonkey extends BaseMonkey {
	protected final Operator operator;

	protected final String monkeyLeftOfOperator;
	protected final String monkeyRightOfOperator;
	
	// false is left changed, true is right changed
	protected Changed changed = Changed.NONE;
	
	private Long leftValue;
	private Long rightValue;
	
	public CalculatorMonkey(List<BaseMonkey> monkeys, String id, Operator op, String monkeyLeft, String monkeyRight) {
		super(monkeys, id);
		
		this.operator = op;
		this.monkeyLeftOfOperator = monkeyLeft;
		this.monkeyRightOfOperator = monkeyRight;
	}
	
	public boolean findValue() {
		if(getValue() != null)
			return true;
		
		BaseMonkey l = getMonkey(monkeyLeftOfOperator);
		BaseMonkey r = getMonkey(monkeyRightOfOperator);
		
		if(l.getValue() == null || r.getValue() == null)
			return false;
		
		leftValue = l.getValue();
		rightValue = r.getValue();
		
		setValue(calculate(l.getValue(), r.getValue(), operator));
		
		return true;
	}
	
	protected Long calculate(Long l, Long r, Operator op) {
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
	
	public Long getLeftValue() {
		return leftValue;
	}
	
	public Long getRightValue() {
		return rightValue;
	}
	
	public BaseMonkey getMonkey(Changed changed) {
		if(changed == Changed.LEFT)
			return getMonkey(monkeyLeftOfOperator);

		if(changed == Changed.RIGHT)
			return getMonkey(monkeyRightOfOperator);

		throw new RuntimeException("Can only get monkey from left or right, not: " + changed.name());
	}
	
	public BaseMonkey getChangedMonkey() {
		return getMonkey(changed);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(id).append(": ");
		sb.append(monkeyLeftOfOperator).append(' ')
				.append(operator.name()).append(' ')
				.append(monkeyRightOfOperator);
		
		if(getValue() != null)
			sb.append(" --> ").append(getValue());
		
		return sb.toString();
	}

	@Override
	public CalculatorMonkey clone() {
		CalculatorMonkey clone = new CalculatorMonkey(monkeys, id, operator, monkeyLeftOfOperator, monkeyRightOfOperator);
		
		clone.leftValue = this.leftValue;
		clone.rightValue = this.rightValue;
		
		return clone;
	}
}
