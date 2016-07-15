package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class Contains implements Matcher {

	private final String matcherValue;

	public Contains(String matcherValue) {
		this.matcherValue = matcherValue;
	}

	public boolean match(Object value) {
		return String.valueOf(value).contains(matcherValue);
	}

}
