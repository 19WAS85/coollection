package org.simple.coollection.query.order;

import java.util.Collections;
import java.util.List;

public class OrderCriteria<T> {

	private final String method;
	private final Order order;

	public OrderCriteria(String method, Order order) {
		this.method = method;
		this.order = order;
	}
	
	public List<T> sort(List<T> list) {
		Collections.sort(list, new OrderComparator<T>(method));
		if(order == Order.DESC) {
			Collections.reverse(list);
		}
		return list;
	}

}