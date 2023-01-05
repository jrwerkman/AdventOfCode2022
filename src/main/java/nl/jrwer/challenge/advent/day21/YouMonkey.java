package nl.jrwer.challenge.advent.day21;

import java.util.List;
import java.util.Random;

public class YouMonkey extends NumberMonkey {
	private Long youValue;
	
	public YouMonkey(List<BaseMonkey> monkeys, String id, Long value) {
		super(monkeys, id, value);
		
		this.youValue = value;
	}
	
	public void reset() {
		youValue = super.getValue();
	}
	
	@Override
	public Long getValue() {
		return youValue;
	}
	
	@Override
	public void setValue(Long value) {
		this.youValue = value;
	}
	
	public void changeYouValue() {
		youValue += new Random().nextLong();
	}
	
	@Override
	public YouMonkey clone() {
		return new YouMonkey(monkeys, id, getValue());
	}	
}
