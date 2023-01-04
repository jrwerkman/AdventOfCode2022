package nl.jrwer.challenge.advent.day09;

class Motion {
	Direction direction;
	int steps;
	
	public Motion(String input) {
		String[] split = input.split(" ");
		
		if(split.length != 2)
			throw new RuntimeException("Wrong input: " + input);
		
		direction = Direction.getDirection(split[0].charAt(0));
		steps = Integer.parseInt(split[1]);
	}
}