package com.wagnerandade.coollection.query.criteria;

import java.lang.reflect.Method;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.query.specification.Specification;

public class Criteria<T> {
	
	private final String method;
	private final Matcher matcher;
	private Specification<T> specification;

	public Criteria(String method, Matcher matcher) {
		this.method = method;
		this.matcher = matcher;
	}
	
	public void setSpecification(Specification<T> specification) {
		this.specification = specification;
	}
	
	public Specification<T> specification() {
		return specification;
	}
	
	public boolean match(T item) {
		try {
			Method m = item.getClass().getMethod(method);
			Object value = m.invoke(item);
			return matcher.match(value);
		} catch(Exception err) {
			throw new RuntimeException(err);
		}
	}
}