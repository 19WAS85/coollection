package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class EqualsString implements Matcher {

	private final String value;
	private boolean ignoreCase;
	private boolean trimValues;

	public EqualsString(String value, boolean ignoreCase, boolean trimValues) {
		this.value = value;
		this.ignoreCase = ignoreCase;
		this.trimValues = trimValues;
	}

	public boolean match(Object anotherValue) {
		if(value==null || anotherValue==null) return value == anotherValue;
		
		if(trimValues && ignoreCase) return (value.trim()).equalsIgnoreCase(((String)anotherValue).trim());
		if(ignoreCase)return (value.trim()).equals(((String)anotherValue).trim());
		if(trimValues)return (value).equalsIgnoreCase((String)anotherValue);
		
		return value.equals(anotherValue);
	}

}
