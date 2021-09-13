package com.maintaining.dao;

import com.maintaining.exceptions.ConstraintsViolationException;
import com.maintaining.exceptions.NotFoundDriverExpetion;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {
    T insertItem(T item);

    List<T> getAllItems();

    T getItemById(long id) throws NotFoundDriverExpetion;

    T updateItem(T item) throws ConstraintsViolationException;

    Map<String, Boolean> deleteItem(long id) throws NotFoundDriverExpetion;

}
