package edu.unl.raikes.BinarySearchTreeLab;

//TODO: initializes person object
public class Person implements Comparable<Person> {
	int key;
	String name;

	// TODO: gives person an NUID and name
	Person(int NUID, String name) {
		this.key = NUID;
		this.name = name;
	}

	// TODO: Makes it a string
	public String toString() {
		return "NUID: " + this.key + "  Name: " + name;
	}

	// TODO: comparing keys
	public int compareTo(Person other) {
		return Integer.compare(key, other.key);
	}
}
