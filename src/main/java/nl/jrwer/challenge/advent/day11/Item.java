package nl.jrwer.challenge.advent.day11;

class Item {
	long worryLevel;
	
	public void calculateNewWorryLevel(Operator operator, long calculateConstant) {
		long amount = calculateConstant == -1 ? worryLevel : calculateConstant;

		if(operator == Operator.ADD)
			worryLevel += amount;
		
		if(operator == Operator.MULTIPLY)
			worryLevel *= amount;
	}
	
	public void reduceWorryLevel(int amount) {
		if(amount <= 1)
			return;
		
		worryLevel /= amount;
	}	
	
	public boolean test(int test) {
		return worryLevel % test == 0;
	}	
}