package com.maintaining.controller.mapper;

import com.maintaining.domain.Car;
import com.maintaining.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CarMapper {

    @Mapping(source = "carId", target = "carDtoId")
    CarDto mapToDto(Car car);

    @Mapping(source = "carDtoId", target = "carId")
    Car mapToEntity(CarDto carDto);

    @Mapping(source = "carId", target = "carDtoId")
    List<CarDto> mapListToCarDot(List<Car> cars);

    @Mapping(source = "carDtoId", target = "carId")
    Car mapToUpdate(CarDto carDto, @MappingTarget Car car);
}
