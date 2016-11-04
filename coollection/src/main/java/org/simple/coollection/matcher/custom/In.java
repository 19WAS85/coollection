package org.simple.coollection.matcher.custom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.simple.coollection.matcher.Matcher;

public class In implements Matcher {

	private final Object[] matcherValues;

	public In(Object[] values) {
		Arrays.sort(values);
		matcherValues = values;
	}

	public In(List<Object> values) {
		this(values.toArray(new Object[]{}));
	}

	@SuppressWarnings("unchecked")
	public boolean match(Object value) {
		int foundedIndex =0;
		if(value.getClass().isAssignableFrom(String.class)){
			foundedIndex = Arrays.binarySearch(matcherValues, value, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					if(o1==null && o2==null) return 0;
					if(o1==null) return 1;
					return o1.toString().trim().compareTo(o2.toString().trim());
				}
			});
			
		}else{
			foundedIndex = Arrays.binarySearch(matcherValues, value, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					if(o1==null && o2==null) return 0;
					if(o1==null) return 1;
					return ((Comparable<Object>)o1).compareTo(o2);
				}
			});
		}		
		
		return foundedIndex > -1;
	}

}
