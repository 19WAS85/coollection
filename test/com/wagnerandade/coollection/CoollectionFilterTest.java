package com.wagnerandade.coollection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

import static com.wagnerandade.coollection.Coollection.*;

public class CoollectionFilterTest {
	
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
	public void should_be_possible_create_a_filter_in_a_collection() {
		List<Animal> filtered = from(animals).where("name", eq("Cat")).all();
		assertThat(filtered.size(), is(2));
		assertThat(filtered.get(0).name(), is("Cat"));
	}
	
	@Test
	public void should_be_possible_create_a_filter_and_take_first_result() {
		Animal first = from(animals).where("age", eq(2)).first();
		assertThat(first.name(), is("Bird"));
	}
	
	@Test
	public void should_be_possible_create_and_filters() {
		List<Animal> result = from(animals).where("name", eq("Cat")).and("age", eq(3)).all();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).name(), is("Cat"));
		assertThat(result.get(0).age(), is(3));
	}
	
	@Test
	public void should_be_possible_use_or_filters() {
		List<Animal> result = from(animals).where("name", eq("Lion")).or("age", eq(2)).all();
		assertThat(result.size(), is(2));
		assertThat(result.get(0).name(), is("Lion"));
		assertThat(result.get(1).name(), is("Bird"));
	}
	
	@Test
	public void should_be_possible_use_contains_matcher() {
		List<Animal> result = from(animals).where("name", contains("i")).all();
		assertThat(result.size(), is(2));
		assertThat(result.get(0).name(), is("Lion"));
		assertThat(result.get(1).name(), is("Bird"));
	}
	
	@Test
	public void should_be_possible_use_equals_ignore_case_matcher() {
		List<Animal> result = from(animals).where("name", eqIgnoreCase("cat")).all();
		assertThat(result.size(), is(2));
		assertThat(result.get(0).name(), is("Cat"));
		assertThat(result.get(1).name(), is("Cat"));
	}
	
	@Test
	public void should_be_possible_to_use_not_in_any_matcher() {
		List<Animal> result = from(animals).where("name", not(contains("i"))).all();
		assertThat(result.size(), is(5));
		assertThat(result.get(1).name(), is("Dog"));
	}
	
	@Test
	public void should_be_possible_to_take_greater_than() {
		List<Animal> result = from(animals).where("age", greaterThan(3)).all();
		assertThat(result.size(), is(3));
		assertThat(result.get(0).name(), is("Lion"));
	}
	
	@Test
	public void should_be_possible_to_take_less_than() {
		List<Animal> result = from(animals).where("age", lessThan(3)).all();
		assertThat(result.size(), is(2));
		assertThat(result.get(0).name(), is("Bird"));
	}
	
	@Test
	public void should_be_possible_to_take_null_values() {
		List<Animal> result = from(animals).where("name", isNull()).all();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).age(), is(-200));
	}
	
	@Test
	public void should_be_possible_create_a_filter_in_a_collection_looking_for_ugly_javabeans_conventions() {
		Animal tapir = new Animal("Tapir", 3, "Tapirus bairdii");
		animals.add(tapir);
		List<Animal> filtered = from(animals).where("scientificName", eq("Tapirus bairdii")).and("live", eq(true)).all();
		assertThat(filtered.size(), is(1));
		assertThat(filtered.get(0).name(), is("Tapir"));
	}
}