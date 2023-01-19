package nl.jrwer.challenge.advent.day11;

import java.util.ArrayList;
import java.util.List;

class SecondItem implements IItem {
	final long worryLevel;
	public List<ItemMonkeyValue> monkeys = new ArrayList<>();
	
	public SecondItem(long worryLevel) {
		this.worryLevel = worryLevel;
	}
	
	@Override
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
	
	@Override
	public void reduceWorryLevel(int amount) {
		throw new UnsupportedOperationException();
	}

	public boolean test(int test) {
		return false;
	}	

	@Override
	public boolean test(Object test) {
		if(!(test instanceof Monkey))
			throw new RuntimeException();
		
		Monkey monkey = (Monkey) test;
		
		for(ItemMonkeyValue imv : monkeys)
			if(imv.monkey.number == monkey.number)
				return imv.value == 0;
		
		throw new RuntimeException("");
	}

	@Override
	public long getWorryLevel() {
		return worryLevel;
	}	

	@Override
	public List<ItemMonkeyValue> getItemMonkeyValues() {
		return monkeys;
	}	
}