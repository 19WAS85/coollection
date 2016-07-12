package org.simple.coollection.query.specification.custom;

import org.simple.coollection.query.criteria.Criteria;
import org.simple.coollection.query.specification.Specification;

public class OrSpecification<T> implements Specification<T> {

	
	public boolean match(T item, Criteria<T> one, Criteria<T> other) {
		return one.match(item) || other.match(item);
	}
	

}