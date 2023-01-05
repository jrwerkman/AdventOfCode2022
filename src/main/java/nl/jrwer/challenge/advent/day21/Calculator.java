package nl.jrwer.challenge.advent.day21;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Calculator {
	public static final String ROOT = "root";
	public static final String YOU = "humn";
	
	protected final List<BaseMonkey> monkeys;
	protected final BaseMonkey rootMonkey;
	
	public Calculator(List<BaseMonkey> monkeys) {
		this.monkeys = monkeys;
		this.rootMonkey = findRootMonkey();
	}
	
	private BaseMonkey findRootMonkey() {
		for(BaseMonkey m : this.monkeys)
			if(m.is(ROOT))
				return m;
		
		return null;
	}
	
	public BaseMonkey getRootMonkey() {
		return rootMonkey;
	}
	
	public void calculateValues() {
		Queue<BaseMonkey> q = new ArrayDeque<>();
		q.addAll(monkeys);
		
		while(!q.isEmpty()) {
			BaseMonkey head = q.remove();
			
			if(!head.findValue())
				q.add(head);
		}
	}
}
