package nl.jrwer.challenge.advent.day22.cube;

import nl.jrwer.challenge.advent.day22.Type;

public class CubeCoord {
	public final int x,y;
	public final Type type;
	
	public CubeCoord(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
