package nl.jrwer.challenge.advent.day17;

class Pipe extends Rock {
	public Pipe() {
		super(3, 3, 0);
	}
	
	@Override
	protected Coord[] getCoords() {
		return new Coord[] {new Coord(2,3), new Coord(2,2), new Coord(2,1), new Coord(2,0)};
	}
}