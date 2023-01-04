package nl.jrwer.challenge.advent.day15;

class Sensor extends Coord {
	final Beacon beacon;
	final int distance;
	
	public Sensor(int x, int y, Beacon beacon) {
		super(x, y, Type.SENSOR);
		
		this.beacon = beacon;
		this.distance = calcDistance();
	}
	
	public int calcDistance() {
		int xDistance = Math.abs(x - beacon.x);
		int yDistance = Math.abs(y - beacon.y);
		
		return (int) (Math.abs(yDistance) + Math.abs(xDistance));
	}
	
	public int getDistanceToBeacon() {
		return distance;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("compare");
		return super.equals(obj) || beacon.equals(obj);
	}		
}