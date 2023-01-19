package nl.jrwer.challenge.advent.day11;

import java.util.ArrayList;
import java.util.List;

class Monkey {
	int number;
	List<IItem> items = new ArrayList<>();
	
	Operator operator;
	// if -1 use worry level
	long calculateConstant;

	int test;
	// Monkeys to throw to
	int monkey1, monkey2;
	
	// inspected items
	int inspectedItems = 0;
	
	// divide worry levels
	final int divider;
	
	public Monkey(int divider) {
		this.divider = divider;
	}
	
	public void throwItems(List<Monkey> monkeys) {
		for(int i=0; i<items.size(); i++) {
			IItem item = items.get(i);
			inspectedItems++;
			
			// calculate operation
			item.calculateNewWorryLevel(operator, calculateConstant);
			
			// monkey gets bored, divide by 3
			if(divider > 0)
				item.reduceWorryLevel(divider);
			
			// decide where to what monkey to throw item
			if(item.test(divider < 0 ? this : test))
				throwToMonkey(item, monkeys, monkey1);
			else
				throwToMonkey(item, monkeys, monkey2);
		}
		
		items.clear();
	}
	
	private void throwToMonkey(IItem item, List<Monkey> monkeys, int number) {
		for(Monkey monkey : monkeys) {
			if(monkey.number == number) {
				monkey.items.add(item);
				
				return;
			}
		}
	}
}