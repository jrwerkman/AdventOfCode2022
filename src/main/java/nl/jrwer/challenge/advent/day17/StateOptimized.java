package nl.jrwer.challenge.advent.day17;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class StateOptimized extends State {
	long cycleStone = 0;
	long cycleAddToTop = 0;
	Map<Key, Cycle> keys = new HashMap<>();
	
	public StateOptimized(long fallingRocks, Sequence sequence) {
		super(fallingRocks, sequence);
	}
	
	public Key getKey() {
		return new Key(rocks.index, sequence.index);
	}
	
	public Cycle getCycle() {
		Key key = getKey();
		Cycle cycle;

		if(keys.containsKey(key)) {
			cycle = keys.get(key);
			cycle.occurences++;
		} else {
			cycle = new Cycle();
			keys.put(key, cycle);
		}
		
		return cycle;
	}
	
	public void moveRockUp(long amount) {
		for(long x=0; x<width; x++) {
			Map<Long, Boolean> newColumn = new HashMap<>();
			Map<Long, Boolean> column = chamber.get(x);
			
			for(Entry<Long, Boolean> entry : column.entrySet()) 
				newColumn.put(entry.getKey() + amount, entry.getValue());
			
			chamber.put(x, newColumn);
		}			
	}
	
	public void processSequence(Cycle cycle) {
		long oldTop = top;
		
		cycleStone = rockCount - cycle.cycleStartStone;
		cycleAddToTop = top - cycle.cycleStartTop;
		
		long rocksLeft = fallingRocks - rockCount;
		long cyclesToAdd = rocksLeft / cycleStone;
		
		rockCount += (cyclesToAdd * cycleStone);
		top += (cyclesToAdd * cycleAddToTop);
		
		moveRockUp(top - oldTop);
	}
}