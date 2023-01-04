package nl.jrwer.challenge.advent.day15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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