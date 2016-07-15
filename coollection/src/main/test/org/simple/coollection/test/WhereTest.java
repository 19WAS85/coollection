package org.simple.coollection.test;

import static org.simple.coollection.Coollection.eqIgnoreCase;
import static org.simple.coollection.Coollection.from;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.simple.coollection.test.pojos.Cat;
import org.simple.coollection.test.pojos.Dog;
import org.simple.coollection.test.pojos.PetAnimal;
import org.simple.coollection.test.pojos.PetShop;

public class WhereTest {
	PetShop myPet = new PetShop();
	@Before
	public void setUp() throws Exception {
		myPet.getAnimalsStore().add(new Dog("brutus", "1"));
		myPet.getAnimalsStore().add(new Dog("mila", "2"));
		myPet.getAnimalsStore().add(new Cat("kila", "3"));
		myPet.getAnimalsStore().add(new Cat("mani", "4"));
	}

	@Test
	public void listAllCats() {
		List<PetAnimal> allCats = from(myPet.getAnimalsStore()).where("getSpecies", eqIgnoreCase("cat")).all();
		for (PetAnimal animal : allCats) {
			System.out.println("Cat: " + animal.getName());
		}
		assert allCats!=null && !allCats.isEmpty();
		
	}

	@Test
	public void firstCat() {
		PetAnimal cat = from(myPet.getAnimalsStore()).where("getSpecies", eqIgnoreCase("cat")).first();
		System.out.println(cat.getName());
		assert cat!=null && cat.getSpecies()=="cat";
		
	}

	@Test
	public void allCatsWithName() {
		List<PetAnimal> cats = from(myPet.getAnimalsStore())
				.where("getSpecies", eqIgnoreCase("cat"))
				.and("name", eqIgnoreCase("kila")).all();
		
		for (PetAnimal cat : cats) {
			System.out.println(cat.getName());
		}

		PetAnimal kila = cats.get(0);
		
		assert kila.getSpecies()=="cat" && kila.getName().equalsIgnoreCase("kila");
		
	}

	@Test
	public void allCatsOrCatsWithName() {
		List<PetAnimal> cats = from(myPet.getAnimalsStore())
				.where("getSpecies", eqIgnoreCase("cat"))
				.or("name", eqIgnoreCase("kila")).all();
		
		for (PetAnimal cat : cats) {
			System.out.println(cat.getName());
		}

		assert cats.size()>1;
		
	}

}
