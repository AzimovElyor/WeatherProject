package com.example.weatherproject.controller;

import com.example.weatherproject.module.Weather;
import com.example.weatherproject.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    @GetMapping("/current-weather")
    public String getCurrentWeatherPage(Model model){
        Set<String> countryNames = weatherService.getCountryNames();
        model.addAttribute("countries",countryNames);
        return "/weather/weather";
    }
    @PostMapping("/current-weather")
    public String getCurrentWeather(Model model, @RequestParam String city){
        Weather todayWeather = weatherService.getTodayWeather(city);
        Set<String> countryNames = weatherService.getCountryNames();
        model.addAttribute("countries",countryNames);
        model.addAttribute("weather", todayWeather);
        return "/weather/weather";
    }
}
