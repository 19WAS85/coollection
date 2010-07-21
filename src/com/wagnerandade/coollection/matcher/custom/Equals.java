package com.wagnerandade.coollection.matcher.custom;

import com.wagnerandade.coollection.matcher.Matcher;

public class Equals implements Matcher {

	private final Object value;

	public Equals(Object value) {
		this.value = value;
	}

	@Override
	public boolean match(Object anotherValue) {
		return value.equals(anotherValue);
	}

}
