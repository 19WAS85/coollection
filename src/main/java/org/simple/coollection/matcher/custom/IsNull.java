package org.simple.coollection.matcher.custom;

import org.simple.coollection.matcher.Matcher;

public class IsNull implements Matcher {

  @Override
  public boolean match(Object value) {
    return value == null;
  }

}
