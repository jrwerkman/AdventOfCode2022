package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 15: Beacon Exclusion Zone ---
//
//You feel the ground rumble again as the distress signal leads you to a large network of subterranean tunnels. You don't have time to search them all, but you don't need to: your pack contains a set of deployable sensors that you imagine were originally built to locate lost Elves.
//
//The sensors aren't very powerful, but that's okay; your handheld device indicates that you're close enough to the source of the distress signal to use them. You pull the emergency sensor system out of your pack, hit the big button on top, and the sensors zoom off down the tunnels.
//
//Once a sensor finds a spot it thinks will give it a good reading, it attaches itself to a hard surface and begins monitoring for the nearest signal source beacon. Sensors and beacons always exist at integer coordinates. Each sensor knows its own position and can determine the position of a beacon precisely; however, sensors can only lock on to the one beacon closest to the sensor as measured by the Manhattan distance. (There is never a tie where two beacons are the same distance to a sensor.)
//
//It doesn't take long for the sensors to report back their positions and closest beacons (your puzzle input). For example:
//
//Sensor at x=2, y=18: closest beacon is at x=-2, y=15
//Sensor at x=9, y=16: closest beacon is at x=10, y=16
//Sensor at x=13, y=2: closest beacon is at x=15, y=3
//Sensor at x=12, y=14: closest beacon is at x=10, y=16
//Sensor at x=10, y=20: closest beacon is at x=10, y=16
//Sensor at x=14, y=17: closest beacon is at x=10, y=16
//Sensor at x=8, y=7: closest beacon is at x=2, y=10
//Sensor at x=2, y=0: closest beacon is at x=2, y=10
//Sensor at x=0, y=11: closest beacon is at x=2, y=10
//Sensor at x=20, y=14: closest beacon is at x=25, y=17
//Sensor at x=17, y=20: closest beacon is at x=21, y=22
//Sensor at x=16, y=7: closest beacon is at x=15, y=3
//Sensor at x=14, y=3: closest beacon is at x=15, y=3
//Sensor at x=20, y=1: closest beacon is at x=15, y=3
//
//So, consider the sensor at 2,18; the closest beacon to it is at -2,15. For the sensor at 9,16, the closest beacon to it is at 10,16.
//
//Drawing sensors as S and beacons as B, the above arrangement of sensors and beacons looks like this:
//
//               1    1    2    2
//     0    5    0    5    0    5
// 0 ....S.......................
// 1 ......................S.....
// 2 ...............S............
// 3 ................SB..........
// 4 ............................
// 5 ............................
// 6 ............................
// 7 ..........S.......S.........
// 8 ............................
// 9 ............................
//10 ....B.......................
//11 ..S.........................
//12 ............................
//13 ............................
//14 ..............S.......S.....
//15 B...........................
//16 ...........SB...............
//17 ................S..........B
//18 ....S.......................
//19 ............................
//20 ............S......S........
//21 ............................
//22 .......................B....
//
//This isn't necessarily a comprehensive map of all beacons in the area, though. Because each sensor only identifies its closest beacon, if a sensor detects a beacon, you know there are no other beacons that close or closer to that sensor. There could still be beacons that just happen to not be the closest beacon to any sensor. Consider the sensor at 8,7:
//
//               1    1    2    2
//     0    5    0    5    0    5
//-2 ..........#.................
//-1 .........###................
// 0 ....S...#####...............
// 1 .......#######........S.....
// 2 ......#########S............
// 3 .....###########SB..........
// 4 ....#############...........
// 5 ...###############..........
// 6 ..#################.........
// 7 .#########S#######S#........
// 8 ..#################.........
// 9 ...###############..........
//10 ....B############...........
//11 ..S..###########............
//12 ......#########.............
//13 .......#######..............
//14 ........#####.S.......S.....
//15 B........###................
//16 ..........#SB...............
//17 ................S..........B
//18 ....S.......................
//19 ............................
//20 ............S......S........
//21 ............................
//22 .......................B....
//
//This sensor's closest beacon is at 2,10, and so you know there are no beacons that close or closer (in any positions marked #).
//
//None of the detected beacons seem to be producing the distress signal, so you'll need to work out where the distress beacon is by working out where it isn't. For now, keep things simple by counting the positions where a beacon cannot possibly be along just a single row.
//
//So, suppose you have an arrangement of beacons and sensors like in the example above and, just in the row where y=10, you'd like to count the number of positions a beacon cannot possibly exist. The coverage from all sensors near that row looks like this:
//
//                 1    1    2    2
//       0    5    0    5    0    5
// 9 ...#########################...
//10 ..####B######################..
//11 .###S#############.###########.
//
//In this example, in the row where y=10, there are 26 positions where a beacon cannot be present.
//
//Consult the report from the sensors you just deployed. In the row where y=2000000, how many positions cannot contain a beacon?


class Day15 {
	public static void main(String[] args) {
		try {
			Day15 day = new Day15();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Sensor> sensors = new InputLoader("input-day-15.txt").getInput();

		long start = System.currentTimeMillis();
		BeaconMap map = new BeaconMap(sensors);
//		System.out.println("Occupied possition: " + map.positionNotBeacon(10));
		System.out.println("Occupied possition: " + map.positionNotBeacon(2000000));
		
		long end = System.currentTimeMillis();
		System.out.println("Solution took " + (end - start) + " ms");
		
		// Answer: 5112034
	}
	
	class BeaconMap {
		protected List<Sensor> sensors;
		
		public BeaconMap(List<Sensor> sensors) {
			this.sensors = sensors;
		}
		
		public int positionNotBeacon(int y) {
			List<Range> result = new ArrayList<>();
			
			for(Sensor s : sensors)
				processSensor(result, s, y);

			return calculate(result) - removeBeacons(y, result);
		}
		
		private int calculate(List<Range> result) {
			int amount = 0;

			for(Range r: result) {
				amount += r.length;
				System.out.println(r.a + "," + r.b);
			}
			
			return amount;
		}
		
		private int removeBeacons(int y, List<Range> result) {
			List<Integer> removed = new ArrayList<>();
			
			for(Range r : result) {
				for(Sensor s : sensors) {
					removeBeacon(removed, s, r, y);
					removeBeacon(removed, s.beacon, r, y);
				}
			}
			
			return removed.size();
		}
		
		public void removeBeacon(List<Integer> removed, Coord c, Range r, int y) {
			if(c.y == y && r.inRange(c.x)) 
				if(!removed.contains(c.x))
					removed.add(c.x);
		}

		public void processSensor(List<Range> result, Sensor sensor, int y) {
			int distanceToBeacon = sensor.getDistanceToBeacon();
			int distanceFromSensor = Math.abs(y - sensor.y);
			int amountToAdd = distanceToBeacon - distanceFromSensor;
			
			if(amountToAdd > 0)
				add(result, sensor.x, amountToAdd);
		}

		public void add(List<Range> result, int x, int amountToAdd) {
			int a = x - amountToAdd;
			int b = x + amountToAdd;
			
			Range newRange = new Range(a, b);

			for (Iterator<Range> iterator = result.iterator(); iterator.hasNext();) {
				Range range = iterator.next();

				if (range.overlap(newRange)) {
					newRange.merge(range);
					iterator.remove();
				}
			}

			result.add(newRange);
		}
	}  

	class Range {
		int a, b;
		int length;
		
		public Range(int a, int b) {
			this.set(a, b);
		}
		
		private void set(int a, int b) {
			this.a = a;
			this.b = b;
			
			this.length = b - a + 1;
		}
		
		public boolean inRange(int x) {
			return a <= x && b >= x;
		}
		
		public boolean overlap(Range range) {
			return (range.a >= a && range.b <= b) 
					|| (range.a < a && (range.b >= a && range.b <= b))
					|| (range.b > b && (range.a >= a && range.a <= b))
					|| (range.a < a && range.b > b);
		}
		
		public void merge(Range range) {
			this.set(range.a <= a ? range.a : a,
					range.b >= b ? range.b : b);
		}
	}
	
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
	
	class Beacon extends Coord {
		public Beacon(int x, int y) {
			super(x, y, Type.BEACON);
		}
	}
	
	class Coord {
		final int x, y;
		final Type type;
		
		public Coord(int x, int y) {
			this(x, y, null);
		}
		
		protected Coord(int x, int y, Type type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Sensor) {
				Sensor s = (Sensor) obj;
				return (x == s.x && y == s.y) || (x == s.beacon.x && y == s.beacon.y);
			} else if(obj instanceof Coord) {
				Coord c = (Coord) obj;
				return x == c.x && y == c.y;
			} else {
				throw new RuntimeException("Cannot compare Coord with: " + obj.getClass().getSimpleName());
			}
		}
		
		@Override
		public String toString() {
			return x + "," + y;
		}
	}
	
	enum Type {
		SENSOR, 
		BEACON; 
	}
	
	class InputLoader extends BasicInputLoader<Sensor>{
		
		public static final String REGEX = "[^-0-9]+";
		
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Sensor handleLine(String line) {
			String[] split = line.split(":");
			String[] sensorS = split[0].split(",");
			String[] beaconS = split[1].split(",");
			
			Beacon beacon = new Beacon(getInt(beaconS[0]), getInt(beaconS[1]));
			Sensor sensor = new Sensor(getInt(sensorS[0]), getInt(sensorS[1]), beacon);

			return sensor;
		}
		
		private int getInt(String input) {
			return Integer.parseInt(input.replaceAll(REGEX, ""));
		}

	}
}
