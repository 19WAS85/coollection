package org.simple.coollection.test;

import static org.simple.coollection.Coollection.from;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.simple.coollection.test.pojos.Cat;
import org.simple.coollection.test.pojos.Dog;
import org.simple.coollection.test.pojos.PetShop;

public class DistinctTest {
	PetShop myPet = new PetShop();
	@Before
	public void setUp() throws Exception {
		myPet.getAnimalsStore().add(new Dog("brutus", "1"));
		myPet.getAnimalsStore().add(new Dog("mila", "2"));
		myPet.getAnimalsStore().add(new Cat("kila", "3"));
		myPet.getAnimalsStore().add(new Cat("mani", "4"));
	}

	@Test
	public void distinctSpecies() {
		List<String> allSpecies = from(myPet.getAnimalsStore()).distinct("getSpecies").<String>select("getSpecies").all();
		for (String species : allSpecies) {
			System.out.println(species);
		}
		assert allSpecies!=null && !allSpecies.isEmpty();
		
	}

}
