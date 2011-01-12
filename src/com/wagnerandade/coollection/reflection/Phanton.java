package com.wagnerandade.coollection.reflection;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

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
		try{
			return invoke(method);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private Object invoke(String method) throws Exception {
		try {
			Method m = clazz.getMethod(method);
			return m.invoke(target);
		}catch(NoSuchMethodException e){
			return PropertyUtils.getProperty(target, method);
		}
	}
	
}