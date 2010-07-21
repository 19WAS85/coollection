package com.wagnerandade.coollection.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wagnerandade.coollection.matcher.Matcher;

public class Query<T> {

	private final Collection<T> collection;
	private final ArrayList<Criteria<T>> criterias;

	public Query(Collection<T> collection) {
		this.collection = collection;
		criterias = new ArrayList<Criteria<T>>();
	}

	public Query<T> where(String method, Matcher matcher) {
		criterias.add(new Criteria<T>(method, matcher));
		return this;
	}

	public List<T> all() {
		List<T> all = new ArrayList<T>();
		for(T item : collection) {
			if(allCriteriasMatch(item)) {
				all.add(item);
			}
		}
		return all;
	}

	public T first() {
		for(T item : collection) {
			if(allCriteriasMatch(item)) {
				return item;
			}
		}
		return null;
	}

	private boolean allCriteriasMatch(T item) {
		for(Criteria<T> criteria : criterias) {
			if(!criteria.match(item)) {
				return false;
			}
		}
		return true;
	}

}