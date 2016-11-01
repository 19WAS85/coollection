package org.simple.coollection.test.pojos;

public class Dog extends PetAnimal {

	public Dog(String name, String pedigree) {
		super(name, pedigree);
	}

	public Dog(String name, String pedigree, Double price) {
		super(name, pedigree, price);
	}

	public String getSpecies() {
		return "dog";
	}

}
