package org.simple.coollection.test;
 
import static org.simple.coollection.Coollection.*;
 
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
import org.simple.coollection.test.pojos.Cat;
import org.simple.coollection.test.pojos.Dog;
import org.simple.coollection.test.pojos.PetAnimal;
import org.simple.coollection.test.pojos.PetShop;
 
public class BetweenTest {
   
   PetShop myPet = new PetShop();
   
   @Before
   public void setUp() throws Exception {
       myPet.getAnimalsStore().add(new Dog("brutus", "1", 1d));
       myPet.getAnimalsStore().add(new Dog("mila", "2", 2d));
       myPet.getAnimalsStore().add(new Cat("kila", "4", 3d));
       myPet.getAnimalsStore().add(new Cat("mani", "4", 9d));
       myPet.getAnimalsStore().add(new Cat("mani", "4", 10d));
       myPet.getAnimalsStore().add(new Cat("mani", "4", 11d));
   }
   
   @Test
   public void testBetween() {
       
       List<PetAnimal> list = from(myPet.getAnimalsStore()).where("price", between(1d, 2d)).all();
       System.out.println(list.size());
       assert list != null;
       
   }
 
}