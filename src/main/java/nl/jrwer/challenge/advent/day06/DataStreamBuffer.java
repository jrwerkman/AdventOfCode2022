package nl.jrwer.challenge.advent.day06;

class DataStreamBuffer {
	final char[] stream;
	
	public DataStreamBuffer(String input) {
		stream = input.toCharArray();
	}
	
	
	public int firstMarkerAtferPosition() {
		return firstMarkerAtferPosition(4);
	}
	
	public int firstMarkerAtferPosition(int length) {
		for(int i=0; i<stream.length-length; i++) {
			if(sequencesUnique(i, length))
				return i + length;
		}
		
		return -1;
	}
	
	private boolean sequencesUnique(int index, int length) {
		for(int i = index; i<index+length; i++)
			for(int j=index; j<index+length; j++)
				if(i!=j && stream[i] == stream[j])
					return false;

		return true;
	}
}