package nl.jrwer.challenge.advent.day11;

import java.util.List;

public interface IItem {
	void calculateNewWorryLevel(Operator operator, long calculateConstant);
	void reduceWorryLevel(int amount);
	boolean test(Object test);
	long getWorryLevel();
	List<ItemMonkeyValue> getItemMonkeyValues();
}
