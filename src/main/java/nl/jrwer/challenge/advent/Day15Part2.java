package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

//--- Part Two ---
//
//Your handheld device indicates that the distress signal is coming from a beacon nearby. The distress beacon is not detected by any sensor, but the distress beacon must have x and y coordinates each no lower than 0 and no larger than 4000000.
//
//To isolate the distress beacon's signal, you need to determine its tuning frequency, which can be found by multiplying its x coordinate by 4000000 and then adding its y coordinate.
//
//In the example above, the search space is smaller: instead, the x and y coordinates can each be at most 20. With this reduced search area, there is only a single position that could have a beacon: x=14, y=11. The tuning frequency for this distress beacon is 56000011.
//
//Find the only possible position for the distress beacon. What is its tuning frequency?


class Day15Part2 extends Day15 {
	public static void main(String[] args) {
		try {
			Day15Part2 day = new Day15Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Sensor> sensors = new InputLoader("input-day-15.txt").getInput();

		long start = System.currentTimeMillis();
		NewBeaconMap map = new NewBeaconMap(sensors);
		Coord c = map.findGap(0,4000000);
		long result = (4000000L * (long) c.x) + (long) c.y;
		
		System.out.println("Result coords: " + c);
		System.out.println("Tuning frequency: " + result);
		long end = System.currentTimeMillis();

		System.out.println("Solution took " + (end - start) + " ms");
		
		// Answer: 13172087230812
	}
	
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
}
