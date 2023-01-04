package nl.jrwer.challenge.advent.day13;
class Pair {
	int number;
	ListElement left = new ListElement();
	ListElement right = new ListElement();
	
	public Pair(String[] input, int number) {
		left = new ListElement(input[0]);
		right = new ListElement(input[1]);

		this.number = number;
	}
	
	public boolean compare() {
		int results = left.compare(right);
		
		return results < 0;
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();

		left.print(sb);
		sb.append('\n');
		
		right.print(sb);
		sb.append("\n\n");
		
		System.out.println(sb.toString());
	}
}