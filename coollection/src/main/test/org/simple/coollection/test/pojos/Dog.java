package org.simple.coollection.test.pojos;

public class Dog extends PetAnimal{

	public Dog(String name, String pedigree) {
		super(name, pedigree);
	}

	public String getSpecies() {
		return "dog";
	}
	
}
