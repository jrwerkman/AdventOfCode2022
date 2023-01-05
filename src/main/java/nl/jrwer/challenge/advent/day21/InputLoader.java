package nl.jrwer.challenge.advent.day21;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<BaseMonkey>{
	final boolean part2;
	
	public InputLoader(String file) {
		this(file, false);
	}

	public InputLoader(String file, boolean part2) {
		super(file);
		
		this.part2 = part2;
	}

	@Override
	protected BaseMonkey handleLine(String line) {
		BaseMonkey m = null;
		String[] split = line.split(":");
		String id = split[0];
		
		String[] formula = split[1].trim().split(" ");;
		
		if(part2 && id.equals(Calculator.ROOT))
			m = new RootMonkey(getList(), id, 
					Operator.EQUAL, 
					formula[0].trim(), 
					formula[2].trim());
		else if(part2 && id.equals(Calculator.YOU)) {
			m = new YouMonkey(getList(), id, 
					Long.parseLong(formula[0].trim()));
		} else if(formula.length == 3) {
			m = new CalculatorMonkey(getList(), id, 
					Operator.getOperator(formula[1].charAt(0)), 
					formula[0].trim(), 
					formula[2].trim());
		} else {
			m = new NumberMonkey(getList(), id, 
					Long.parseLong(formula[0].trim()));
		}
			
		return m;
	}
}