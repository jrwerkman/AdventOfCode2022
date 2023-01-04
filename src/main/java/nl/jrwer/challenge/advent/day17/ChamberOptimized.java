package nl.jrwer.challenge.advent.day17;

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