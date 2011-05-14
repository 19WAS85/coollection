package com.wagnerandade.coollection;

import java.util.Collection;

import com.wagnerandade.coollection.matcher.Matcher;
import com.wagnerandade.coollection.matcher.custom.Contains;
import com.wagnerandade.coollection.matcher.custom.Equals;
import com.wagnerandade.coollection.matcher.custom.EqualsIgnoreCase;
import com.wagnerandade.coollection.matcher.custom.GreaterThan;
import com.wagnerandade.coollection.matcher.custom.IsNull;
import com.wagnerandade.coollection.matcher.custom.LessThan;
import com.wagnerandade.coollection.matcher.custom.Not;
import com.wagnerandade.coollection.query.Query;

public class Coollection {

	public static <T> Query<T> from(Collection<T> collection) {
		return new Query<T>(collection);
	}

	public static Matcher eq(Object value) {
		return new Equals(value);
	}
	
	public static Matcher contains(String value) {
		return new Contains(value);
	}
	
	public static Matcher eqIgnoreCase(String value) {
		return new EqualsIgnoreCase(value);
	}
	
	public static Matcher not(Matcher matcher) {
		return new Not(matcher);
	}
	
	public static Matcher greaterThan(Number value) {
		return new GreaterThan(value);
	}
	
	public static Matcher lessThan(Number value) {
		return new LessThan(value);
	}
	
	public static Matcher isNull() {
		return new IsNull();
	}

}