package com.wagnerandade.coollection.reflection;

import java.lang.reflect.Method;

public class Phanton<T> {
	
	private final T target;
	private Class<?> clazz;

	private Phanton(T target) {
		this.target = target;
		clazz = target.getClass();
	}
	
	public static <T> Phanton<T> from(T target) {
		return new Phanton<T>(target);
	}
	
	public Object call(String method) {
		try {
			Method m = clazz.getMethod(method);
			return m.invoke(target);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}