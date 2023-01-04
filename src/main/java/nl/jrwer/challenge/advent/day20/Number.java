package nl.jrwer.challenge.advent.day20;

class Number {
	final int originalIndex;
	final long value;
	
	public Number(int originalIndex, long value) {
		this.originalIndex = originalIndex;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Number) {
			Number c = (Number) obj;
			
			return c.originalIndex == originalIndex && c.value == value;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "originalIndex: " + originalIndex + ", value: " + value;
	}
}