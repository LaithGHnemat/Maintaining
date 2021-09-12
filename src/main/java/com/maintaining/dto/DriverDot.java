package com.maintaining.dto;

import com.maintaining.domainValues.OnlineStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DriverDot {

    private long driverId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    private String username;
    private String password;
    private OnlineStatus onlineStatus;
    private CarDto car;
    private Boolean hasCarNow = false;

}
