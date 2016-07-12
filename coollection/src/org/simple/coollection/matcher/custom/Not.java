package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class Not implements Matcher {

	private final Matcher matcher;

	public Not(Matcher matcher) {
		this.matcher = matcher;
	}

	
	public boolean match(Object value) {
		return !matcher.match(value);
	}

}
