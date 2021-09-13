package com.maintaining.service.carService;

import com.maintaining.domain.Car;
import com.maintaining.domain.Driver;
import com.maintaining.domainValues.CarStatus;
import com.maintaining.domainValues.Enginetype;
import com.maintaining.domainValues.OnlineStatus;
import com.maintaining.exceptions.NotFoundCarException;
import com.maintaining.repository.CarRepository;
import com.maintaining.repository.DriverRepository;
import com.maintaining.service.driverService.DriverService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;


    Car carForTest;

    @BeforeEach
    public void initiate() {
        carRepository.deleteAll();

    }

    @Test
    public void whenGivenUnValidDataCarToInsertCarThenRejecUpdateTransaction() {
        Car car = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(1)
                .carModel("Canyon")
                .carColor("RED")
                .convertible(true)
                .rating(new BigDecimal(1))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("GMC")
                .build();
        assertThrows(TransactionSystemException.class, () -> carService.insertItem(car));
    }

    @Test
    public void whenGivenValidDataCarToInsertCarThenUpdateTransactionSuccess() {
        Car car = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Canyon")
                .carColor("RED")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("GMC")
                .build();
        List<Car> cars = carService.getAllItems();
        int count = cars.size();

        Car car1 = carService.insertItem(car);
        assertEquals(car1.getCarId(), car.getCarId());
        assertEquals(car1.getManufacturer(), car.getManufacturer());
        assertNotNull(car1);
        assertEquals(count + 1, carService.getAllItems().size());
    }

    @Test
    public void whenGivenUnValidDataCarToUpdateCarThenUpdateTransactionReject() {
        this.carForTest = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Canyon")
                .carColor("RED")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("GMC")
                .build();
        Car car1 = carService.insertItem(carForTest);
        assertEquals(car1.getCarId(), carForTest.getCarId());
        assertEquals(car1.getManufacturer(), carForTest.getManufacturer());
        assertNotNull(car1);

        Car carToUpdate = Car.builder()
                .carId(carForTest.getCarId())
                .dateCreated(ZonedDateTime.now())
                .seatCount(2)
                .carModel("Canyon")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("GMC")
                .build();

        assertThrows(TransactionSystemException.class, () -> carService.updateItem(carToUpdate));
    }

    @Test
    public void whenGivenValidDataCarToUpdateCarThenUpdateCarSuccess() {
        this.carForTest = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Canyon")
                .carColor("RED")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("GMC")
                .build();
        Car car1 = carService.insertItem(carForTest);
        assertEquals(car1.getCarId(), carForTest.getCarId());
        assertEquals(car1.getManufacturer(), carForTest.getManufacturer());
        assertNotNull(car1);

        Car carToUpdate = Car.builder()
                .carId(carForTest.getCarId())
                .dateCreated(ZonedDateTime.now())
                .licensePlate("014964OPN")
                .seatCount(4)
                .carModel("Canyon")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("GMC")
                .build();
        Car c = carService.updateItem(carToUpdate);
        assertEquals(c.getCarId(), carForTest.getCarId());
        assertNotEquals(c.getCarColor(), carForTest.getCarColor());
        assertNotEquals(c.getSeatCount(), carForTest.getSeatCount());
    }

    @Test
    public void whenGivenUnValidDataCarToDeleteCarThenNotFoundCarException() {
        Car dodge = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Dakota")
                .carColor("Red")
                .convertible(true)
                .rating(new BigDecimal(4.9))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.HYBRID)
                .manufacturer("Dodge")
                .build();
        Car gmc = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Yukon")
                .carColor("Red")
                .convertible(true)
                .rating(new BigDecimal(4.9))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.HYBRID)
                .manufacturer("GMC")
                .build();
        carService.insertItem(dodge);
        carService.insertItem(gmc);

        assertThrows(NotFoundCarException.class, () -> carService.deleteItem(dodge.getCarId() + gmc.getCarId()));
    }

    @Test
    public void whenGivenValidDataCarToDeleteCarThenDeleteCarSuccess() {
        Car dodge = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Dakota")
                .carColor("Red")
                .convertible(true)
                .rating(new BigDecimal(4.9))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.HYBRID)
                .manufacturer("Dodge")
                .build();
        Car gmc = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSX964OPN")
                .seatCount(2)
                .carModel("Yukon")
                .carColor("Red")
                .convertible(true)
                .rating(new BigDecimal(4.9))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.HYBRID)
                .manufacturer("GMC")
                .build();
        carService.insertItem(dodge);
        carService.insertItem(gmc);
        int carCount = carService.getAllItems().size();
        carService.deleteItem(dodge.getCarId());
        assertEquals(carCount - 1, carService.getAllItems().size());
    }

    @Test
    public void whenSelectAllCarsAllOfThemMustReturn() {
        Car bentley = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QVDFG4OPN")
                .seatCount(4)
                .carModel("Continental")
                .carColor("Purple")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Bentley")
                .build();
        Car porsche = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSESD7OPN")
                .seatCount(2)
                .carModel("Cayenne")
                .carColor("Orange")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Porsche")
                .build();
        Car astonMartin = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("OPLV64OPN")
                .seatCount(2)
                .carModel("DBS")
                .carColor("Maroon")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("AstonMartin")
                .build();
        carService.insertItem(bentley);
        carService.insertItem(porsche);
        carService.insertItem(astonMartin);

        List<Car> cars = carService.getAllItems();
        assertEquals(3, cars.size());
    }

    @Test
    public void whenSelectExistCarIdItMustReturn() {
        Car bentley = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QVDFG4OPN")
                .seatCount(4)
                .carModel("Continental")
                .carColor("Purple")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Bentley")
                .build();
        Car porsche = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSESD7OPN")
                .seatCount(2)
                .carModel("Cayenne")
                .carColor("Orange")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Porsche")
                .build();
        Car astonMartin = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("OPLV64OPN")
                .seatCount(2)
                .carModel("DBS")
                .carColor("Maroon")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("AstonMartin")
                .build();
        carService.insertItem(bentley);
        carService.insertItem(porsche);
        carService.insertItem(astonMartin);

        Car car = carService.getItemById(porsche.getCarId());
        assertEquals("Porsche", car.getManufacturer());
    }

    @Test
    public void whenSelectNotExistCarIdThenNotFoundCarException() {
        Car bentley = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QVDFG4OPN")
                .seatCount(4)
                .carModel("Continental")
                .carColor("Purple")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Bentley")
                .build();
        Car porsche = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("QSESD7OPN")
                .seatCount(2)
                .carModel("Cayenne")
                .carColor("Orange")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Porsche")
                .build();
        Car astonMartin = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("OPLV64OPN")
                .seatCount(2)
                .carModel("DBS")
                .carColor("Maroon")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("AstonMartin")
                .build();
        carService.insertItem(bentley);
        carService.insertItem(porsche);
        carService.insertItem(astonMartin);
        assertThrows(NotFoundCarException.class, () -> carService.getItemById
                (bentley.getCarId() + porsche.getCarId() + astonMartin.getCarId()));
    }
}