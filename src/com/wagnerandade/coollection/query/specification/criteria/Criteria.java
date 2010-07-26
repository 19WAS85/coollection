package com.wagnerandade.coollection.query.specification.criteria;

import java.lang.reflect.Method;

import com.wagnerandade.coollection.matcher.Matcher;

public class Criteria<T> {

	private final String method;
	private final Matcher matcher;

	public Criteria(String method, Matcher matcher) {
		this.method = method;
		this.matcher = matcher;
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
