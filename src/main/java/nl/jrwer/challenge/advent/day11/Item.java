package nl.jrwer.challenge.advent.day11;

import java.util.List;

class Item implements IItem {
	long worryLevel;
	
	@Override
	public void calculateNewWorryLevel(Operator operator, long calculateConstant) {
		long amount = calculateConstant == -1 ? worryLevel : calculateConstant;

		if(operator == Operator.ADD)
			worryLevel += amount;
		
		if(operator == Operator.MULTIPLY)
			worryLevel *= amount;
	}
	
	@Override
	public void reduceWorryLevel(int amount) {
		if(amount <= 1)
			return;
		
		worryLevel /= amount;
	}	
	
	@Override
	public boolean test(Object obj) {
		if(!(obj instanceof Integer))
			throw new RuntimeException();
		
		Integer test = (Integer) obj;
		
		return worryLevel % test == 0;
	}

	@Override
	public long getWorryLevel() {
		return worryLevel;
	}

	@Override
	public List<ItemMonkeyValue> getItemMonkeyValues() {
		throw new UnsupportedOperationException();
	}	
}