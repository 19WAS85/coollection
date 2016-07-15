package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class Equals implements Matcher {

	private final Object value;

	public Equals(Object value) {
		this.value = value;
	}

	public boolean match(Object anotherValue) {
		if(value==null || anotherValue==null) return value == anotherValue;
		return value.equals(anotherValue);
	}

}
