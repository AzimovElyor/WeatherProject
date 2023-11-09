package com.example.weatherproject.module;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Location {
    private Double lat;
    private Double lon;
    private String name;
    private String region;
    private String country;
    private String tz_id;
    private Integer localtime_epoch;
    private String localtime;
}
