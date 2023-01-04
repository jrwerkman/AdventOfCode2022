package nl.jrwer.challenge.advent.day09;

class Rope {
	private Element head;		
	private Chart c = new Chart(this);
	private boolean debug = false;

	public Rope(int size) {
		this.head = new Element(size);
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void move(Motion motion) {
		move(motion.direction, motion.steps);
	}
	
	public void move(Direction direction, int steps) {
		for(int i=0; i<steps; i++) {
			move(direction);
			
			if(debug)
				c.printState();
		}
	}
	
	private void move(Direction direction) {
		head.move(direction, null);
		head.registerPostion();
	}
	
	public Element getTail() {
		Element tail = head;
		
		while(tail.tail != null)
			tail = tail.tail;
		
		return tail;
	}
	
	public Element getHead() {
		return head;
	}
	
	public void printResult() {
		c.printResult();
	}
}