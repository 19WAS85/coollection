package org.simple.coollection.query.criteria;

import java.util.ArrayList;
import java.util.List;

import org.simple.coollection.query.specification.Specification;
import org.simple.coollection.query.specification.custom.AndSpecification;
import org.simple.coollection.query.specification.custom.OrSpecification;

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
		Boolean matched = null;
		
		Specification<T> lastSpec = null;

		for(int i = criterias.size() - 1; i > -1; i--) {
			Criteria<T> one = criterias.get(i);
			Specification<T> spec = one.specification()!=null?one.specification(): lastSpec;
			if(spec==null) spec = new AndSpecification<T>();
			
			if(spec.getClass() == OrSpecification.class) {
				if(matched==null)matched = false;
				matched = matched || one.match(item);
			}else {
				if(matched==null)matched = true;
				matched = matched && one.match(item);
			}
			lastSpec = one.specification();
		}
		return matched;
	}
	
}