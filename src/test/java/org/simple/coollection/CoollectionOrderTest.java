package org.simple.coollection;

import org.junit.Before;
import org.junit.Test;
import org.simple.coollection.query.order.Order;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.simple.coollection.Coollection.eq;
import static org.simple.coollection.Coollection.from;

public class CoollectionOrderTest {

  private ArrayList<Animal> animals;

  @Before
  public void before() {
    animals = new ArrayList<Animal>();
    animals.add(new Animal("Lion", 10));
    animals.add(new Animal("Cat", 5));
    animals.add(new Animal("Dog", 5));
    animals.add(new Animal("Bird", 2));
    animals.add(new Animal("Cat", 3));
    animals.add(new Animal(null, -200));
    animals.add(new Animal("Unkown", null));
  }

  @Test
  public void should_be_possible_to_order_a_collection_by_a_string() {
    List<Animal> ordered = from(animals).orderBy("name").all();
    assertThat(ordered.get(0).name(), is("Bird"));
  }

  @Test
  public void should_be_possible_to_order_desc_with_a_numeric_with_filter() {
    List<Animal> ordered = from(animals).where("name", eq("Cat")).orderBy("age", Order.DESC).all();
    assertThat(ordered.get(1).age(), is(3));
  }

  @Test
  public void should_be_possible_to_order_a_collection_by_a_string_and_get_first() {
    Animal animal = from(animals).orderBy("name").first();
    assertThat(animal.name(), is("Bird"));
  }

  @Test
  public void should_not_modify_original_collection_when_order() {
    from(animals).orderBy("name").first();
    assertThat(animals.get(0).name(), is("Lion"));
  }

  @Test
  public void should_be_possible_to_order_a_collection_by_a_string_looking_for_ugly_javabeans_and_get_first() {
    List<Animal> animals = new ArrayList<Animal>();
    animals.add(new Animal("Tapir", 5, "Tapirus bairdii"));
    animals.add(new Animal("Chicken", 3, "Gallus gallus domesticus"));
    animals.add(new Animal("Unkown Animal", 2, null));
    Animal animal = from(animals).orderBy("scientificName").first();
    assertThat(animal.name(), is("Chicken"));
  }

}
