package org.simple.coollection.test.pojos;

public class Cat extends PetAnimal{
	
	public Cat(String name, String pedigree) {
		super(name, pedigree);
	}

	public String getSpecies() {
		return "cat";
	}
	
}
