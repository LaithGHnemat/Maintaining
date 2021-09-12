package com.maintaining.controller;

import com.maintaining.domain.Car;
import com.maintaining.domain.Driver;
import com.maintaining.domainValues.Enginetype;
import com.maintaining.domainValues.OnlineStatus;
import com.maintaining.dto.DriverDot;
import com.maintaining.exceptions.*;
import com.maintaining.controller.mapper.DriverMapper;
import com.maintaining.service.carService.CarServiceImpl;
import com.maintaining.service.driverService.DriverServiceIml;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/driver")
@Api(value = "drivers", description = "Operations for Driver services (CRUD).")
@RequiredArgsConstructor
public class DriverController {

    private final DriverServiceIml driverService;
    private final DriverMapper driverMapper;
    private final CarServiceImpl carService;

    @GetMapping("/drivers")
    public ResponseEntity<List<DriverDot>> getAllCars() {
        List<DriverDot> driverDots = driverMapper.mapListToDot(driverService.getAllItems());
        return new ResponseEntity<>(driverDots, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDot> getDriver(@Valid @PathVariable(value = "driverId") Long driverId)
            throws NotFoundCarException {
        DriverDot driverDot = driverMapper.mapToDto(driverService.getItemById(driverId));
        return ResponseEntity.ok(driverDot);
    }

    @PostMapping("drivers")
    public ResponseEntity<DriverDot> addCar(@Valid @RequestBody DriverDot driverDot) throws ConstraintsViolationException {
        Driver driver = driverMapper.mapToEntity(driverDot);
        DriverDot dto = driverMapper.mapToDto(driverService.insertItem(driver));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/drivers/{id}")
    public Map<String, Boolean> deleteDriver(@Valid @PathVariable(value = "id") Long driverId)
            throws NotFoundCarException {
        return driverService.deleteItem(driverId);
    }

    @PutMapping("/drivers")
    public ResponseEntity<DriverDot> updateDriver(@Valid @RequestBody DriverDot driverDot)
            throws NotFoundCarException, ConstraintsViolationException {
        Driver driver = driverService.getItemById(driverDot.getDriverId());
        Driver mapperDriver = driverMapper.mapToUpdate(driverDot, driver);
        DriverDot dto = driverMapper.mapToDto(driverService.updateItem(mapperDriver));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/drivers/mapCar/{driverId}/{carId}")
    public ResponseEntity<DriverDot> mapCar(@Valid @PathVariable Long driverId, @Valid @PathVariable Long carId) throws CarAlreadyInUseException, OfflineDriverExceptoin {
        Driver driver = driverService.getItemById(driverId);
        Car car = carService.getItemById(carId);
        DriverDot dto = driverMapper.mapToDto(driverService.mapCar(driver, car));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/drivers/deselectCar/{driverId}/{carId}")
    public ResponseEntity<DriverDot> deselectCar(@Valid @PathVariable Long driverId, @Valid @PathVariable Long carId) {
        Driver driver = driverService.getItemById(driverId);
        Car car = carService.getItemById(carId);
        DriverDot dto = driverMapper.mapToDto(driverService.deselectCar(driver, car));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/findByCarId/{carId}")
    public Driver findByCarId(@Valid @PathVariable Long carId) throws ConstraintsViolationException, NotFoundDriverExpetion {
        return driverService.findDriverByCarId(carId);
    }

    @GetMapping("/drivers/manufacturer/{mnf}")
    public ResponseEntity<List<DriverDot>> findByManufacturer(@Valid @PathVariable String mnf) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriverByManufacturer(mnf));
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping("/drivers/Enginetype/{enginetype}")
    public ResponseEntity<List<DriverDot>> findByEngineType(@Valid @PathVariable Enginetype enginetype) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriversEnginetype(enginetype));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/SeatCount/{seatCount}")
    public ResponseEntity<List<DriverDot>> findBySeatCount(@Valid @PathVariable int seatCount) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriversBySeatCount(seatCount));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/Convertible/{convertible}")
    public ResponseEntity<List<DriverDot>> findByConvertible(@Valid @PathVariable boolean convertible) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriversHaveConvertibleCars(convertible));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/Rating/{rating}")
    public ResponseEntity<List<DriverDot>> findByRating(@Valid @PathVariable BigDecimal rating) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriversByRatingCars(rating));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/findDriversByStatus/{onlineStatus}")
    public ResponseEntity<List<DriverDot>> findDriversByStatus(@Valid @PathVariable OnlineStatus onlineStatus) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriversByStatus(onlineStatus));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/EnginetypeAndManufacturer/{enginetype}/{manufacturer}")
    public ResponseEntity<List<DriverDot>> findByEngineTypeAndManufacturer(@Valid @PathVariable Enginetype enginetype, @Valid @PathVariable String manufacturer) throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.findDriversEngineTypeAndManufacturer(enginetype, manufacturer));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/findDriversByStatus/{onlineStatus}/{hasCar}")
    public ResponseEntity<List<DriverDot>> findDriversByStatusAndHasCar(@Valid @PathVariable OnlineStatus onlineStatus, @Valid @PathVariable boolean hasCar)
            throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.getDriverByStatusAndHasCar(onlineStatus, hasCar));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/drivers/findDriversByStatus/{onlineStatus}/{hasCar}/{password}")
    public ResponseEntity<List<DriverDot>> findDriversByStatusAndHasCarOrPassord
            (@Valid @PathVariable OnlineStatus onlineStatus, @Valid @PathVariable boolean hasCar, @Valid @PathVariable String password)
            throws ConstraintsViolationException, NotFoundDriverExpetion {
        List<DriverDot> dto = driverMapper.mapListToDot(driverService.getDriverByStatusAndHasCarOrPassword(onlineStatus, hasCar, password));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
