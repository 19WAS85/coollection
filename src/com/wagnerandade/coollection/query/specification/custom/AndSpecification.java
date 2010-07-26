package com.wagnerandade.coollection.query.specification.custom;

import com.wagnerandade.coollection.query.specification.Specification;
import com.wagnerandade.coollection.query.specification.criteria.Criteria;

public class AndSpecification<T> extends Specification<T> {
	
	public AndSpecification(Criteria<T> criteria) {
		super(criteria);
	}

	@Override
	public boolean match(T item) {
		for(Criteria<T> criteria : criterias()) {
			if(!criteria.match(item)) {
				return false;
			}
		}
		return true;
	}
}