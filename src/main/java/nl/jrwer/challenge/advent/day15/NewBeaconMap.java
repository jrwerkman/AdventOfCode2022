package nl.jrwer.challenge.advent.day15;

import java.util.ArrayList;
import java.util.List;

class NewBeaconMap extends BeaconMap {

	public NewBeaconMap(List<Sensor> sensors) {
		super(sensors);
	}
	
	public Coord findGap(int min, int max) {
		for(int i=min; i<=max; i++) {
			List<Range> ranges = getNotBeacon(i);
			
			int gap = findGapInRanges(ranges, min, max);
			
			if(gap != -1) 
				return new Coord(gap, i);
		}
		
		return new Coord(-1,-1);
	}
	
	private List<Range> getNotBeacon(int y) {
		List<Range> result = new ArrayList<>();
		
		for(Sensor s : sensors)
			processSensor(result, s, y);

		return result;
	}
	
	private int findGapInRanges(List<Range> ranges, int min, int max) {
		Range first = ranges.get(0);
		
		for(int i=1; i<ranges.size(); i++) {
			Range r = ranges.get(i);
			
			if(r.b > min && r.a < max && r.a - first.b > 1) {
				System.out.println("Found: " + (r.a - 1));
				return r.a - 1;
			}
			
			first = r;
		}
		

		
		return -1;
	}
	
}