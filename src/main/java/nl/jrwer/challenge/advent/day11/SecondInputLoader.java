package nl.jrwer.challenge.advent.day11;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.MultipleLinesInputLoader;

class SecondInputLoader extends MultipleLinesInputLoader<SecondMonkey> {
	List<SecondItem> items = new ArrayList<SecondItem>();
	
	public SecondInputLoader(String file) {
		super(file, 6);
	}

	protected SecondMonkey handleLines(String[] lines) {
		SecondMonkey monkey = new SecondMonkey();
		
		for(String line : lines)
			processLine(monkey, line);
	
		return monkey;
	}
	
	private void processLine(SecondMonkey monkey, String line) {
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
	
	private void processStartingItems(SecondMonkey monkey, String line) {
		String[] items = line.replaceAll("[^,0-9]+", "").split(",");
		
		for(String item : items) {
			SecondItem itemObject = new SecondItem(Integer.parseInt(item));
			monkey.items.add(itemObject);
			
			this.items.add(itemObject);
		}
	}
	
	private void processOperation(SecondMonkey monkey, String line) {
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
	protected void postProcess(List<SecondMonkey> objects) {
		for(SecondItem item : items) {
			for(SecondMonkey monkey : objects) {
				ItemMonkeyValue value = new ItemMonkeyValue();
				value.value = item.worryLevel % monkey.test;
				value.monkey = monkey;
				
				item.monkeys.add(value);
			}
		}
		
	}
}