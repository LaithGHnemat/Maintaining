package com.maintaining.service.driverService;

import com.maintaining.domain.Car;
import com.maintaining.domain.Driver;
import com.maintaining.domainValues.CarStatus;
import com.maintaining.domainValues.Enginetype;
import com.maintaining.domainValues.OnlineStatus;
import com.maintaining.exceptions.*;
import com.maintaining.repository.CarRepository;
import com.maintaining.repository.DriverRepository;
import com.maintaining.service.carService.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DriverServiceImlTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    Driver driver;
    Driver driver1;

    @BeforeEach
    public void initiate() {
        preparedData();
    }

    @Test
    public void whenGivenValidDataThenInsertSuccessInTheDataBase() {
        List<Driver> d = driverRepository.findAll();
        assertEquals(3, d.size());
    }

    @Test
    public void whenGivenNotExistingIdToDeleteThenDataNotDeletedSuccess() {
        assertThrows(NotFoundDriverExpetion.class, () -> driverService.deleteItem(driver.getId() + driver1.getId()));
        List<Driver> d = driverRepository.findAll();
        assertEquals(3, d.size());

    }

    @Test
    public void whenGivenExistingIdToDeleteThenDataDeletedSuccess() throws NotFoundDriverExpetion {
        driverService.deleteItem(driver.getId());
        List<Driver> d = driverRepository.findAll();
        assertEquals(2, d.size());
    }

    @Test
    public void whenGivenUnValidDataDriverToUpdateThenRejecUpdateTransaction() {
        this.driver = Driver.builder()
                .id(this.driver.getId())
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .password("ZDvdc")
                .car(null)
                .build();
        assertThrows(TransactionSystemException.class, () -> driverService.updateItem(driver));
    }

    @Test
    public void whenGivenValiedDriverToUpdateThenUpdateSuccessfully() throws ConstraintsViolationException, NotFoundDriverExpetion {
        this.driver = Driver.builder()
                .id(this.driver.getId())
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Mohanad")
                .password("ZDvdc")
                .car(null)
                .build();
        driverService.updateItem(driver);
        Driver d = driverService.getItemById(driver.getId());
        assertEquals("Mohanad", d.getUsername());
    }

    @Test
    public void givenNotFoundDriverIdWhenSelectItThenExeption() {
        assertThrows(NotFoundDriverExpetion.class, () -> driverService.getItemById(driver.getId() + driver1.getId()));
    }

    @Test
    public void givenDriverIdWhenSelectItThenSlecetIt() throws NotFoundDriverExpetion {
        Driver d = driverService.getItemById(driver.getId());
        assertEquals(d.getId(), d.getId());
        assertEquals(d.getUsername(), d.getUsername());
        assertEquals(d.getOnlineStatus(), d.getOnlineStatus());
        assertEquals(d.getHasCarNow(), d.getHasCarNow());
    }

    @Test
    public void whenSelectAllDriversThenSlecetThem() {
        List<Driver> d = driverService.getAllItems();
        assertEquals(3, d.size());
    }

    @Test
    public void whenSelectDriversByThereStayusThenGetThem() throws NotFoundDriverExpetion {
        List<Driver> drivers = driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
        assertEquals(2, drivers.size());
    }

    @Test
    public void whenGivenMissingDataThenRejectInsertTransaction() {
        Driver driver = Driver.builder()
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .password("ZDvdc")
                .car(null)
                .build();
        assertThrows(Exception.class, () -> driverService.insertItem(driver));
    }

    @Test
    public void whenSelectDriversByCarsCharacteristics() throws NotFoundDriverExpetion {
        driverRepository.deleteAll();
        carRepository.deleteAll();

        Car car = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("MNG45OH7")
                .seatCount(4)
                .carModel("Accord")
                .carColor("Yallow")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Honda")
                .build();
        Car car2 = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("JORT679ER")
                .seatCount(2)
                .carModel("Accord")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Honda")
                .build();

        Car car3 = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("451679ER")
                .seatCount(2)
                .carModel("Accord")
                .carColor("red")
                .convertible(true)
                .rating(new BigDecimal(2))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.GAS)
                .manufacturer("Honda")
                .build();

        carService.insertItem(car);
        carService.insertItem(car2);
        carService.insertItem(car3);

        Driver d = Driver.builder()
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Aya")
                .password("werrw32")
                .car(car)
                .build();
        Driver dd = Driver.builder()
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Laith")
                .password("laith215")
                .car(car2)
                .build();
        Driver ddd = Driver.builder()
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("ysan")
                .password("tftftf")
                .car(car3)
                .build();

        driverService.insertItem(d);
        driverService.insertItem(dd);
        driverService.insertItem(ddd);

        List<Driver> driverRepositoryByAndCar_engineType = driverRepository.findByAndCar_EngineType(Enginetype.ELECTRIC);
        List<Driver> driversByManufacturer = driverRepository.findByAndCar_Manufacturer("Honda");
        List<Driver> driversHaveConvCars = driverRepository.findByAndCar_Convertible(true);
        List<Driver> drivresSeatCount = driverRepository.findByAndCar_SeatCount(2);
        List<Driver> nnn = driverRepository.findByAndCar_Rating(new BigDecimal(2));

        assertEquals(2, driverRepositoryByAndCar_engineType.size());
        assertEquals(3, driversByManufacturer.size());
        assertEquals(3, driversHaveConvCars.size());
        assertEquals(2, drivresSeatCount.size());
        assertEquals(1, nnn.size());
    }

    @Test
    public void whenMapOfflineDriverToCarThenOfflineDriverException() {
        driverRepository.deleteAll();
        carRepository.deleteAll();
        Car car = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("JORT679ER")
                .seatCount(4)
                .carModel("B-Series")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.UNMAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Mazda")
                .build();
        carService.insertItem(car);

        Driver driver = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.OFFLINE)
                .username("Aya")
                .password("werrw32")
                .car(null)
                .build();
        driverService.insertItem(driver);

        assertNull(driver.getCar());
        assertThrows(OfflineDriverExceptoin.class, () -> driverService.mapCar(driver, car));
    }

    @Test
    public void whenMapOnlineDriverToCarInUsingThenCarAlreadyInUseException() {
        driverRepository.deleteAll();
        carRepository.deleteAll();
        Car car = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("JORT679ER")
                .seatCount(4)
                .carModel("LS Hybrid")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.MAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Lexus")
                .build();
        carService.insertItem(car);

        Driver driver = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Dalal")
                .password("HBHBIOD")
                .car(null)
                .build();
        driverService.insertItem(driver);

        assertNull(driver.getCar());
        assertThrows(CarAlreadyInUseException.class, () -> driverService.mapCar(driver, car));
    }

    @Test
    public void whenMapOnlineDriverToFreeCarThenMapTheCarToDriver() throws CarAlreadyInUseException, OfflineDriverExceptoin, ConstraintsViolationException {
        driverRepository.deleteAll();
        carRepository.deleteAll();
        Car car = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("JORT679ER")
                .seatCount(4)
                .carModel("LS Hybrid")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.UNMAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Lexus")
                .build();
        carService.insertItem(car);


        Driver driver = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Dalal")
                .password("HBHBIOD")
                .car(null)
                .build();
        driverService.insertItem(driver);
        Driver d = driverService.mapCar(driver, car);

        assertNotNull(d.getCar());
        assertEquals(d.getCar().getManufacturer(), "Lexus");
        assertEquals(d.getCar().getCarId(), car.getCarId());
    }

    @Test
    public void whenDeselectCarThenGivenCarMapedToOtherDriverThenRemoveCarException() throws CarAlreadyInUseException, OfflineDriverExceptoin, ConstraintsViolationException {
        driverRepository.deleteAll();
        carRepository.deleteAll();

        Car carLexus = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("JORT679ER")
                .seatCount(4)
                .carModel("LS Hybrid")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.UNMAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Lexus")
                .build();
        carService.insertItem(carLexus);

        Driver driverDalal = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Dalal")
                .password("HBHBIOD")
                .car(null)
                .build();
        driverService.insertItem(driverDalal);
        Driver d = driverService.mapCar(driverDalal, carLexus);

        Car carChevrolet = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("BRAS123YU")
                .seatCount(4)
                .carModel("LS Hybrid")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.UNMAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Lexus")
                .build();
        carService.insertItem(carChevrolet);

        Driver driverEman = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Eman")
                .password("ZZZZ")
                .car(null)
                .build();
        driverService.insertItem(driverEman);

        Driver dd = driverService.mapCar(driverEman, carChevrolet);
        assertThrows(RemoveCarException.class, () -> driverService.deselectCar(driverEman, carLexus));

    }

    @Test
    public void whenDeselectCarThenGivenCarOfTheDriverThenCarDeselected() throws CarAlreadyInUseException, OfflineDriverExceptoin, ConstraintsViolationException {
        driverRepository.deleteAll();
        carRepository.deleteAll();
        Car carLexus = Car.builder()
                .dateCreated(ZonedDateTime.now())
                .licensePlate("JORT679ER")
                .seatCount(4)
                .carModel("LS Hybrid")
                .carColor("Green")
                .convertible(true)
                .rating(new BigDecimal(4))
                .carStatus(CarStatus.UNMAP)
                .engineType(Enginetype.ELECTRIC)
                .manufacturer("Lexus")
                .build();
        carService.insertItem(carLexus);


        Driver driverLexus = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Ramos")
                .password("HBHBIOD")
                .car(null)
                .build();
        driverService.insertItem(driverLexus);
        Driver d = driverService.mapCar(driverLexus, carLexus);

        assertNotNull(d.getCar());
        assertEquals(d.getCar().getManufacturer(), "Lexus");
        assertEquals(d.getCar().getCarId(), carLexus.getCarId());

        driverService.deselectCar(driverLexus, carLexus);
        assertNull(d.getCar());
    }


    private void preparedData() {
        driverRepository.deleteAll();
        this.driver = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("laith")
                .password("ZDvdc")
                .car(null)
                .build();
        this.driver1 = Driver.builder()
                .hasCarNow(false)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.ONLINE)
                .username("Aya")
                .password("werrw32")
                .car(null)
                .build();

        Driver driver2 = Driver.builder()
                .hasCarNow(true)
                .dateCreated(ZonedDateTime.now())
                .onlineStatus(OnlineStatus.OFFLINE)
                .username("Aya")
                .password("werrw32")
                .car(null)
                .build();
        driverService.insertItem(driver);
        driverService.insertItem(driver1);
        driverService.insertItem(driver2);
    }
}