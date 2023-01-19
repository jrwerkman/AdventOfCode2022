package nl.jrwer.challenge.advent.day25;

import java.util.HashMap;
import java.util.Map;

public class Snafu {
	public static final Map<Character, Long> SNAFU_DIGITS = Map.of(
			'2', 2L,
			'1', 1L,
			'0', 0L,
			'-', -1L,
			'=', -2L); 
	
	private String snafu = null;
	private long decimal = 0L;
	
	public Snafu(String snafu) {
		this.snafu = snafu;
	}
	
	public Snafu(long decimal) {
		this.decimal = decimal;
	}
	
	public long getDecimal() {
		if(decimal > 0L)
			return decimal;
		
		char[] snafuDigits = snafu.toCharArray();
		
		for(int i=snafuDigits.length - 1; i>=0; i--) 
			decimal += SNAFU_DIGITS.get(snafuDigits[i]) *
					Math.pow(5, (snafuDigits.length - 1) - i);

		return decimal;
	}
	
	Map<Integer, SnafuDigit> map = new HashMap<>(); 
	
	public String getSnafu() {
		if(snafu != null)
			return snafu;
		
		int position = (int) (Math.log(decimal) / Math.log(5));
		long number = decimal;

		for(int i=position; i>=0; i--) 
			number = toSnafu(number, i);

		StringBuilder sb = new StringBuilder();
		for(int i=position; i>=0; i--)
			sb.append(map.get(i).digit);

		snafu = sb.toString();
		return snafu;
	}
	
	private long toSnafu(long number, int position) {
		long weight = (long) Math.pow(5, position);
		long amount = number / weight;
		long rest = number % weight;
		
		if(rest < 0) {
			if(more(rest, position)) {
				amount--;
				rest = number - (amount * weight);
			}
		} else if(rest > 0) {
			if(more(rest, position)) {
				amount++;
				rest = number - (amount * weight);
			}
		} 

		map.put(position, SnafuDigit.get((int) amount));
		
		return rest;
	}
	
	private boolean more(long rest, int position) {
		long max = 0;
		
		for(int i=1; i<position; i++) 
			max += (2 * Math.pow(5, i));
		
		return Math.abs(rest) > max; 
	}
}
