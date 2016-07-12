package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class EndsWith implements Matcher {

	private final Object value;

	public EndsWith(Object value) {
		this.value = value;
	}

	public boolean match(Object anotherValue) {
		if(value==null && anotherValue==null)return true;
		if(value==null || anotherValue==null)return false;
		
		return anotherValue.toString().toUpperCase().endsWith(value.toString().toUpperCase());
	}

}
