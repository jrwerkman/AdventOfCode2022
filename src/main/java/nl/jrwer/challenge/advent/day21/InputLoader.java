package nl.jrwer.challenge.advent.day21;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Monkey>{
	
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Monkey handleLine(String line) {
		Monkey m = null;
		String[] split = line.split(":");
		String id = split[0];
		
		String[] formula = split[1].trim().split(" ");;
		
		if(formula.length == 3) {
			m = new Monkey(getList(), id, 
					Operator.getOperator(formula[1].charAt(0)), 
					formula[0].trim(), 
					formula[2].trim());
		} else {
			m = new Monkey(getList(), id, 
					Long.parseLong(formula[0].trim()));
		}
			
		return m;
	}
}