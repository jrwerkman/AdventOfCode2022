package nl.jrwer.challenge.advent.day22.cube;

public class CubeSideConnection {
	final CubeSidePair one, two;
	
	public CubeSideConnection(CubeSidePair one, CubeSidePair two) {
		this.one = one;
		this.two = two;
	}
	
	public Side getCurrentSide(CubeSide side) {
		return getCurrentPair(side.number).side;
	}
	
	public CubeSidePair getCurrentPair(CubeSide side) {
		return getCurrentPair(side.number);
	}
	
	public CubeSidePair getCurrentPair(int number) {
		return one.cube == number ? one : two;
	}

	public Side getOtherSide(CubeSide side) {
		return getOtherPair(side.number).side;
	}
	
	public CubeSidePair getOtherPair(CubeSide side) {
		return getOtherPair(side.number);
	}
	
	public CubeSidePair getOtherPair(int number) {
		return one.cube == number ? two : one;
	}
}
