package nl.jrwer.challenge.advent.day21;

import java.util.ArrayList;
import java.util.List;

public class SecondCalculator extends Calculator {
	private final YouMonkey youMonkey;
	
	public SecondCalculator(List<BaseMonkey> monkeys) {
		super(monkeys);
		
		this.youMonkey = findYouMonkey();
	}
	
	private YouMonkey findYouMonkey() {
		for(BaseMonkey m : this.monkeys)
			if(m.is(YOU))
				return (YouMonkey) m;
		
		return null;
	}
	
	@Override
	public RootMonkey getRootMonkey() {
		return (RootMonkey) rootMonkey;
	}
	
	public YouMonkey getYouMonkey() {
		return youMonkey;
	}
	
	public Long findYouMonkeyValue() {
		markChangedMonkeys();
		
		System.out.println("Searching for you value");
		searchAndSetYouValue();
		
		System.out.println("Reseting value and doing the final calculation");
		reset();
		calculateValues();
		
		return youMonkey.getValue();
	}
	
	private void searchAndSetYouValue() {
		RootMonkey rootMonkey = getRootMonkey();
		
		Long currentValue = rootMonkey.changed == Changed.LEFT ? 
				rootMonkey.getRightValue() : rootMonkey.getLeftValue();
		
		BaseMonkey currentMonkey = rootMonkey.getChangedMonkey(); 
		
		while(!currentMonkey.is(YOU)) {
			CalculatorMonkey currentCalcMonkey = (CalculatorMonkey) currentMonkey;
			
			currentValue = calculateValue(currentValue, currentCalcMonkey);
			currentMonkey = currentCalcMonkey.getChangedMonkey();
		}

		youMonkey.setValue(currentValue);
	}
	
	public Long calculateValue(Long currentValue, CalculatorMonkey monkey) { 
		if(monkey.changed == Changed.LEFT) {
			if(monkey.operator == Operator.ADD) // 150 = x + 10
				return currentValue - monkey.getRightValue(); 
			if(monkey.operator == Operator.SUBTRACT) // 150 = x - 10
				return currentValue + monkey.getRightValue();
			if(monkey.operator == Operator.DIVIDE) // 150 = x / 2
				return currentValue * monkey.getRightValue();
			if(monkey.operator == Operator.MULTIPLY) // 150 = x * 50
				return currentValue / monkey.getRightValue();
		}
		
		if(monkey.changed == Changed.RIGHT) {
			if(monkey.operator == Operator.ADD) // 150 = 10 + x
				return currentValue - monkey.getLeftValue(); 
			if(monkey.operator == Operator.SUBTRACT) // 150 = 160 - x
				return monkey.getLeftValue() - currentValue;
			if(monkey.operator == Operator.DIVIDE) // 150 = 300 / x
				return monkey.getLeftValue() / currentValue;
			if(monkey.operator == Operator.MULTIPLY) // 150 = 50 * x
				return currentValue / monkey.getLeftValue();
		}
		
		throw new RuntimeException("Could not calculate");
	}
	
	private void markChangedMonkeys() {
		System.out.println("Calculating values");
		calculateValues();

		System.out.println("Retrieving the calculator monkeys");
		List<CalculatorMonkey> calculatorMonkeys = cloneCalculatorMonkeys();

		// reset all calculated values
		System.out.println("Reseting all value of calculator monkeys");
		reset();
		// increment to recalculate and see changing monkeys
		System.out.println("Randomly change the youmonkey value");
		youMonkey.changeYouValue();
		
		// recalculate
		calculateValues();
		System.out.println("Calculating values again, so there can be checked what values are changed.");
		
		
		markChangedMonkeys(calculatorMonkeys);
		System.out.println("Mark the (Calculator) monkey that have value changed after the previes calculation");
	}
	
	private void markChangedMonkeys(List<CalculatorMonkey> calculatorMonkeys) {
		for(BaseMonkey baseMonkey : monkeys) 
			for(CalculatorMonkey calculatorMonkey : calculatorMonkeys)
				markIfChanged(baseMonkey, calculatorMonkey);
	}
	
	public void markIfChanged(BaseMonkey firstBase, CalculatorMonkey second) {
		if(!(firstBase instanceof CalculatorMonkey) || !firstBase.equals(second))
			return;
		
		CalculatorMonkey first = (CalculatorMonkey) firstBase;
		
		long firstLeft = first.getLeftValue();
		long firstRight = first.getRightValue();
		long secondLeft = second.getLeftValue();
		long secondRight = second.getRightValue();
		
		if(firstLeft == secondLeft && firstRight == secondRight)
			return;
		
		if(firstLeft != secondLeft && firstRight != secondRight)
			throw new RuntimeException("Didnt expect both left and right to be changed"); 
		else if(firstLeft != secondLeft)
			first.changed = Changed.LEFT;
		else if(firstRight != secondRight)
			first.changed = Changed.RIGHT;
	}
	
	private List<CalculatorMonkey> cloneCalculatorMonkeys() {
		List<CalculatorMonkey> calculatorMonkeys = new ArrayList<>();
		
		for(BaseMonkey m : monkeys) 
			if(m instanceof CalculatorMonkey)
				calculatorMonkeys.add(((CalculatorMonkey) m).clone());
		
		return calculatorMonkeys;
	}
	
	private void reset() {
		for(BaseMonkey m : monkeys) {
			if(m instanceof CalculatorMonkey)
				m.setValue(null);
		}
	}
	
}
