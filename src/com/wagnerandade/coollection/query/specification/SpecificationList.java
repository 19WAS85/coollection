package com.wagnerandade.coollection.query.specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationList<T> {

	private final List<Specification<T>> specifications;
	private Specification<T> last;
	
	public SpecificationList() {
		specifications = new ArrayList<Specification<T>>();
	}
	
	public void add(Specification<T> specification) {
		last = specification;
		specifications.add(specification);
	}
	
	public Specification<T> last() {
		return last;
	}
	
	public boolean match(T item) {
		for(Specification<T> specification : specifications) {
			if(!specification.match(item)) {
				return false;
			}
		}
		return true;
	}
}