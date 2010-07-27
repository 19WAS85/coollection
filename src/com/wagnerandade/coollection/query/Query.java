package com.wagnerandade.coollection.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.query.specification.Specification;
import com.wagnerandade.coollection.query.specification.custom.AndSpecification;

public class Query<T> {

	private final Collection<T> collection;
	private Specification<T> lastSpecification;

	public Query(Collection<T> collection) {
		this.collection = collection;
	}

	public Query<T> where(String method, Matcher matcher) {
		lastSpecification = new AndSpecification<T>(method, matcher);
		return this;
	}

	public Query<T> and(String method, Matcher matcher) {
		AndSpecification<T> specification = new AndSpecification<T>(method, matcher);
		setNext(specification);
		return this;
	}

	public List<T> all() {
		List<T> all = new ArrayList<T>();
		for(T item : collection) {
			if(lastSpecification.match(item)) {
				all.add(item);
			}
		}
		return all;
	}

	public T first() {
		for(T item : collection) {
			if(lastSpecification.match(item)) {
				return item;
			}
		}
		return null;
	}

	private void setNext(Specification<T> specification) {
		lastSpecification.setNext(specification);
		lastSpecification = specification;
	}

}