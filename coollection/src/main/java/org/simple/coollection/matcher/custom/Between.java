package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class Between implements Matcher {

	private final Object matcherMin;
	private final Object matcherMax;

	public Between(Object min, Object max) {
		matcherMin=min;
		matcherMax=max;
	}

	@SuppressWarnings("unchecked")
	public boolean match(Object value) {
		Comparable<Object> v = (Comparable<Object>) value;
		
		return (v.compareTo(matcherMin) >= 0 && v.compareTo(matcherMax) <= 0);
	}

}
