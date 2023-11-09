package com.example.weatherproject.module;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Weather {
    private Location location;
    private Current current;
}