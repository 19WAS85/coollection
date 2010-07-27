package com.wagnerandade.coollection.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.query.criteria.Criteria;
import com.wagnerandade.coollection.query.criteria.CriteriaList;
import com.wagnerandade.coollection.query.specification.custom.AndSpecification;
import com.wagnerandade.coollection.query.specification.custom.OrSpecification;

public class Query<T> {

	private final Collection<T> collection;
	private CriteriaList<T> criterias;

	public Query(Collection<T> collection) {
		this.collection = collection;
		criterias = new CriteriaList<T>();
	}

	public Query<T> where(String method, Matcher matcher) {
		Criteria<T> criteria = new Criteria<T>(method, matcher);
		criterias.add(criteria);
		return this;
	}

	public Query<T> and(String method, Matcher matcher) {
		Criteria<T> criteria = new Criteria<T>(method, matcher);
		criteria.setSpecification(new AndSpecification<T>());
		criterias.add(criteria);
		return this;
	}

	public Query<T> or(String method, Matcher matcher) {
		Criteria<T> criteria = new Criteria<T>(method, matcher);
		criteria.setSpecification(new OrSpecification<T>());
		criterias.add(criteria);
		return this;
	}

	public List<T> all() {
		List<T> all = new ArrayList<T>();
		for(T item : collection) {
			if(criterias.match(item)) {
				all.add(item);
			}
		}
		return all;
	}

	public T first() {
		for(T item : collection) {
			if(criterias.match(item)) {
				return item;
			}
		}
		return null;
	}

}