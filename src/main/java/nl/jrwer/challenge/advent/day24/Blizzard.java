package nl.jrwer.challenge.advent.day24;

public class Blizzard {
	final int x, y;
	final Direction direction;
	
	public Blizzard(Direction direction, int x, int y) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Blizzard atTime(int time, int maxX, int maxY) {
		return new Blizzard(direction, getNextX(time, maxX), getNextY(time, maxY));
	}

	private int getNextX(int time, int maxX) {
		int steps = time % (maxX - 2); 
		
		if(direction == Direction.RIGHT)
			return (x + steps >= maxX - 1) ? ((x + steps) - maxX) + 2 : x + steps;
		else if(direction == Direction.LEFT)
			return (x - steps <= 0) ? (maxX + (x - steps)) - 2 : x - steps;
		else
			return x;
	}
	
	private int getNextY(int time, int maxY) {
		int steps = time % (maxY - 2); 

		if(direction == Direction.DOWN)
			return (y + steps >= maxY - 1) ? ((y + steps) - maxY) + 2 : y + steps;
		else if(direction == Direction.UP)
			return (y - steps <= 0) ? (maxY + (y - steps)) - 2 : y - steps;
		else
			return y;
	}
}
