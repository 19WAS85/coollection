package com.wagnerandade.coollection.matcher.custom;

import com.wagnerandade.coollection.matcher.Matcher;

public class Not implements Matcher {

	private final Matcher matcher;

	public Not(Matcher matcher) {
		this.matcher = matcher;
	}

	@Override
	public boolean match(Object value) {
		return !matcher.match(value);
	}

}
