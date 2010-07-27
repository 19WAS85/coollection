package com.wagnerandade.coollection.query.specification;

import java.lang.reflect.Method;

import com.wagnerandade.coollection.matcher.Matcher;

public abstract class Specification<T> {
	
	private final String method;
	private final Matcher matcher;
	private Specification<T> next;

	public Specification(String method, Matcher matcher) {
		this.method = method;
		this.matcher = matcher;
	}

	public void setNext(Specification<T> specification) {
		next = specification;
	}
	
	public Specification<T> next() {
		return next;
	}
	
	public Matcher matcher() {
		return matcher;
	}
	
	protected Object methodValue(Object item) {
		try {
			Method m = item.getClass().getMethod(method);
			return m.invoke(item);
		} catch(Exception err) {
			throw new RuntimeException(err);
		}
	}
	
	public abstract boolean match(T item);
}