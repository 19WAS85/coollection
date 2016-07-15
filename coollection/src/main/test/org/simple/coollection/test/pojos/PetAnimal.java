package org.simple.coollection.test.pojos;

public abstract class PetAnimal implements Animal {
	String pedigree;
	String name;
	public PetAnimal(){
		
	}
	public PetAnimal(String argName, String argPedigree) {
		this();
		this.name = argName;
		this.pedigree = argPedigree;
	}

	public abstract String getSpecies();

	public String getPedigree() {
		return pedigree;
	}

	public void setPedigree(String pedigree) {
		this.pedigree = pedigree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
