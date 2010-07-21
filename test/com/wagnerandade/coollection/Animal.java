package com.wagnerandade.coollection;

public class Animal {
	
	private final String name;
	private final int age;

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String name() {
		return name;
	}
	
	public int age() {
		return age;
	}
	
}