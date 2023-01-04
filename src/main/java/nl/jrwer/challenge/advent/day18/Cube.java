package nl.jrwer.challenge.advent.day18;

class Cube {
	final int x,y,z;
	
	public Cube(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cube) {
			Cube c = (Cube) obj;
			
			return c.x == x && c.y == y && c.z == z;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return x +","+y +"," + z;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}