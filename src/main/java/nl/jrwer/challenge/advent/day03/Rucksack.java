package nl.jrwer.challenge.advent.day03;

class Rucksack extends Priority {
	private char[] firstCompartment;
	private char[] secondCompariment;
	
	public Rucksack(String content) {
		prepare(content);
	}
	
	private void prepare(String content) {
		int length = content.length() / 2;
		
		this.firstCompartment = content.substring(0, length).toCharArray();
		this.secondCompariment = content.substring(length).toCharArray();
		
		if(this.firstCompartment.length != this.secondCompariment.length)
			throw new RuntimeException("Rucksack error: " + content);
	}
	
	@Override
	public char getItem() {
		if(item != 0)
			return item;
		
		for(char first : firstCompartment)
			for(char second : secondCompariment)
				if(first == second) {
					item = first;
					return item;
				}
		
		return 0;
	}
}