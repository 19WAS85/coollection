package com.wagnerandade.coollection;

import java.util.Collection;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.matcher.custom.Equals;
import com.wagnerandade.coollection.query.Query;

public class Coollection {

	public static <T> Query<T> from(Collection<T> collection) {
		return new Query<T>(collection);
	}

	public static Matcher eq(Object value) {
		return new Equals(value);
	}

}
