package org.simple.coollection.test.pojos;

public class Cat extends PetAnimal{
	
	public Cat(String name, String pedigree) {
		super(name, pedigree);
	}
	public Cat(String name, String pedigree, Double price) {
	       super(name, pedigree, price);
	   }
	 
	public String getSpecies() {
		return "cat";
	}
	
}
