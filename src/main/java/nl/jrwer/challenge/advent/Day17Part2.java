package nl.jrwer.challenge.advent;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//--- Part Two ---
//
//The elephants are not impressed by your simulation. They demand to know how tall the tower will be after 1000000000000 rocks have stopped! Only then will they feel confident enough to proceed through the cave.
//
//In the example above, the tower would be 1514285714288 units tall!
//
//How tall will the tower be after 1000000000000 rocks have stopped?

class Day17Part2 extends Day17 {
	public static void main(String[] args) {
		try {
			Day17Part2 day = new Day17Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		Sequence sequence = new InputLoader("input-day-17.txt").getInput();
		StateOptimized state = new StateOptimized(1000000000000L, sequence);

		long start = System.currentTimeMillis();
		ChamberOptimized c = new ChamberOptimized(state);
		System.out.println(c.calculate());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
		
		// Answer: 1562536022966
	}
	
	class Key {
		final long rocksIndex;
		final long sequenceIndex;
		
		public Key(long rocksIndex, long sequenceIndex) {
			this.rocksIndex = rocksIndex;
			this.sequenceIndex = sequenceIndex;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Key) {
				Key c = (Key) obj;
				
				return rocksIndex == c.rocksIndex && sequenceIndex == c.sequenceIndex;
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			return rocksIndex +","+sequenceIndex;
		}
		
		@Override
		public int hashCode() {
			return toString().hashCode();
		}
	}
	
	class Cycle {
		int occurences = 0;
		
		long cycleStartStone = 0;
		long cycleStartTop = 0;
		
		public void setStart(State state) {
			cycleStartStone = state.rockCount;
			cycleStartTop = state.top;
		}
	}
	
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
	
	class ChamberOptimized {
		final StateOptimized state;

		public ChamberOptimized(StateOptimized state) {
			this.state = state;
		}
		
		
		public long calculate() {
			while(state.hasNextRock())
				fall(state.nextRock());
			
			return state.top;
		}
		
		private void fall(Rock rock) {
			while(state.isFalling()) {
				Direction dir = state.nextDirection();
				
				if(state.canMove())
					rock.move(dir);
				
				if(state.canFall()) 
					rock.move(Direction.DOWN);
			}
			
			state.addRock();
			state.setTop();
			
			if(state.cycleStone == 0) {
				Cycle cycle = state.getCycle();
				
				if(cycle.occurences == 1) 
					cycle.setStart(state);
				
				if(cycle.occurences == 2)
					state.processSequence(cycle);
			}
		}
		
		public void printHeight() {
			StringBuilder sb = new StringBuilder();
			
			for(long y=state.top; y>0; y--) {
				for(long x=0; x<state.width; x++)
					sb.append(state.get(x, y));
				
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}		
	}
	
}
