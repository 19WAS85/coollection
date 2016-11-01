package org.simple.coollection.test;
 
import static org.simple.coollection.Coollection.from;
 
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
import org.simple.coollection.test.pojos.Cat;
import org.simple.coollection.test.pojos.Dog;
import org.simple.coollection.test.pojos.PetAnimal;
import org.simple.coollection.test.pojos.PetShop;
 
public class MaxTest {
   PetShop myPet = new PetShop();
   @Before
   public void setUp() throws Exception {
       myPet.getAnimalsStore().add(new Dog("brutus", "1", Double.valueOf(100d)));
       myPet.getAnimalsStore().add(new Dog("brutus", "5", Double.valueOf(1000d)));
       myPet.getAnimalsStore().add(new Dog("mila", "2", Double.valueOf(100d)));
       myPet.getAnimalsStore().add(new Cat("kila", "4", Double.valueOf(1000d)));
       myPet.getAnimalsStore().add(new Cat("mani", "4", Double.valueOf(1000d)));
   }
 
   @Test
   public void maxPriceAll() {
	   System.out.println("Max price is: " + from(myPet.getAnimalsStore()).maxValue("price", Double.class));
	   
       List<PetAnimal> maxPedgree = from(myPet.getAnimalsStore()).max("price", Double.class).all();
       for (PetAnimal species : maxPedgree) {
           System.out.println(species.getName());
           System.out.println(species.getPrice());
       }

       assert maxPedgree!=null && !maxPedgree.isEmpty();
       
   }
   
   @Test
   public void maxPriceFirst() {
	   System.out.println("Max price is: " + from(myPet.getAnimalsStore()).maxValue("price", Double.class));

	   PetAnimal maxPedgree = from(myPet.getAnimalsStore()).max("price", Double.class).first();
       
           System.out.println(maxPedgree.getName());
           System.out.println(maxPedgree.getPrice());
           
       assert maxPedgree!=null;
       
   }

   @Test
   public void maxNameAll() {
	   System.out.println("Max name is: " + from(myPet.getAnimalsStore()).maxValue("name", String.class));
	   
       List<PetAnimal> maxPedgree = from(myPet.getAnimalsStore()).max("name", String.class).all();
       for (PetAnimal species : maxPedgree) {
           System.out.println(String.format("%1s %2s %3s", species.getName(), species.getPedigree(), species.getPrice()));
       }

       assert maxPedgree!=null && !maxPedgree.isEmpty();
       
   }
 
}