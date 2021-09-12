package com.maintaining.service.driverService;

import com.maintaining.domain.Car;
import com.maintaining.domain.Driver;
import com.maintaining.domainValues.CarStatus;
import com.maintaining.domainValues.OnlineStatus;
import com.maintaining.exceptions.CarAlreadyInUseException;
import com.maintaining.exceptions.OfflineDriverExceptoin;
import com.maintaining.exceptions.RemoveCarException;
import com.maintaining.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarsMapperToDriveres extends DriverServiceIml{


    public CarsMapperToDriveres(DriverRepository driverRepository) {
        super(driverRepository);
    }
    @Override
    @Transactional
    public Driver mapCar(Driver driver, Car car) throws OfflineDriverExceptoin, CarAlreadyInUseException {
        checkDriverStatus(driver.getOnlineStatus());
        checkCarStatus(car.getCarStatus());
        return resetData(driver, car);
    }
    @Override
    @Transactional
    public Driver deselectCar(Driver driver, Car car) {
        checkDriverHaveCar(driver);
        checkSameCar(driver, car);
        return doneDeleteTransaction(driver, car);
    }
    public void checkDriverStatus(OnlineStatus onlineStatus) throws OfflineDriverExceptoin {
        if (onlineStatus == OnlineStatus.OFFLINE) {
            throw new OfflineDriverExceptoin("U need to be Online to map a car.");
        }
    }

    public void checkCarStatus(CarStatus carStatus) throws CarAlreadyInUseException {
        if (carStatus == CarStatus.MAP) {
            throw new CarAlreadyInUseException("someone using this car now. try to choose other car.");
        }
    }

    private Driver resetData(Driver driver, Car car) {
        driver.setCar(car);
        driver.setHasCarNow(true);
        car.setCarStatus(CarStatus.MAP);
        updateItem(driver);
        return driver;
    }
    private Driver doneDeleteTransaction(Driver driver, Car car) {
        driver.setCar(null);
        driver.setHasCarNow(false);
        car.setCarStatus(CarStatus.UNMAP);
        return updateItem(driver);
    }
    private void checkSameCar(Driver driver, Car car) {
        if (driver.getCar().getCarId() != car.getCarId()) {
            throw new RemoveCarException("the car with id = " + car.getCarId() + " not mapaing for u");
        }
    }
    private void checkDriverHaveCar(Driver driver) {
        if (driver.getCar() == null) {
            throw new RemoveCarException("u don't have a car dude");
        }
    }
}
