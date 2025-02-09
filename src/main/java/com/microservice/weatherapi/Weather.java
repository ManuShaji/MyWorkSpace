package com.microservice.weatherapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Weather {
private String city;
private String temperature;
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getTemperature() {
	return temperature;
}
public void setTemperature(String temperature) {
	this.temperature = temperature;
}
}
