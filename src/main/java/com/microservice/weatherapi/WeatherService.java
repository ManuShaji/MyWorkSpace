package com.microservice.weatherapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class WeatherService {
@Value("${weather.api.key}")
private  String OPEN_API_KEY;
private final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
public String getApi() {
	return OPEN_API_KEY;
}
private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
@Cacheable("weatherCache")
public String getResponse(String city) throws Exception {
	RestTemplate restTemplate = new RestTemplate();
	 String url = API_URL+"?q=" + city + "&appid=" + OPEN_API_KEY;
	String response = restTemplate.getForObject(url,String.class);
	ObjectMapper mapper  =new ObjectMapper();
	JsonNode jsonNode = mapper.readTree(response);
	String description = jsonNode.path("weather").get(0).path("description").asText();
	String temperature = jsonNode.path("main").path("temp").asText();
	Double temp = Double.parseDouble(temperature);
	temp = (double) Math.round(temp-273);
	
	Weather weather  = new Weather();
	weather.setCity(city);
	weather.setTemperature(temp+" °C");
	 String formattedResult = String.format("Temperature: %s°C, Description: %s", temp, description);
	 log.info(formattedResult);
	return formattedResult;
}
}
