package org.simple.coollection.query;



import static org.simple.coollection.Coollection.from;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.simple.coollection.matcher.Matcher;
import org.simple.coollection.query.criteria.Criteria;
import org.simple.coollection.query.criteria.CriteriaList;
import org.simple.coollection.query.order.Order;
import org.simple.coollection.query.order.OrderCriteria;
import org.simple.coollection.query.specification.custom.AndSpecification;
import org.simple.coollection.query.specification.custom.OrSpecification;
import org.simple.coollection.reflection.Phanton;


public class Query<T> {

	private final Collection<T> collection;
	private CriteriaList<T> criterias;
	private OrderCriteria<T> orderCriteria;
	
	public Query(Collection<T> collection) {
		this.collection = collection;
		criterias = new CriteriaList<T>();
	}

	public Query<T> in(Collection<T> values) {
		List<T> all = cloneCollection(collection);
		List<T> in = new ArrayList<T>();
		List<T> valuesCopy = cloneCollection(values);
		
		for (T sub : valuesCopy) {
			
			for (T v : all) {
				if (v == null && sub == null) continue;
				
				T left = (v != null ? v : sub); 
				T right = (v != null ? sub : v); 
				
				if(left.getClass().isAssignableFrom(String.class)){
					if (left.toString().trim().equalsIgnoreCase(right.toString().trim())) in.add(v);
					
				}else{
					if (left.equals(right)) in.add(v);
				}
			}
			
			all.removeAll(in);
			
		}
		
		return from(in);
	}
	public Query<T> in(T... values) {
		return in(Arrays.asList(values));
	}
	public <TInArr> Query<T> in(String method, TInArr... values) {
		return in(method, Arrays.asList(values));
	}
	
	public <TInArr> Query<T> in(String method, Collection<TInArr> values) {
		List<T> all = cloneCollection(collection);
		List<T> in = new ArrayList<T>();
		List<TInArr> valuesCopy = from(values).all();
		
		for (TInArr sub : valuesCopy) {
			
			for (T v : all) {
				
				TInArr v1 = (TInArr) Phanton.from(v).call(method);
				if (v1 == null && sub == null) continue;
				
				TInArr left = (v1 != null ? v1 : sub); 
				TInArr right = (v1 != null ? sub : v1); 
				
				if(left.getClass().isAssignableFrom(String.class)){
					if (left.toString().trim().equalsIgnoreCase(right.toString().trim())) in.add(v);
					
				}else{
					if (left.equals(right)) in.add(v);
				}
			}
			
			all.removeAll(in);
			
		}

		return from(in);
	}
	public Query<T> where(String method, Matcher matcher) {
		Criteria<T> criteria = new Criteria<T>(method, matcher);
		criterias.add(criteria);
		return this;
	}

	public Query<T> and(String method, Matcher matcher) {
		Criteria<T> criteria = new Criteria<T>(method, matcher);
		criteria.setSpecification(new AndSpecification<T>());
		criterias.add(criteria);
		return this;
	}

	public Query<T> or(String method, Matcher matcher) {
		Criteria<T> criteria = new Criteria<T>(method, matcher);
		criteria.setSpecification(new OrSpecification<T>());
		criterias.add(criteria);
		return this;
	}
	
	public Query<T> orderBy(String method, Order order) {
		orderCriteria = new OrderCriteria<T>(method, order);
		return this;
	}

	public Query<T> orderBy(String method) {
		return orderBy(method, Order.ASC);
	}

	public List<T> all() {
		List<T> all = new ArrayList<T>();
		for(T item : collection) {
			if(item==null)continue;

			if(criterias.match(item)) {
				all.add(item);
			}
		}
		if(orderCriteria != null) {
			all = orderCriteria.sort(all);
		}
		return all;
	}
	public T first() {
		List<T> all = cloneCollection(collection);
		if(orderCriteria != null) {
			all = orderCriteria.sort(all);
		}
		for(T item : all) {
			if(criterias.match(item)) {
				return item;
			}
		}
		return null;
	}

	public List<T> firstOccours(int occours) {
		List<T> all = cloneCollection(collection);
		if(orderCriteria != null) {
			all = orderCriteria.sort(all);
		}
		
		ArrayList<T> list = new ArrayList<T>();
		for(T item : all) {
			if(criterias.match(item)) list.add(item);
			if(list.size()>=occours) break;
		}
		
		return list;
	}

	private List<T> cloneCollection(Collection<T> collection) {
		List<T> list = new ArrayList<T>();
		for(T item : collection) {
			if(item==null)continue;
	
			list.add(item);
		}
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public <TSub> Query<TSub> select (String method) {
		List<TSub> select = new ArrayList<TSub>();
		for (T t : all()) {
			try {
				select.addAll((Collection<TSub>) Phanton.from(t).call(method));
			}
			catch (Exception e) {
				select.add((TSub) Phanton.from(t).call(method));				
			}
		}
		return from(select);
	}
	@SuppressWarnings("unchecked")
	public <TSub> Query<TSub> select (String method, Class<TSub> returnType) {
		List<TSub> select = new ArrayList<TSub>();
		for (T t : all()) {
			try {
				select.addAll((Collection<TSub>) Phanton.from(t).call(method));
			}
			catch (Exception e) {
				select.add((TSub) Phanton.from(t).call(method));				
			}
		}
		return from(select);
	}

	public void set(String method, Object newValue) {
		for (T t : all()) {
			Phanton.from(t).set(method, newValue);
		}
	}
	public Map<Object, List<T>> groupBy(String groupBy) {
		Map<Object, List<T>> groups = new HashMap<Object, List<T>>(); 
		for (T t : all()) {
			Object v = (Object) Phanton.from(t).call(groupBy);
			
			if(!groups.containsKey(v)) groups.put(v, new ArrayList<T>()); 
			
			groups.get(v).add(t);
		}
		return groups;
	}

	public Query<T> distinct() {
		HashSet<T> distinct = new HashSet<T>();
		
		for (T t : all()) {
			distinct.add(t);
		}
		
		return (Query<T>) from(distinct);
	}
	public Query<T> distinct(String distinctBy) {
		HashMap<Object, T> distinct = new HashMap<Object, T>();
		
		for (T element : all()) {
			Object elementValue = (Object) Phanton.from(element).call(distinctBy);
			
			if(distinct.containsKey(elementValue)) continue;
			
			distinct.put(elementValue, element);
		}
		return (Query<T>) from(distinct.values());
	}
	public Query<T> distinctString(String distinctBy) {
		return distinctString(distinctBy, true, true);
	}
	public Query<T> distinctString(String distinctBy, boolean ignoreCase, boolean trimValues) {
		HashMap<Object, T> distinct = new HashMap<Object, T>();
		for (T element : all()) {
			String elementValue = (String) Phanton.from(element).call(distinctBy);
			
			if(elementValue!=null){

				if(trimValues && ignoreCase) elementValue = elementValue.trim().toUpperCase();
				if(ignoreCase) elementValue = elementValue.toUpperCase();
				if(trimValues) elementValue = elementValue.trim();
				
			}

			
			if(distinct.containsKey(elementValue)) continue;
			
			distinct.put(elementValue, element);
		}
		return (Query<T>) from(distinct.values());
	}
	public double sum(String sumBy) {
		Double sum = 0D;
		
		for (T t : all()) {
			Object v = (Object) Phanton.from(t).call(sumBy);
			try {
				sum += Double.valueOf(v.toString());
			}
			catch (Exception e) {}
		}
		return (double) sum;
	}
}