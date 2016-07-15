package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class LessThan implements Matcher {

	private final Number value;

	public LessThan(Number value) {
		this.value = value;
	}

	
	public boolean match(Object matchValue) {
		if (matchValue == null){
			return false;
		}
		return ((Number) matchValue).doubleValue() < value.doubleValue();
	}

}
