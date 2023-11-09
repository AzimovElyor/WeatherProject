package com.example.weatherproject.module;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Current {
    private String last_updated;
    private Double temp_c;
    private Double temp_f;
    private Double wind_mph;
    private Double wind_kph;
    private Double wind_degree;
    private Integer humidity;
    private Double feelslike_c;
    private Double feelslike_f;

}
