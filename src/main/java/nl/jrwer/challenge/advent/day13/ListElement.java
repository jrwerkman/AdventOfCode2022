package nl.jrwer.challenge.advent.day13;

import java.util.ArrayList;
import java.util.List;

class ListElement implements Element {
	List<Element> element = new ArrayList<>();
	
	public ListElement() {
		
	}
	
	public ListElement(String input) {
		parseInput(this, input, 1);
	}
	
	private int parseInput(ListElement parentElement, String input, int index) {
		for(int i=index; i<input.length() - 1; i++) {
			if(input.charAt(i) == '[') {
				ListElement childElement = new ListElement();
				parentElement.element.add(childElement);
				
				i = parseInput(childElement, input, i + 1);
			} else if(input.charAt(i) == ']') {
				return i;
			} else if(input.charAt(i) == ',') {
				// do nothing
			} else {
				IntegerElement element = new IntegerElement();
				i = getDigit(element, input, i);
				
				parentElement.element.add(element);
			}
		}
		
		return input.length();
	}
	
	private int getDigit(IntegerElement element, String input, int i) {
		int charsForDigit = 0;
		
		for(;i<input.length()-1; i++) {
			if(input.charAt(i) == ']' || input.charAt(i) == ',')
				break;
			
			charsForDigit++;
		}
		
		element.value = Integer.parseInt(input.substring(i - charsForDigit, i));
		
		return i;
	}		
	
	public Element get(int i) {
		return element.get(i);
	}
	
	public int size() {
		return element.size();
	}

	@Override
	public Integer compare(Element other) {
		ListElement lOther;
		
		if(other instanceof IntegerElement) {
			lOther = new ListElement();
			lOther.element.add(other);
		} else if (other instanceof ListElement) {
			lOther = (ListElement) other;
		} else {
			throw new RuntimeException("Uncomparable class: " + other.getClass().getSimpleName());
		}
		
		int till = size() < lOther.size() ? size() : lOther.size();
		
		for(int i=0; i<till; i++) {
			Element elem1 = get(i);
			Element elem2 = lOther.get(i);
			
			Integer result = elem1.compare(elem2);
			
			if(result != null && result != 0)
				return result;
		}

		return size() - lOther.size();
	}

	@Override
	public void print(StringBuilder sb) {
		sb.append('[');
		for(Element e : element) {
			e.print(sb);
		}
		sb.append(']');
	}
}