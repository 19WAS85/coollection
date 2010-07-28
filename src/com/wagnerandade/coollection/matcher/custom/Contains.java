package com.wagnerandade.coollection.matcher.custom;

import com.wagnerandade.coollection.matcher.Matcher;

public class Contains implements Matcher {

	private final String matcherValue;

	public Contains(String matcherValue) {
		this.matcherValue = matcherValue;
	}

	@Override
	public boolean match(Object value) {
		return String.valueOf(value).contains(matcherValue);
	}

}
