package nl.jrwer.challenge.advent.day13;

interface Element {
	Integer compare(Element other);
	void print(StringBuilder sb);
}