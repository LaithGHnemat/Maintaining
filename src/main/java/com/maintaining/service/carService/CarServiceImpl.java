package com.maintaining.service.carService;

import com.maintaining.domain.Car;
import com.maintaining.exceptions.NotFoundCarException;
import com.maintaining.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car insertItem(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> getAllItems() {
        return this.carRepository.findAll();
    }

    @Override
    public Car getItemById(long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundCarException("Car not found for this id :: " + carId));
        return car;
    }

    @Override
    public Car updateItem(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public Map<String, Boolean> deleteItem(long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundCarException("car not found for this id :: " + id));
        carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
