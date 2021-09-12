package com.maintaining.service.carService;

import com.maintaining.domain.Car;
import com.maintaining.dao.GenericDao;

import java.util.List;
import java.util.Map;

public interface CarService extends GenericDao<Car> {
    Car insertItem(Car car);

    List<Car> getAllItems();

    Car getItemById(long id);

    Car updateItem(Car car);

    Map<String, Boolean> deleteItem(long id);
}
