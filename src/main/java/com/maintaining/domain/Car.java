package com.maintaining.domain;


import com.maintaining.domainValues.CarStatus;
import com.maintaining.domainValues.Enginetype;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "cars_tabel")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id", nullable = false)
    private long carId;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name = "license_Plate", nullable = false)
    @NotEmpty(message = "license Plate must not be empty")
    @Size(min = 7, max = 10)
    private String licensePlate;

    @Column(name = "seat_count", nullable = false)
    @Min(2)
    private int seatCount;

    @Column(name = "car_model", nullable = false)
    private String carModel;
    @Column(name = "car_color", nullable = false)
    private String carColor;
    @Column(name = "convertible", nullable = false)
    private boolean convertible;

    @Column(nullable = true, scale = 1)
    @Min(1)
    @Max(5)
    private BigDecimal rating = null;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus carStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Enginetype engineType;

    @Column(nullable = false)
    @NotEmpty(message = "Car manufacturer cannot be null!")
    private String manufacturer;

}
