package nl.jrwer.challenge.advent.day04;

class Section {
	int start, end;
	
	public Section(String range) {
		String[] split = range.split("-");
		
		if(split.length != 2)
			throw new RuntimeException("Expected 2 number is ragne, got: " + split.length + " ("+range+")");
		
		start = Integer.parseInt(split[0]);
		end = Integer.parseInt(split[1]);
	}

	public boolean completeOverlap(Section compare) {
		if((start >= compare.start && end <= compare.end) ||
				start <= compare.start && end >= compare.end) {
			return true;
		}
		
		return false;
	}
	/*
	 * 3-4 4-6
	 * 4-6 3-4
	 */
	public boolean partialOverlap(Section compare) {
		if(completeOverlap(compare))
			return true;
		
		if((start <= compare.start && end >= compare.start) ||
				(start <= compare.end && end >= compare.end))
			return true;
		
		
		
		return false;
	}
}