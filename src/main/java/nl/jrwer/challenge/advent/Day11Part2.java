package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.MultipleLinesInputLoader;

//--- Part Two ---
//
//You're worried you might not ever get your items back. So worried, in fact, that your relief that a monkey's inspection didn't damage an item no longer causes your worry level to be divided by three.
//
//Unfortunately, that relief was all that was keeping your worry levels from reaching ridiculous levels. You'll need to find another way to keep your worry levels manageable.
//
//At this rate, you might be putting up with these monkeys for a very long time - possibly 10000 rounds!
//
//With these new rules, you can still figure out the monkey business after 10000 rounds. Using the same example above:
//
//== After round 1 ==
//Monkey 0 inspected items 2 times.
//Monkey 1 inspected items 4 times.
//Monkey 2 inspected items 3 times.
//Monkey 3 inspected items 6 times.
//
//== After round 20 ==
//Monkey 0 inspected items 99 times.
//Monkey 1 inspected items 97 times.
//Monkey 2 inspected items 8 times.
//Monkey 3 inspected items 103 times.
//
//== After round 1000 ==
//Monkey 0 inspected items 5204 times.
//Monkey 1 inspected items 4792 times.
//Monkey 2 inspected items 199 times.
//Monkey 3 inspected items 5192 times.
//
//== After round 2000 ==
//Monkey 0 inspected items 10419 times.
//Monkey 1 inspected items 9577 times.
//Monkey 2 inspected items 392 times.
//Monkey 3 inspected items 10391 times.
//
//== After round 3000 ==
//Monkey 0 inspected items 15638 times.
//Monkey 1 inspected items 14358 times.
//Monkey 2 inspected items 587 times.
//Monkey 3 inspected items 15593 times.
//
//== After round 4000 ==
//Monkey 0 inspected items 20858 times.
//Monkey 1 inspected items 19138 times.
//Monkey 2 inspected items 780 times.
//Monkey 3 inspected items 20797 times.
//
//== After round 5000 ==
//Monkey 0 inspected items 26075 times.
//Monkey 1 inspected items 23921 times.
//Monkey 2 inspected items 974 times.
//Monkey 3 inspected items 26000 times.
//
//== After round 6000 ==
//Monkey 0 inspected items 31294 times.
//Monkey 1 inspected items 28702 times.
//Monkey 2 inspected items 1165 times.
//Monkey 3 inspected items 31204 times.
//
//== After round 7000 ==
//Monkey 0 inspected items 36508 times.
//Monkey 1 inspected items 33488 times.
//Monkey 2 inspected items 1360 times.
//Monkey 3 inspected items 36400 times.
//
//== After round 8000 ==
//Monkey 0 inspected items 41728 times.
//Monkey 1 inspected items 38268 times.
//Monkey 2 inspected items 1553 times.
//Monkey 3 inspected items 41606 times.
//
//== After round 9000 ==
//Monkey 0 inspected items 46945 times.
//Monkey 1 inspected items 43051 times.
//Monkey 2 inspected items 1746 times.
//Monkey 3 inspected items 46807 times.
//
//== After round 10000 ==
//Monkey 0 inspected items 52166 times.
//Monkey 1 inspected items 47830 times.
//Monkey 2 inspected items 1938 times.
//Monkey 3 inspected items 52013 times.
//
//After 10000 rounds, the two most active monkeys inspected items 52166 and 52013 times. Multiplying these together, the level of monkey business in this situation is now 2713310158.
//
//Worry levels are no longer divided by three after each item is inspected; you'll need to find another way to keep your worry levels manageable. Starting again from the initial state in your puzzle input, what is the level of monkey business after 10000 rounds?


public class Day11Part2 {
	public static void main(String[] args) {
		try {
			Day11Part2 day = new Day11Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Monkey> monkeys = new InputLoader("input-day-11.txt").getInput();

		for(int i=0; i<10000; i++)
			for(Monkey monkey : monkeys)
				monkey.throwItems(monkeys);
		
		long highest = 0, higher = 0;
		for(Monkey monkey : monkeys) {
			int inspectedItems = monkey.inspectedItems;
			System.out.println("Monkey " + monkey.number + ": " + monkey.inspectedItems);
			
			if(inspectedItems > higher) {
				if(inspectedItems > highest) {
					higher = highest;
					highest = inspectedItems;
				} else {
					higher = inspectedItems;
				}
			}
		}
		
		System.out.println("\nhigher: " + higher);
		System.out.println("highest: " + highest);
		System.out.println("\nOutcome: " + higher * highest);
		
		//Answer 66802
	}

	class ItemMonkeyValue {
		Monkey monkey;
		long value;
	}
	
	class Item {
		final long worryLevel;
		public List<ItemMonkeyValue> monkeys = new ArrayList<>();
		
		public Item(long worryLevel) {
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
		
		public boolean test(Monkey monkey, int test) {
			for(ItemMonkeyValue imv : monkeys)
				if(imv.monkey.number == monkey.number)
					return imv.value == 0;
			
			throw new RuntimeException("");
		}	
	}
	
	class Monkey {
		int number;
		List<Item> items = new ArrayList<>();
		
		Operator operator;
		// if -1 use worry level
		long calculateConstant;

		int test;
		// Monkeys to throw to
		int monkey1, monkey2;
		
		// inspected items
		int inspectedItems = 0;
		
		public void throwItems(List<Monkey> monkeys) {
			for(int i=0; i<items.size(); i++) {
				Item item = items.get(i);
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
		
		private void throwToMonkey(Item item, List<Monkey> monkeys, int number) {
			for(Monkey monkey : monkeys) {
				if(monkey.number == number) {
					monkey.items.add(item);
					
					return;
				}
			}
		}
	}
	
	enum Operator {
		SELF, MULTIPLY, ADD;
	}
	
	class InputLoader extends MultipleLinesInputLoader<Monkey> {
		List<Item> items = new ArrayList<Item>();
		
		public InputLoader(String file) {
			super(file, 6);
		}

		protected Monkey handleLines(String[] lines) {
			Monkey monkey = new Monkey();
			
			for(String line : lines)
				processLine(monkey, line);
		
			return monkey;
		}
		
		private void processLine(Monkey monkey, String line) {
			if(line.startsWith("Monkey"))
				monkey.number = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
			
			if(line.contains("Starting items")) 
				processStartingItems(monkey, line);
			
			if(line.contains("Operation:"))
				processOperation(monkey, line);
			
			if(line.contains("Test:"))
				monkey.test = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
			
			if(line.contains("If true:"))
				monkey.monkey1 = Integer.parseInt(line.replaceAll("[^0-9]+", ""));

			if(line.contains("If false:"))
				monkey.monkey2 = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
		}
		
		private void processStartingItems(Monkey monkey, String line) {
			String[] items = line.replaceAll("[^,0-9]+", "").split(",");
			
			for(String item : items) {
				Item itemObject = new Item(Integer.parseInt(item));
				monkey.items.add(itemObject);
				
				this.items.add(itemObject);
			}
		}
		
		private void processOperation(Monkey monkey, String line) {
			if(line.contains("*"))
				monkey.operator = Operator.MULTIPLY;
			if(line.contains("+"))
				monkey.operator = Operator.ADD;
			
			if(count(line, "old") == 2)
				monkey.operator = Operator.SELF;
			else
				monkey.calculateConstant = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
		}
		
		private int count(String input, String word) {
			if(input.length() < word.length())
				return 0;
			else if(input.length() == word.length())
				return input.equals(word) ? 1 : 0;
			
			
			char[] inputArr = input.toCharArray();
			char[] wordArr = word.toCharArray();
			
			int count = 0;
			int wordLength = wordArr.length;
			
			for(int i=0; i<=inputArr.length-wordLength; i++) {
				for(int j=0; j<wordLength; j++) {
					if(inputArr[i + j] != wordArr[j])
						break;
					
					if(j == wordLength - 1)
						count ++;
				}
			}
			
			return count;
		}

		@Override
		protected void postProcess(List<Monkey> objects) {
			for(Item item : items) {
				for(Monkey monkey : objects) {
					ItemMonkeyValue value = new ItemMonkeyValue();
					value.value = item.worryLevel % monkey.test;
					value.monkey = monkey;
					
					item.monkeys.add(value);
				}
			}
			
		}
	}
}
