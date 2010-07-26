package com.wagnerandade.coollection.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.query.specification.SpecificationList;
import com.wagnerandade.coollection.query.specification.criteria.Criteria;
import com.wagnerandade.coollection.query.specification.custom.AndSpecification;

public class Query<T> {

	private final Collection<T> collection;
	private final SpecificationList<T> specifications;

	public Query(Collection<T> collection) {
		this.collection = collection;
		specifications = new SpecificationList<T>();
	}

	public Query<T> where(String method, Matcher matcher) {
		specifications.add(new AndSpecification<T>(new Criteria<T>(method, matcher)));
		return this;
	}

	public Query<T> and(String method, Matcher matcher) {
		specifications.last().add(new Criteria<T>(method, matcher));
		return this;
	}

	public List<T> all() {
		List<T> all = new ArrayList<T>();
		for(T item : collection) {
			if(specifications.match(item)) {
				all.add(item);
			}
		}
		return all;
	}

	public T first() {
		for(T item : collection) {
			if(specifications.match(item)) {
				return item;
			}
		}
		return null;
	}

}