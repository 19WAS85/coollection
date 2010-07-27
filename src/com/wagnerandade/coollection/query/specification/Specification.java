package com.wagnerandade.coollection.query.specification;

import com.wagnerandade.coollection.query.criteria.Criteria;

public interface Specification<T> {
	
	public boolean match(T item, Criteria<T> one, Criteria<T> other);
	
}