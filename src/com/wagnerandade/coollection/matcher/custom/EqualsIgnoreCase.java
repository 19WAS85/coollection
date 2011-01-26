package com.wagnerandade.coollection.matcher.custom;

import com.wagnerandade.coollection.matcher.Matcher;

public class EqualsIgnoreCase implements Matcher {

	private final String value;

	public EqualsIgnoreCase(String value) {
		this.value = value;
	}

	@Override
	public boolean match(Object anotherValue) {
		return (value).equalsIgnoreCase((String)anotherValue);
	}

}
