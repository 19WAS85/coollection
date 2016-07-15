package org.simple.coollection.test.pojos;

import java.util.ArrayList;
import java.util.List;

public class PetShop {
	List<PetAnimal> animalsStore = new ArrayList<PetAnimal>();

	public List<PetAnimal> getAnimalsStore() {
		return animalsStore;
	}

	public void setAnimalsStore(List<PetAnimal> animalsStore) {
		this.animalsStore = animalsStore;
	}
	
}
