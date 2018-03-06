package org.simple.coollection.query.criteria;

import java.util.ArrayList;
import java.util.List;

public class CriteriaList<T> {
	
	private List<Criteria<T>> criterias;
	
	public CriteriaList() {
		criterias = new ArrayList<Criteria<T>>();
	}

	public void add(Criteria<T> criteria) {
		criterias.add(criteria);
	}
	
	public boolean match(T item) {
		if(criterias.size() == 0) {
			return true;
		}
		if(criterias.size() == 1) {
			return criterias.get(0).match(item);
		}
		boolean matched = true;
		for(int i = criterias.size() - 1; i > 0; i--) {
			Criteria<T> one = criterias.get(i);
			Criteria<T> other = criterias.get(i - 1);
			matched = matched && one.specification().match(item, one, other);
		}
		return matched;
	}
	
}