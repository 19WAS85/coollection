package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class StartsWith implements Matcher {

	private final Object value;

	public StartsWith(Object value) {
		this.value = value;
	}

	
	public boolean match(Object anotherValue) {
		if(value==null && anotherValue==null)return true;
		if(value==null || anotherValue==null)return false;
		
		return value.toString().toUpperCase().startsWith(anotherValue.toString().toUpperCase());
	}

}
