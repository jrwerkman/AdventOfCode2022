package nl.jrwer.challenge.advent.day22.cube;

public class CubeSideConnections {
	public static final CubeSideConnection[] EXAMPLE = new CubeSideConnection[] {
			new CubeSideConnection(new CubeSidePair(1, Side.LEFT), new CubeSidePair(3, Side.TOP)),
			new CubeSideConnection(new CubeSidePair(1, Side.BOTTOM), new CubeSidePair(4, Side.TOP)),
			new CubeSideConnection(new CubeSidePair(1, Side.RIGHT), new CubeSidePair(6, Side.RIGHT)),
			new CubeSideConnection(new CubeSidePair(1, Side.TOP), new CubeSidePair(2, Side.TOP)),
			
			new CubeSideConnection(new CubeSidePair(2, Side.RIGHT), new CubeSidePair(3, Side.LEFT)),
			new CubeSideConnection(new CubeSidePair(2, Side.LEFT), new CubeSidePair(6, Side.BOTTOM)),
			new CubeSideConnection(new CubeSidePair(2, Side.BOTTOM), new CubeSidePair(5, Side.BOTTOM)),
			
			new CubeSideConnection(new CubeSidePair(3, Side.RIGHT), new CubeSidePair(4, Side.LEFT)),
			new CubeSideConnection(new CubeSidePair(3, Side.BOTTOM), new CubeSidePair(5, Side.LEFT)),
			
			new CubeSideConnection(new CubeSidePair(4, Side.BOTTOM), new CubeSidePair(5, Side.TOP)),
			new CubeSideConnection(new CubeSidePair(4, Side.RIGHT), new CubeSidePair(6, Side.TOP)),

			new CubeSideConnection(new CubeSidePair(5, Side.RIGHT), new CubeSidePair(6, Side.LEFT)),
	};
	
	public static final CubeSideConnection[] CHALLENGE = new CubeSideConnection[] {
			new CubeSideConnection(new CubeSidePair(1, Side.LEFT), new CubeSidePair(4, Side.LEFT)),
			new CubeSideConnection(new CubeSidePair(1, Side.BOTTOM), new CubeSidePair(3, Side.TOP)),
			new CubeSideConnection(new CubeSidePair(1, Side.RIGHT), new CubeSidePair(2, Side.LEFT)),
			new CubeSideConnection(new CubeSidePair(1, Side.TOP), new CubeSidePair(6, Side.LEFT)),
			
			new CubeSideConnection(new CubeSidePair(2, Side.RIGHT), new CubeSidePair(5, Side.RIGHT)),
			new CubeSideConnection(new CubeSidePair(2, Side.TOP), new CubeSidePair(6, Side.BOTTOM)),
			new CubeSideConnection(new CubeSidePair(2, Side.BOTTOM), new CubeSidePair(3, Side.RIGHT)),
			
			new CubeSideConnection(new CubeSidePair(3, Side.LEFT), new CubeSidePair(4, Side.TOP)),
			new CubeSideConnection(new CubeSidePair(3, Side.BOTTOM), new CubeSidePair(5, Side.TOP)),
			
			new CubeSideConnection(new CubeSidePair(4, Side.BOTTOM), new CubeSidePair(6, Side.TOP)),
			new CubeSideConnection(new CubeSidePair(4, Side.RIGHT), new CubeSidePair(5, Side.LEFT)),

			new CubeSideConnection(new CubeSidePair(5, Side.BOTTOM), new CubeSidePair(6, Side.RIGHT)),
	};
	
}
