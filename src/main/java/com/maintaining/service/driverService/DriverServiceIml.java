package com.maintaining.service.driverService;

import com.maintaining.domain.Driver;
import com.maintaining.domainValues.Enginetype;
import com.maintaining.domainValues.OnlineStatus;
import com.maintaining.exceptions.NotFoundDriverExpetion;
import com.maintaining.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public abstract class DriverServiceIml implements DriverService {

    protected final DriverRepository driverRepository;

    @Override
    public Driver insertItem(Driver driver) {
        return this.driverRepository.save(driver);
    }

    @Override
    public List<Driver> getAllItems() {
        return this.driverRepository.findAll();
    }

    @Override
    public Driver getItemById(long id) throws NotFoundDriverExpetion {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundDriverExpetion("driver is not found for this id = " + id));
        return driver;
    }

    @Override
    public Driver updateItem(Driver driver) {
        return this.driverRepository.save(driver);
    }

    @Override
    public Map<String, Boolean> deleteItem(long id) throws NotFoundDriverExpetion {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundDriverExpetion("driver not found for this id :: " + id));
        driverRepository.delete(driver);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Driver findDriverByCarId(long carId) {
        return driverRepository.findByAndCar_CarId(carId);
    }

    public List<Driver> findDriverByManufacturer(String manufacturer) {
        return driverRepository.findByAndCar_Manufacturer(manufacturer);
    }

    public List<Driver> findDriversBySeatCount(int seatCount) {
        return driverRepository.findByAndCar_SeatCount(seatCount);
    }

    public List<Driver> findDriversHaveConvertibleCars(boolean convertible) {
        return driverRepository.findByAndCar_Convertible(convertible);
    }

    public List<Driver> findDriversEnginetype(Enginetype enginetype) {
        return driverRepository.findByAndCar_EngineType(enginetype);
    }

    public List<Driver> findDriversByRatingCars(BigDecimal rating) {
        return driverRepository.findByAndCar_Rating(rating);
    }

    public List<Driver> findDriversByDateCreated(ZonedDateTime zonedDateTime) {
        return driverRepository.findByDateCreated(zonedDateTime);
    }

    public List<Driver> findDriversEngineTypeAndManufacturer(Enginetype enginetype, String mnf) {
        return driverRepository.findByAndCar_EngineTypeAndCar_Manufacturer(enginetype, mnf);
    }

    public List<Driver> findDriversByStatus(OnlineStatus onlineStatus) {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    public List<Driver> getDriverByStatusAndHasCar(OnlineStatus onlineStatus, boolean hasCar) {
        return driverRepository.findByOnlineStatusAndHasCarNow(onlineStatus, hasCar);
    }

    public List<Driver> getDriverByStatusAndHasCarOrPassword(OnlineStatus onlineStatus, boolean hasCar, String password) {
        return driverRepository.findByOnlineStatusAndHasCarNowOrPassword(onlineStatus, hasCar, password);
    }

}
