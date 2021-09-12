package com.maintaining.controller.mapper;

import com.maintaining.domain.Driver;
import com.maintaining.dto.DriverDot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CarMapper.class})

public interface DriverMapper {

    @Mapping(source = "id", target = "driverId")
    DriverDot mapToDto(Driver driver);

    @Mapping(source = "driverId", target = "id")
    Driver mapToEntity(DriverDot driverDot);


    @Mapping(source = "id", target = "driverId")
    List<DriverDot> mapListToDot(List<Driver> drivers);

    @Mapping(source = "driverId", target = "id")
    Driver mapToUpdate(DriverDot driverDot, @MappingTarget Driver driver);

}
