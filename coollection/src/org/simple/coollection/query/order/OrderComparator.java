package org.simple.coollection.query.order;

import java.util.Comparator;
import org.simple.coollection.reflection.Phanton;


public class OrderComparator<T> implements Comparator<T> {

	private final String method;

	public OrderComparator(String method) {
		this.method = method;
	}

	@SuppressWarnings("unchecked")
	public int compare(T one, T other) {
		Object oneValue = Phanton.from(one).call(method);
		Object otherValue = Phanton.from(other).call(method);
		if(oneValue == null || otherValue == null) {
			return 0;
		}
		if(oneValue instanceof Comparable) {
			return ((Comparable<Object>) oneValue).compareTo(otherValue);
		} else {
			return 0;
		}
	}
	
}