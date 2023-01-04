package nl.jrwer.challenge.advent.day04;

class Pair {
	Section one, two;
	
	public Pair(String cleaningSections) {
		String[] sections = cleaningSections.split(",");
		
		if(sections.length != 2)
			throw new RuntimeException("Expected 2 sections, got: " + sections.length);
		
		one = new Section(sections[0]);
		two = new Section(sections[1]);
	}
	
	public boolean completeOverlap() {
		return one.completeOverlap(two);
	}
	
	public boolean partialOverlap() {
		return one.partialOverlap(two);
	}
}