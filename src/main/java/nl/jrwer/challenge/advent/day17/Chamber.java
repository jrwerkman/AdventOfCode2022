package nl.jrwer.challenge.advent.day17;

class Chamber {
	final State state;
	
	public Chamber(State state) {
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