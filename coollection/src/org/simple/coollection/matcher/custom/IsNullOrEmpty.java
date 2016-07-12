package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class IsNullOrEmpty implements Matcher {

	
	public boolean match(Object value) {
		return (value == null || value.toString().trim().equals(""));
	}

}
