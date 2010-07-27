package com.wagnerandade.coollection.query.specification.custom;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.query.specification.Specification;

public class AndSpecification<T> extends Specification<T> {

	public AndSpecification(String method, Matcher matcher) {
		super(method, matcher);
	}

	@Override
	public boolean match(T item) {
		boolean match = matcher().match(methodValue(item));
		if(next() == null) {
			return match;
		} else {
			return match && next().match(item);
		}
	}
}