package nl.jrwer.challenge.advent.day11;

import java.util.List;

import nl.jrwer.challenge.advent.input.MultipleLinesInputLoader;

class InputLoader extends MultipleLinesInputLoader<Monkey>{
	protected final int divider;
	
	public InputLoader(String file, int divider) {
		super(file, 6);
		
		this.divider = divider;
	}

	protected InputLoader(String file) {
		this(file, -1);
	}

	protected Monkey handleLines(String[] lines) {
		Monkey monkey = new Monkey(divider);
		
		for(String line : lines)
			processLine(monkey, line);
	
		return monkey;
	}
	
	protected void processLine(Monkey monkey, String line) {
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
		
		createItems(monkey, items);
	}
	
	protected void createItems(Monkey monkey, String[] items) {
		for(String item : items) {
			Item itemObject = new Item();
			itemObject.worryLevel = Integer.parseInt(item);
			
			monkey.items.add(itemObject);
		}
	}
	
	protected void processOperation(Monkey monkey, String line) {
		if(line.contains("*"))
			monkey.operator = Operator.MULTIPLY;
		if(line.contains("+"))
			monkey.operator = Operator.ADD;
		
		if(count(line, "old") == 2)
			monkey.calculateConstant = -1;
		else
			monkey.calculateConstant = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
	}
	
	protected int count(String input, String word) {
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
	}
}
