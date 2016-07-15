package org.simple.coollection.query.criteria;

import org.simple.coollection.matcher.Matcher;
import org.simple.coollection.query.specification.Specification;
import org.simple.coollection.reflection.Phanton;

public class Criteria<T> {
	
	private final String method;
	private final Matcher matcher;
	private Specification<T> specification;

	public Criteria(Matcher matcher) {
		this.method = null;
		this.matcher = matcher;
	}
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
			Object value = null;
			
			value = method!=null? Phanton.from(item).call(method): item;
			
			return matcher.match(value);
		} catch(Exception err) {
			throw new RuntimeException(err);
		}
	}
}