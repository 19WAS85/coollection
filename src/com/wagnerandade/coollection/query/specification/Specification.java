package com.wagnerandade.coollection.query.specification;

import java.util.ArrayList;
import java.util.List;

import com.wagnerandade.coollection.query.specification.criteria.Criteria;

public abstract class Specification<T> {
	
	private List<Criteria<T>> criterias;
	
	public Specification(Criteria<T> criteria) {
		criterias = new ArrayList<Criteria<T>>();
		criterias.add(criteria);
	}

	public void add(Criteria<T> criteria) {
		criterias.add(criteria);
	}
	
	public List<Criteria<T>> criterias() {
		return criterias;
	}
	
	public abstract boolean match(T item);
}