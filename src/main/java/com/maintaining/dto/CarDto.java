package com.maintaining.dto;

import com.maintaining.domainValues.CarStatus;
import com.maintaining.domainValues.Enginetype;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CarDto {

    private long carDtoId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    private String licensePlate;
    private int seatCount;
    private String carModel;
    private String carColor;
    private boolean convertible;
    private BigDecimal rating = null;
    private CarStatus carStatus;
    private Enginetype engineType;
    private String manufacturer;
}
