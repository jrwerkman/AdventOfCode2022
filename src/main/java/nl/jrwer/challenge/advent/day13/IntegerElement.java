package nl.jrwer.challenge.advent.day13;

class IntegerElement implements Element {
	int value;

	@Override
	public Integer compare(Element other) {
		if(other instanceof IntegerElement) {
			IntegerElement iOther = (IntegerElement) other;
			
			return value - iOther.value;
		} else if (other instanceof ListElement) {
			ListElement thisListElem = new ListElement();
			thisListElem.element.add(this);
			
			return thisListElem.compare(other);
		} else {
			throw new RuntimeException("Uncomparable class: " + other.getClass().getSimpleName());
		}
	}

	@Override
	public void print(StringBuilder sb) {
		sb.append(value).append(',');
		
	}
}