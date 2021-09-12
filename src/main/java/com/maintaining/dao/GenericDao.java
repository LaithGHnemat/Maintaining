package com.maintaining.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {
    T insertItem(T item);

    List<T> getAllItems();

    T getItemById(long id);

    T updateItem(T item);

    Map<String, Boolean> deleteItem(long id);

}
