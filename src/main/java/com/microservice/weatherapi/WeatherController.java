package com.microservice.weatherapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class WeatherController {
@Autowired
private WeatherService weatherService;

@GetMapping("/weather")
public String getWeatherForm() {
    return "weather";
}

@GetMapping("/weather/result")
public String getWeather(@RequestParam String city, Model model) throws Exception {
    String result = weatherService.getResponse(city);
    model.addAttribute("result", result);
    return "weather";
}
}
