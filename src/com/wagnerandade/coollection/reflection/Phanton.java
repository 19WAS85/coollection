package com.wagnerandade.coollection.reflection;

import java.lang.reflect.Field;

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

  private Object invoke(String name) {
    for (final Field field : clazz.getDeclaredFields()) {
      try {
        if (name.equals(field.getName())) {
          field.setAccessible(Boolean.TRUE);
          return field.get(target);
        }
        String formatedName = name.toLowerCase().replace("get", "");
        String formatedfieldName = field.getName().toLowerCase().replace("get", "");
        if (formatedName.equals(formatedfieldName)) {
          field.setAccessible(Boolean.TRUE);
          return field.get(target);
        }
        String formatedGetName = "get" + name;
        String formatedGetFieldName = "get" + field.getName();
        if (formatedGetName.equals(formatedGetFieldName))
        {
          field.setAccessible(Boolean.TRUE);
          return field.get(target);
        }
      } catch (final IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    } throw new RuntimeException("No such property with name " + name);
  }
}