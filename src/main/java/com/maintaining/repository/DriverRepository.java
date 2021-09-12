package com.maintaining.repository;

import com.maintaining.domain.Driver;
import com.maintaining.domainValues.Enginetype;
import com.maintaining.domainValues.OnlineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByAndCar_CarId(long carId);

    List<Driver> findByAndCar_Manufacturer(String manufacturerName);

    List<Driver> findByAndCar_EngineType(Enginetype enginetype);

    List<Driver> findByAndCar_SeatCount(int seatCount);

    List<Driver> findByAndCar_Convertible(boolean convertible);

    List<Driver> findByAndCar_Rating(BigDecimal rating);

    List<Driver> findByAndCar_EngineTypeAndCar_Manufacturer(Enginetype enginetype,String manufacturerName);

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

    List<Driver> findByOnlineStatusAndHasCarNow(OnlineStatus onlineStatus, Boolean hasCar);

    List<Driver> findByOnlineStatusAndHasCarNowOrPassword(OnlineStatus onlineStatus, Boolean hasCar, String password);
}
