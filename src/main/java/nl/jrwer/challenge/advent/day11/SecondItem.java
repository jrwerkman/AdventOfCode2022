package nl.jrwer.challenge.advent.day11;

import java.util.ArrayList;
import java.util.List;

class SecondItem {
	final long worryLevel;
	public List<ItemMonkeyValue> monkeys = new ArrayList<>();
	
	public SecondItem(long worryLevel) {
		this.worryLevel = worryLevel;
	}
	
	public void calculateNewWorryLevel(Operator operator, long calculateConstant) {
		for(ItemMonkeyValue imv : monkeys) {
			if(operator == Operator.SELF)
				imv.value = (imv.value * imv.value) % imv.monkey.test;

			if(operator == Operator.ADD)
				imv.value = (imv.value + calculateConstant) % imv.monkey.test;
			
			if(operator == Operator.MULTIPLY)
				imv.value = (imv.value * calculateConstant) % imv.monkey.test;
		}
	}
	
	public boolean test(SecondMonkey monkey, int test) {
		for(ItemMonkeyValue imv : monkeys)
			if(imv.monkey.number == monkey.number)
				return imv.value == 0;
		
		throw new RuntimeException("");
	}	
}