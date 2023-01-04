package nl.jrwer.challenge.advent.day11;

import java.util.ArrayList;
import java.util.List;

class SecondMonkey {
	int number;
	List<SecondItem> items = new ArrayList<>();
	
	Operator operator;
	// if -1 use worry level
	long calculateConstant;
	

	int test;
	// Monkeys to throw to
	int monkey1, monkey2;
	
	// inspected items
	int inspectedItems = 0;
	
	public void throwItems(List<SecondMonkey> monkeys) {
		for(int i=0; i<items.size(); i++) {
			SecondItem item = items.get(i);
			inspectedItems++;
			
			// calculate operation
			item.calculateNewWorryLevel(operator, calculateConstant);
			
			// decide where to what monkey to throw item
			if(item.test(this, test))
				throwToMonkey(item, monkeys, monkey1);
			else
				throwToMonkey(item, monkeys, monkey2);
		}
		
		items.clear();
	}
	
	private void throwToMonkey(SecondItem item, List<SecondMonkey> monkeys, int number) {
		for(SecondMonkey monkey : monkeys) {
			if(monkey.number == number) {
				monkey.items.add(item);
				
				return;
			}
		}
	}
}