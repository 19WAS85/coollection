package org.simple.coollection;

import java.util.Collection;
import org.simple.coollection.matcher.Matcher;
import org.simple.coollection.matcher.custom.Contains;
import org.simple.coollection.matcher.custom.EndsWith;
import org.simple.coollection.matcher.custom.Equals;
import org.simple.coollection.matcher.custom.EqualsIgnoreCase;
import org.simple.coollection.matcher.custom.GreaterThan;
import org.simple.coollection.matcher.custom.IsNull;
import org.simple.coollection.matcher.custom.IsNullOrEmpty;
import org.simple.coollection.matcher.custom.LessThan;
import org.simple.coollection.matcher.custom.Not;
import org.simple.coollection.matcher.custom.StartsWith;
import org.simple.coollection.query.Query;


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
	public static Matcher isNullOrEmpty() {
		return new IsNullOrEmpty();
	}

	public static Matcher endsWith(Object value) {
		return new EndsWith(value);
	}
	public static Matcher startsWith(Object value) {
		return new StartsWith(value);
	}

}