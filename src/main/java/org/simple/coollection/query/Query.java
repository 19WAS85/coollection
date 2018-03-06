package org.simple.coollection.query;

import org.simple.coollection.matcher.Matcher;
import org.simple.coollection.query.criteria.Criteria;
import org.simple.coollection.query.criteria.CriteriaList;
import org.simple.coollection.query.order.Order;
import org.simple.coollection.query.order.OrderCriteria;
import org.simple.coollection.query.specification.custom.AndSpecification;
import org.simple.coollection.query.specification.custom.OrSpecification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Query<T> {

  private final Collection<T> collection;
  private CriteriaList<T> criterias;
  private OrderCriteria<T> orderCriteria;

  public Query(Collection<T> collection) {
    this.collection = collection;
    criterias = new CriteriaList<T>();
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
    for (T item : collection) {
      if (criterias.match(item)) {
        all.add(item);
      }
    }
    if (orderCriteria != null) {
      all = orderCriteria.sort(all);
    }
    return all;
  }

  public T first() {
    List<T> all = cloneCollection(collection);
    if (orderCriteria != null) {
      all = orderCriteria.sort(all);
    }
    for (T item : all) {
      if (criterias.match(item)) {
        return item;
      }
    }
    return null;
  }

  private List<T> cloneCollection(Collection<T> collection) {
    List<T> list = new ArrayList<T>();
    for (T item : collection) {
      list.add(item);
    }
    return list;
  }

}
