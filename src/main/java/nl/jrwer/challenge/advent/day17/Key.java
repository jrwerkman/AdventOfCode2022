package nl.jrwer.challenge.advent.day17;

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