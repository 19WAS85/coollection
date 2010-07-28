package com.wagnerandade.coollection.matcher.custom;

import com.wagnerandade.coollection.matcher.Matcher;

public class IsNull implements Matcher {

	@Override
	public boolean match(Object value) {
		return value == null;
	}

}
