package com.maintaining.service.driverService;

import com.maintaining.domain.Car;
import com.maintaining.domain.Driver;
import com.maintaining.dao.GenericDao;
import com.maintaining.exceptions.CarAlreadyInUseException;
import com.maintaining.exceptions.OfflineDriverExceptoin;
import org.springframework.stereotype.Service;

@Service
public interface DriverService extends GenericDao<Driver> {

    Driver mapCar(Driver driver, Car car) throws OfflineDriverExceptoin, CarAlreadyInUseException;

    Driver deselectCar(Driver driver, Car car);
}
