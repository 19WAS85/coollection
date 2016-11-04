package org.simple.coollection.test;

import static org.simple.coollection.Coollection.eq;
import static org.simple.coollection.Coollection.from;
import static org.simple.coollection.Coollection.in;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.simple.coollection.test.pojos.Cat;
import org.simple.coollection.test.pojos.Dog;
import org.simple.coollection.test.pojos.PetAnimal;
import org.simple.coollection.test.pojos.PetShop;

public class InTest {
	PetShop myPet = new PetShop();
	@Before
	public void setUp() throws Exception {
		myPet.getAnimalsStore().add(new Dog("brutus", "1", 100d));
		myPet.getAnimalsStore().add(new Dog("mila", "2", 200d));
		myPet.getAnimalsStore().add(new Cat("kila", "3", 300d));
		myPet.getAnimalsStore().add(new Cat("mani", "4", 400d));
	}

	@Test
	public void inString() {
		/*List<String> brothersNames = from(myPet.getAnimalsStore())
				.select("name", String.class)
				.in(Arrays.asList(new String[]{"mila", "brutus"})).all();*/
		
		List<String> brothersNames = from(myPet.getAnimalsStore())
				.where("name", in("mila", "brutus"))
				.select("name", String.class)
				.all();
		for (String brotherName : brothersNames) {
			System.out.println(brotherName);
		}
		assert brothersNames!=null && !brothersNames.isEmpty() && brothersNames.size()==2;

		brothersNames = from(myPet.getAnimalsStore())
				.where("name", in(Arrays.asList("mila", "brutus")))
				.select("name", String.class)
				.all();
		for (String brotherName : brothersNames) {
			System.out.println(brotherName);
		}
		assert brothersNames!=null && !brothersNames.isEmpty() && brothersNames.size()==2;

	}

	@Test
	public void inNumber() {
		/*List<String> brothersNames = from(myPet.getAnimalsStore())
				.select("name", String.class)
				.in(Arrays.asList(new String[]{"mila", "brutus"})).all();*/
		
		List<PetAnimal> brothers = from(myPet.getAnimalsStore())
				.where("price", in(100d, 300d))
				.all();
		
		for (PetAnimal brother : brothers) {
			System.out.println(brother.getName());
		}
		
		assert !brothers.isEmpty() && brothers.size()==2;
		
	}
	

	@Test
	public void inFieldName() {
		 List<PetAnimal> brothers = from(myPet.getAnimalsStore())
				 .where("name", in("mila", "brutus", "brutus", "mani"))
				.all();
		 
		 
		for (PetAnimal brother : brothers) {
			System.out.println(brother.getName());
		}
		
		assert brothers!=null && !brothers.isEmpty() && brothers.size()==3;
		assert from(brothers).where("name", eq("mila")).first().getName().equals("mila");
		assert from(brothers).where("name", eq("brutus")).first().getName().equals("brutus");
		assert from(brothers).where("name", eq("mani")).first().getName().equals("mani");

		assert from(brothers).where("name", eq("mila")).all().size()==1;
		assert from(brothers).where("name", eq("brutus")).all().size()==1;
		assert from(brothers).where("name", eq("mani")).all().size()==1;

	}

}
