package nl.jrwer.challenge.advent.day21;

import java.util.List;

abstract class BaseMonkey {
	protected final List<BaseMonkey> monkeys;
	protected final String id;
	private Long value;
	
	protected BaseMonkey(List<BaseMonkey> monkeys, String id) {
		this(monkeys, id, null);
	}
	
	protected BaseMonkey(List<BaseMonkey> monkeys, String id, Long value) {
		this.monkeys = monkeys;
		this.id = id;
		this.value = value;
	}
	
	public boolean is(String compareId) {
		return this.id.equals(compareId);
	}
	
	public abstract boolean findValue();
	public abstract BaseMonkey clone();

	protected BaseMonkey getMonkey(String id) {
		for(BaseMonkey m : monkeys)
			if(m.is(id))
				return m;
		
		throw new RuntimeException("Monkey not in collection: " + id);
	}
	
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BaseMonkey) {
			BaseMonkey c = (BaseMonkey) obj;
			
			return c.id.equals(id);
		}
		
		return false;
	}
}
