package nl.jrwer.challenge.advent.day11;

import java.util.ArrayList;
import java.util.List;

class SecondInputLoader extends InputLoader {
	List<IItem> items = new ArrayList<IItem>();
	
	public SecondInputLoader(String file) {
		super(file);
	}

	@Override
	protected void createItems(Monkey monkey, String[] items) {
		for(String item : items) {
			SecondItem itemObject = new SecondItem(Integer.parseInt(item));
			monkey.items.add(itemObject);
			
			this.items.add(itemObject);
		}
	}
	
	@Override
	protected void processOperation(Monkey monkey, String line) {
		if(line.contains("*"))
			monkey.operator = Operator.MULTIPLY;
		if(line.contains("+"))
			monkey.operator = Operator.ADD;
		
		if(count(line, "old") == 2)
			monkey.operator = Operator.SELF;
		else
			monkey.calculateConstant = Integer.parseInt(line.replaceAll("[^0-9]+", ""));
	}

	@Override
	protected void postProcess(List<Monkey> objects) {
		for(IItem item : items) {
			for(Monkey monkey : objects) {
				ItemMonkeyValue value = new ItemMonkeyValue();
				value.value = item.getWorryLevel() % monkey.test;
				value.monkey = monkey;
				
				item.getItemMonkeyValues().add(value);
			}
		}
		
	}
}