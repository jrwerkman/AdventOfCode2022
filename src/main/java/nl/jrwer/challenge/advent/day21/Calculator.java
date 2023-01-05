package nl.jrwer.challenge.advent.day21;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Calculator {
	private final List<Monkey> monkeys;
	private final Monkey rootMonkey;
	
	public Calculator(List<Monkey> monkeys) {
		this.monkeys = monkeys;
		this.rootMonkey = getRootMonkey();
	}
	
	private Monkey getRootMonkey() {
		for(Monkey m : this.monkeys)
			if(m.is("root"))
				return m;
		
		return null;
	}
	
	public Long getValueRootMonkey() {
		Queue<Monkey> q = new ArrayDeque<>();
		q.addAll(monkeys);
		
		while(!q.isEmpty()) {
			Monkey head = q.remove();
			
			if(!head.findValue())
				q.add(head);
		}
		
		return rootMonkey.getValue();
	}
}
