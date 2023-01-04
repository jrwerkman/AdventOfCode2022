package nl.jrwer.challenge.advent.day15;

class Range {
	int a, b;
	int length;
	
	public Range(int a, int b) {
		this.set(a, b);
	}
	
	private void set(int a, int b) {
		this.a = a;
		this.b = b;
		
		this.length = b - a + 1;
	}
	
	public boolean inRange(int x) {
		return a <= x && b >= x;
	}
	
	public boolean overlap(Range range) {
		return (range.a >= a && range.b <= b) 
				|| (range.a < a && (range.b >= a && range.b <= b))
				|| (range.b > b && (range.a >= a && range.a <= b))
				|| (range.a < a && range.b > b);
	}
	
	public void merge(Range range) {
		this.set(range.a <= a ? range.a : a,
				range.b >= b ? range.b : b);
	}
}