package org.simple.coollection.reflection;

import java.lang.reflect.Field;
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
	
	public Object call(String name) {
		return invoke(name);
	}

	private Object invoke(String name){
		Object nestedTarget = target;
		Object returnValue = null;
		
		Class<? extends Object> nestedTargetClazz = clazz;
		for (String nestedName : name.split("\\.")) {
			try {
				Method m = nestedTargetClazz.getMethod(nestedName);
				returnValue = m.invoke(nestedTarget);
			} catch (Exception e) {
				Field field = findField(nestedName, nestedTargetClazz);
				
				if(field==null) throw new RuntimeException(e);

				try {
					field.setAccessible(Boolean.TRUE);
					returnValue = field.get(nestedTarget);
					
				}catch (Exception e1) {
					throw new RuntimeException(e1);
				}
				
			}

			if(returnValue==null) break;
			nestedTarget = returnValue;
			nestedTargetClazz = nestedTarget.getClass();
		}
		
		return returnValue;
	}
	
	
	private Field findField(String fieldName, Class<?> clazz) {
		Field field = null;
		try {
			field = clazz.getField(fieldName);
		} catch (NoSuchFieldException e2) {
		} catch (SecurityException e2) {
		}
		if(field==null) {
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e2) {
			} catch (SecurityException e2) {
			}
		}
		
		return field;
		
	}

	public void set(String name, Object newValue) {
		invoke(name, newValue);
	}
	private void invoke(String name, Object newValue){
		Object nestedTarget = target;

		Class<? extends Object> nestedTargetClazz = clazz;
		String[] nestedNames = name.split("\\.");

		if(nestedNames.length>2){
			nestedTarget = invoke(name.substring(0, name.lastIndexOf(".")));
			nestedTargetClazz = nestedTarget.getClass();
			nestedNames = new String[]{name.substring(name.lastIndexOf(".")+1)};
		}
		
		String nestedName = nestedNames[0];
		try {
			Method m = nestedTargetClazz.getMethod(nestedName, newValue.getClass());
			m.invoke(nestedTarget, newValue);
		} catch (Exception e) {
			Field field = findField(nestedName, nestedTargetClazz);

			if(field==null) throw new RuntimeException(e);
			
			try {
				field.setAccessible(Boolean.TRUE);
				field.set(nestedTarget, newValue);
			}catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		}

	}
}