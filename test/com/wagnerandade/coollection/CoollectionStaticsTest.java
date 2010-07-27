package com.wagnerandade.coollection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

import static com.wagnerandade.coollection.Coollection.*;

public class CoollectionStaticsTest {
	
	private ArrayList<Animal> animals;
	
	@Before
	public void before() {
		animals = new ArrayList<Animal>();
		animals.add(new Animal("Lion", 10));
		animals.add(new Animal("Cat", 5));
		animals.add(new Animal("Dog", 5));
		animals.add(new Animal("Bird", 2));
		animals.add(new Animal("Cat", 3));
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
	public void should_be_possible_user_or_filters() {
		List<Animal> result = from(animals).where("name", eq("Lion")).or("age", eq(2)).all();
		assertThat(result.size(), is(2));
		assertThat(result.get(0).name(), is("Lion"));
		assertThat(result.get(1).name(), is("Bird"));
	}
}