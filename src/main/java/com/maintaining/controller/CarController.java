package com.maintaining.controller;

import com.maintaining.domain.Car;
import com.maintaining.dto.CarDto;
import com.maintaining.exceptions.ConstraintsViolationException;
import com.maintaining.exceptions.NotFoundCarException;
import com.maintaining.controller.mapper.CarMapper;
import com.maintaining.service.carService.CarServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Api(value = "cars", description = "Operations for (CRUD) cars and drivers are here .")
@RequiredArgsConstructor
//@Secured("USER")
public class CarController {

    private final CarServiceImpl carService;
    private final CarMapper carMapper;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDto = carMapper.mapListToCarDot(carService.getAllItems());
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<CarDto> getCar(@Valid @PathVariable(value = "carId") Long carId)
            throws NotFoundCarException {
        CarDto carDto = carMapper.mapToDto(carService.getItemById(carId));
        return ResponseEntity.ok(carDto);
    }

    @PostMapping("cars")
    public ResponseEntity<CarDto> addCar(@Valid @RequestBody CarDto carDto)  {
        Car car = carMapper.mapToEntity(carDto);
        CarDto c = carMapper.mapToDto(carService.insertItem(car));
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    public Map<String, Boolean> deleteCar(@Valid @PathVariable(value = "id") Long carId)
            throws NotFoundCarException {
        return carService.deleteItem(carId);
    }

    @PutMapping("/cars")
    public ResponseEntity<CarDto> updateCar(@Valid @RequestBody CarDto dto)
            throws NotFoundCarException {
        Car car = carService.getItemById(dto.getCarDtoId());
        Car mapperCar = carMapper.mapToUpdate(dto, car);
        CarDto carDto = carMapper.mapToDto(carService.updateItem(mapperCar));
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }

}
