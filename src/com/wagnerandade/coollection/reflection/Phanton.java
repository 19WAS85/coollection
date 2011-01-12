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
	
	public Object call(String name) {
		try{
			return invoke(name);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private Object invoke(String name) throws Exception {
		try {
			Method m = clazz.getMethod(name);
			return m.invoke(target);
		}catch(NoSuchMethodException e){
			return PropertyUtils.getProperty(target, name);
		}
	}
	
}