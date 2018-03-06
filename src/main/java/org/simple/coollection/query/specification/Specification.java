package org.simple.coollection.query.specification;

import org.simple.coollection.query.criteria.Criteria;

public interface Specification<T> {

  boolean match(T item, Criteria<T> one, Criteria<T> other);

}
