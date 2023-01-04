package nl.jrwer.challenge.advent.day08;

class TreeLine {
	final int[] trees;
	
	public TreeLine(String input) {
		char[] arr = input.toCharArray();
		this.trees = new int[input.length()];
		
		for(int i=0; i<arr.length; i++)
			// for part 1
			// '0' is 48 in char, so remove 48 to get the corresponding number 
			// Its not really necessary, because the differences between the elements in 
			// the array will not change. We could even keep the chars
			this.trees[i] = (arr[i] - 48); 
	}
}